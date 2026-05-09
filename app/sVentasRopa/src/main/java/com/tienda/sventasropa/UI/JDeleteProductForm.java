package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.interfaces.IProductService;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

public class JDeleteProductForm extends javax.swing.JInternalFrame {

    private final IProductService productService;

    /**
     * Constructor para NetBeans
     */
    public JDeleteProductForm() {
        this.productService = null;
        initComponents();
        setSize(600, 450);
        setMinimumSize(new Dimension(600, 450));
        setResizable(true);
    }

    /**
     * Constructor para tu MainFrame
     */
    public JDeleteProductForm(IProductService productService) {

    this.productService = productService;

    initComponents();

    initTable();

    loadProductsTable();

    UITheme.apply(this);
    }
    
    private void initTable() {

    tblProducts.setModel(
        new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID",
                "Name",
                "Size",
                "Color",
                "Price",
                "Stock"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    }
    
    private void loadProductsTable() {

    DefaultTableModel model =
        (DefaultTableModel) tblProducts.getModel();

    model.setRowCount(0);

    if (productService == null) return;

    for (Product product : productService.getAllProducts()) {

        model.addRow(new Object[]{
            product.getProductId(),
            product.getProductName(),
            product.getProductSize(),
            product.getProductColor(),
            product.getProductPrice(),
            product.getProductStock()
        });
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblSize = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        lblColor = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        scrollProductsTable = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Delete Product");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Delete Existing Product");

        lblId.setText("ID:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblName.setText("Name:");

        txtName.setEditable(false);

        lblSize.setText("Size:");

        txtSize.setEditable(false);

        lblColor.setText("Color:");

        txtColor.setEditable(false);

        lblPrice.setText("Price:");

        txtPrice.setEditable(false);

        lblStock.setText("Stock:");

        txtStock.setEditable(false);

        btnDelete.setText("Delete Product");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Size", "Color", "Price", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setGridColor(new java.awt.Color(225, 225, 225));
        tblProducts.setRowHeight(28);
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        scrollProductsTable.setViewportView(tblProducts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblColor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addComponent(scrollProductsTable)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSize)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollProductsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            if (productService != null) {
                Product p = productService.findProductById(id);
                if (p != null) {
                    txtName.setText(p.getProductName());
                    txtSize.setText(p.getProductSize());
                    txtColor.setText(p.getProductColor());
                    txtPrice.setText(String.valueOf(p.getProductPrice()));
                    txtStock.setText(String.valueOf(p.getProductStock()));
                    txtId.setEditable(false);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID numérico válido para buscar.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "No Encontrado", JOptionPane.WARNING_MESSAGE);
            btnClearActionPerformed(null);
            loadProductsTable();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor busque un producto antes de eliminarlo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (productService != null && productService.deleteProduct(id)) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    btnClearActionPerformed(null);
                    
                    loadProductsTable(); 
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtId.setText("");
        txtId.setEditable(true); // Desbloqueamos el ID
        txtName.setText("");
        txtSize.setText("");
        txtColor.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtId.requestFocus();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        int row = tblProducts.getSelectedRow();
        txtId.setText(tblProducts.getValueAt(row, 0).toString());
        txtName.setText(tblProducts.getValueAt(row, 1).toString());
        txtSize.setText(tblProducts.getValueAt(row, 2).toString());
        txtColor.setText(tblProducts.getValueAt(row, 3).toString());
        txtPrice.setText(tblProducts.getValueAt(row, 4).toString());
        txtStock.setText(tblProducts.getValueAt(row, 5).toString());
        txtId.setEditable(false);
    }//GEN-LAST:event_tblProductsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollProductsTable;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}