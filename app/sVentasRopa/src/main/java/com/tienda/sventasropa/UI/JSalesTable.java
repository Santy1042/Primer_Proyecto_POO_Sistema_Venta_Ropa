/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.tienda.sventasropa.UI;

import javax.swing.table.DefaultTableModel;

/**.
 * JFrame que sera usado cuando el usuario quiera ver las ventas realizadas.
 * Se llamará atravez de un menu MDI
 * @author Christopher
 */
public class JSalesTable extends javax.swing.JInternalFrame {

    public JSalesTable() {
        initComponents();
        initSalesTable();
    }

    public void loadSales(Object[][] sales) {
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        model.setRowCount(0);
        if (sales == null) {
            return;
        }
        for (Object[] row : sales) {
            model.addRow(row);
        }
    }


    public void loadSalesFromService() {
        // Placeholder for a real sales service call.
        // Example:
        // List<Sale> sales = salesService.getAllSales();
        // Object[][] rows = sales.stream()
        //     .map(s -> new Object[]{s.getId(), s.getClientName(), s.getProductName(), s.getQuantity(), s.getTotal(), s.getDate()})
        //     .toArray(Object[][]::new);
        // loadSales(rows);
    }

    private void initSalesTable() {
        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Venta ID", "Cliente", "Producto", "Cantidad", "Total", "Fecha"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelShowSales = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventas");

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Venta ID", "Cliente", "Producto", "Cantidad", "Total", "Fecha"}
        ));
        jPanelShowSales.setViewportView(salesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowSales, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowSales, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jPanelShowSales;
    private javax.swing.JTable salesTable;
    // End of variables declaration//GEN-END:variables
}
