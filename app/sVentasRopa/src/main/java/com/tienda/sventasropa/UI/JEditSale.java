package com.tienda.sventasropa.UI;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Sale;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author marco
 */
public class JEditSale extends javax.swing.JInternalFrame {

    private ISaleService iSaleService;

    public JEditSale() {
        initComponents();
    }

    public JEditSale(ISaleService iSaleService) {
        initComponents();
        this.iSaleService = iSaleService;
        initTable();
        loadSales();
        UITheme.apply(this);
    }

    private void initTable() {
        tableClients.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Details", "date", "Subtotal"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });

        tableClients.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableClients.getSelectedRow() != -1) {
                int row = tableClients.getSelectedRow();
                fieldId.setText(tableClients.getValueAt(row, 0).toString());
                fieldClient.setText(tableClients.getValueAt(row, 1).toString());
                fieldSaleDetail.setText(tableClients.getValueAt(row, 2).toString());
                fieldDate.setText(tableClients.getValueAt(row, 3).toString());
                fieldSubTotal.setText(tableClients.getValueAt(row, 4).toString());
            }
        });
    }

    private void loadSales() {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setRowCount(0);
        if (iSaleService == null) return;
        List<Sale> sales = iSaleService.getAllSales();
        for (Sale sale : sales) {
            model.addRow(new Object[]{
                sale.getId(), sale.getClient().getName(), sale.getClient().getLastName(), sale.getClient().getEmail(), sale.getClient().getPhoneNumber()
            });
        }
    }

    private void updateSale() {
        try {
            if (fieldId.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Seleccione un cliente de la tabla.");
            }
            int id = Integer.parseInt(fieldId.getText().trim());
            Sale Updatesale = new Sale(id, fieldClient.getText().trim(), fieldSaleDetail.getText().trim(), fieldDate.getText().trim(), fieldSubTotal.getText().trim());
            iSaleService.updateSale(Updatesale);
            JOptionPane.showMessageDialog(this, "Venta actualizada exitosamente.");
            loadSales();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        fieldId.setText("");
        fieldClient.setText("");
        fieldSaleDetail.setText("");
        fieldDate.setText("");
        fieldSubTotal.setText("");
        tableClients.clearSelection();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        fieldId = new javax.swing.JTextField();
        labelClient = new javax.swing.JLabel();
        fieldClient = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        fieldSaleDetail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        fieldDate = new javax.swing.JTextField();
        labelPhone = new javax.swing.JLabel();
        fieldSubTotal = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        scrollClients = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();

        // Configuración de la ventana
        setClosable(true); // <--- ESTO PERMITE CERRAR EL FORMULARIO
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Edit Sale");
        setPreferredSize(new java.awt.Dimension(700, 520));

        panelTop.setBackground(new java.awt.Color(102, 255, 102));
        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Sale Data"));
        panelTop.setLayout(new java.awt.GridLayout(6, 2, 8, 8));

        labelId.setText("ID (Locked):");
        panelTop.add(labelId);
        fieldId.setEditable(false);
        fieldId.setBackground(new java.awt.Color(204, 204, 204));
        panelTop.add(fieldId);

        labelClient.setText("Client:");
        panelTop.add(labelClient);
        panelTop.add(fieldClient);

        labelLastName.setText("Last Name:");
        panelTop.add(labelLastName);
        panelTop.add(fieldSaleDetail);

        labelEmail.setText("Email:");
        panelTop.add(labelEmail);
        panelTop.add(fieldDate);

        labelPhone.setText("Phone:");
        panelTop.add(labelPhone);
        panelTop.add(fieldSubTotal);

        btnUpdate.setBackground(new java.awt.Color(153, 153, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Sale");
        btnUpdate.addActionListener(evt -> updateSale());
        panelTop.add(btnUpdate);

        btnClear.setBackground(new java.awt.Color(255, 102, 102));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(evt -> clearFields());
        panelTop.add(btnClear);

        getContentPane().add(panelTop, java.awt.BorderLayout.PAGE_START);
        scrollClients.setViewportView(tableClients);
        getContentPane().add(scrollClients, java.awt.BorderLayout.CENTER);

        pack();
    }

    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField fieldDate;
    private javax.swing.JTextField fieldId;
    private javax.swing.JTextField fieldClient;
    private javax.swing.JTextField fieldSaleDetail;
    private javax.swing.JTextField fieldSubTotal;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scrollClients;
    private javax.swing.JTable tableClients;
}