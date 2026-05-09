/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame que sera usado cuando el usuario quiera ver los clientes registrados.
 * Se llamará atravez de un menu MDI
 * @author Christopher
 */
public class JClientTable extends javax.swing.JInternalFrame {

    private IClientService iclientService;

    public JClientTable() {
        initComponents();
        initClientTable();
    }

    public JClientTable(IClientService iclientService) {
        this();
        this.iclientService = iclientService;
        loadClients();
        UITheme.apply(this);
    }

    public void setClientService(IClientService iclientService) {
        this.iclientService = iclientService;
    }

    public void loadClients() {
        DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
        model.setRowCount(0);

        if (iclientService == null) {
            return;
        }

        List<Client> clients = iclientService.getAllClients();
        for (Client client : clients) {
            model.addRow(new Object[]{
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber()
            });
        }
    }

    private void initClientTable() {
        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre", "Apellido", "Correo", "Teléfono"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        ScrollClientTable = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");

        Background.setBackground(new java.awt.Color(153, 255, 255));

        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre", "Apellido", "Correo", "Teléfono"}
        ));
        ScrollClientTable.setViewportView(clientTable);

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollClientTable, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollClientTable, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JTable clientTable;
    private javax.swing.JScrollPane ScrollClientTable;
    // End of variables declaration//GEN-END:variables
}
