/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christopher
 */
public class JCreateSale extends javax.swing.JInternalFrame {

    private ISaleService saleService;
    private IClientRepository clientRepository;
    private IProductRepository productRepository;
    private Sale currentSale;
    private int nextSaleId = 1;

    /**
     * Creates new form JCreateSale
     */
    public JCreateSale() {
        initComponents();
    }

    public JCreateSale(ISaleService saleService, IClientRepository clientRepository, IProductRepository productRepository) {
        this.saleService = saleService;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        initComponents();
        loadClients();
        loadProducts();
        initSaleDetailsTable();
    }

    public void setSaleService(ISaleService saleService) {
        this.saleService = saleService;
    }

    public void setClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void loadClients() {
        if (clientRepository == null) return;
        List<Client> clients = clientRepository.getAllClients();
        clientCombo.removeAllItems();
        for (Client client : clients) {
            clientCombo.addItem(client);
        }
    }

    private void loadProducts() {
        if (productRepository == null) return;
        List<Product> products = productRepository.getAllProducts();
        productCombo.removeAllItems();
        for (Product product : products) {
            productCombo.addItem(product);
        }
    }

    private void initSaleDetailsTable() {
        saleDetailsTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Producto", "Cantidad", "Precio Unit.", "Subtotal"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void createNewSale() {
        if (clientCombo.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Client selectedClient = (Client) clientCombo.getSelectedItem();
        currentSale = saleService.createSale(nextSaleId++, selectedClient);
        DefaultTableModel model = (DefaultTableModel) saleDetailsTable.getModel();
        model.setRowCount(0);
        updateTotalLabel();
    }

    private void addProductToSale() {
        if (currentSale == null) {
            createNewSale();
        }
        
        if (productCombo.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product selectedProduct = (Product) productCombo.getSelectedItem();
            if (selectedProduct.getProductStock() < quantity) {
                JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente. Disponible: " + selectedProduct.getProductStock(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SaleDetail detail = new SaleDetail(selectedProduct.getProductId(), selectedProduct, quantity);
            currentSale.addDetail(detail);
            
            DefaultTableModel model = (DefaultTableModel) saleDetailsTable.getModel();
            model.addRow(new Object[]{
                selectedProduct.getProductName(),
                quantity,
                selectedProduct.getProductPrice(),
                detail.getSubtotal()
            });

            quantityField.setText("");
            updateTotalLabel();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveSale() {
        if (currentSale == null || !currentSale.hasDetails()) {
            JOptionPane.showMessageDialog(this, "La venta debe tener al menos un producto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            saleService.saveSale(currentSale);
            JOptionPane.showMessageDialog(this, "Venta guardada exitosamente. ID: " + currentSale.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Reset for new sale
            currentSale = null;
            DefaultTableModel model = (DefaultTableModel) saleDetailsTable.getModel();
            model.setRowCount(0);
            updateTotalLabel();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotalLabel() {
        if (currentSale == null) {
            totalLabel.setText("Total: $0.00");
        } else {
            totalLabel.setText(String.format("Total: $%.2f", currentSale.getTotal()));
        }
    }

    private void deleteSale() {
        if (currentSale == null) {
            JOptionPane.showMessageDialog(this, "No hay venta en proceso para eliminar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar la venta #" + currentSale.getId() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                saleService.deleteSale(currentSale.getId());
                JOptionPane.showMessageDialog(this, "Venta eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Reset for new sale
                currentSale = null;
                DefaultTableModel model = (DefaultTableModel) saleDetailsTable.getModel();
                model.setRowCount(0);
                updateTotalLabel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel clientLabel = new javax.swing.JLabel();
        clientCombo = new javax.swing.JComboBox<>();
        javax.swing.JLabel productLabel = new javax.swing.JLabel();
        productCombo = new javax.swing.JComboBox<>();
        javax.swing.JLabel quantityLabel = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        javax.swing.JButton addProductBtn = new javax.swing.JButton();
        javax.swing.JButton removeProductBtn = new javax.swing.JButton();
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
        saleDetailsTable = new javax.swing.JTable();
        totalLabel = new javax.swing.JLabel();
        javax.swing.JButton saveSaleBtn = new javax.swing.JButton();
        javax.swing.JButton deleteSaleBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Crear Venta");

        clientLabel.setText("Cliente:");
        productLabel.setText("Producto:");
        quantityLabel.setText("Cantidad:");
        
        clientCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));
        productCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));

        quantityField.setText("1");

        addProductBtn.setText("Agregar Producto");
        addProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductToSale();
            }
        });

        saleDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Producto", "Cantidad", "Precio Unit.", "Subtotal"}
        ));
        scrollPane.setViewportView(saleDetailsTable);

        totalLabel.setText("Total: $0.00");
        totalLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        saveSaleBtn.setText("Guardar Venta");
        saveSaleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSale();
            }
        });

        deleteSaleBtn.setText("Eliminar Venta");
        deleteSaleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSale();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientLabel)
                            .addComponent(productLabel)
                            .addComponent(quantityLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantityField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addProductBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeProductBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveSaleBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSaleBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientLabel)
                    .addComponent(clientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productLabel)
                    .addComponent(productCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductBtn)
                    .addComponent(removeProductBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel)
                    .addComponent(saveSaleBtn)
                    .addComponent(deleteSaleBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> clientCombo;
    private javax.swing.JComboBox<Object> productCombo;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTable saleDetailsTable;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
