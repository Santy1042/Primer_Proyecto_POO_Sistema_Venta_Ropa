/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.service.ProductService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame que sera usado cuando el usuario quiera ver los productos registrados.
 * Se llamará atravez de un menu MDI
 * @author Christopher
 */
public class JProductsTable extends javax.swing.JInternalFrame {

    private ProductService productService;

    public JProductsTable() {
        initComponents();
        initProductsTable();
    }

    public JProductsTable(ProductService productService) {
        this();
        this.productService = productService;
        loadProductsFromService();
        UITheme.apply(this);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void loadProducts(Object[][] products) {
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);
        if (products == null) {
            return;
        }
        for (Object[] row : products) {
            model.addRow(row);
        }
    }

    public void loadProductsFromService() {
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);

        if (productService == null) {
            return;
        }

        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
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

    private void initProductsTable() {
        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Size","Color", "Price", "Stock"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelShowProducts = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos");

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre", "Precio", "Stock"}
        ));
        jPanelShowProducts.setViewportView(productsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jPanelShowProducts;
    private javax.swing.JTable productsTable;
    // End of variables declaration//GEN-END:variables
}
