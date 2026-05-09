package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

/**
 * @author marco
 */
public class JDeleteSaleForm extends javax.swing.JInternalFrame {

    private final ISaleService iSaleService;

    public JDeleteSaleForm(ISaleService iSaleService) {
        if (iSaleService == null) {
            throw new IllegalArgumentException("El servicio de ventas no puede ser nulo");
        }
        this.iSaleService = iSaleService;
        
        initComponents();
        initTable();
        loadSales();
        UITheme.apply(this);
    }

    private void initTable() {
        tableSales.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Client Name", "Total Items", "Date", "Total"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });

        tableSales.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableSales.getSelectedRow() != -1) {
                int row = tableSales.getSelectedRow();
                int id = Integer.parseInt(tableSales.getValueAt(row, 0).toString());
                fieldId.setText(String.valueOf(id));
                
                Optional<Sale> optSale = iSaleService.findSale(id);
                if (optSale.isPresent()) {
                    Sale sale = optSale.get();
                    StringBuilder detailsText = new StringBuilder();
                    
                    detailsText.append("CLIENTE: ").append(sale.getClient().getName()).append(" ").append(sale.getClient().getLastName()).append("\n");
                    detailsText.append("FECHA: ").append(sale.getDate().toString()).append("\n");
                    detailsText.append("--------------------------------------------------\n");
                    detailsText.append("ARTÍCULOS DE LA VENTA:\n");
                    
                    for (SaleDetail detail : sale.getDetails()) {
                        detailsText.append(" • ").append(detail.toString()).append("\n");
                    }
                    
                    detailsText.append("--------------------------------------------------\n");
                    detailsText.append("TOTAL A ELIMINAR: $").append(sale.getTotal());
                    
                    areaDetails.setText(detailsText.toString());
                } else {
                    areaDetails.setText("Error: No se pudieron cargar los detalles de la venta.");
                }
            }
        });
    }

    private void loadSales() {
        DefaultTableModel model = (DefaultTableModel) tableSales.getModel();
        model.setRowCount(0);
        
        List<Sale> sales = iSaleService.getAllSales();
        for (Sale sale : sales) {
            String clientName = (sale.getClient() != null) ? sale.getClient().getName() : "Desconocido";
            int itemsCount = (sale.getDetails() != null) ? sale.getDetails().size() : 0;
            
            model.addRow(new Object[]{
                sale.getId(), 
                clientName, 
                itemsCount, 
                sale.getDate().toString(), 
                sale.getSubtotal()
            });
        }
    }

    private void deleteSale() {
        try {
            String idText = fieldId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una venta de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea ELIMINAR PERMANENTEMENTE la Venta #" + id + "?\nEsta acción no se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
                
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = iSaleService.deleteSale(id);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Venta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    loadSales();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la venta. Es posible que ya no exista.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        fieldId.setText("");
        areaDetails.setText("");
        tableSales.clearSelection();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        panelControls = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        fieldId = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        scrollDetails = new javax.swing.JScrollPane();
        areaDetails = new javax.swing.JTextArea();
        scrollSales = new javax.swing.JScrollPane();
        tableSales = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Delete Sale Record");
        setPreferredSize(new java.awt.Dimension(750, 550));

        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de la Venta a Eliminar"));
        panelTop.setLayout(new java.awt.BorderLayout(0, 10));

        panelControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        labelId.setText("ID Venta Seleccionada:");
        panelControls.add(labelId);
        
        fieldId.setEditable(false);
        fieldId.setPreferredSize(new java.awt.Dimension(80, 25));
        panelControls.add(fieldId);

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setForeground(java.awt.Color.WHITE);
        btnDelete.setText("ELIMINAR VENTA");
        btnDelete.addActionListener(evt -> deleteSale());
        panelControls.add(btnDelete);

        btnClear.setText("Limpiar Selección");
        btnClear.addActionListener(evt -> clearFields());
        panelControls.add(btnClear);

        panelTop.add(panelControls, java.awt.BorderLayout.NORTH);

        areaDetails.setEditable(false);
        areaDetails.setColumns(20);
        areaDetails.setRows(6);
        areaDetails.setBackground(new java.awt.Color(245, 245, 245));
        areaDetails.setFont(new java.awt.Font("Monospaced", 0, 12));
        scrollDetails.setViewportView(areaDetails);

        panelTop.add(scrollDetails, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelTop, java.awt.BorderLayout.NORTH);

        scrollSales.setViewportView(tableSales);
        getContentPane().add(scrollSales, java.awt.BorderLayout.CENTER);

        pack();
    }

    private javax.swing.JTextArea areaDetails;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JTextField fieldId;
    private javax.swing.JLabel labelId;
    private javax.swing.JPanel panelControls;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scrollDetails;
    private javax.swing.JScrollPane scrollSales;
    private javax.swing.JTable tableSales;
}