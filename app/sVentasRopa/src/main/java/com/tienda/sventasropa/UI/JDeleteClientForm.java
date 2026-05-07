package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class JDeleteClientForm extends javax.swing.JInternalFrame {

    private IClientService iclientService;

    public JDeleteClientForm() {
        initComponents();
    }

    public JDeleteClientForm(IClientService iclientService) {
        initComponents();
        this.iclientService = iclientService;
        initTable();
        loadClients();
        UITheme.apply(this);
    }

    private void initTable() {
        tableClients.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Email", "Phone"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });

        tableClients.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableClients.getSelectedRow() != -1) {
                int row = tableClients.getSelectedRow();
                fieldSearchId.setText(tableClients.getValueAt(row, 0).toString());
            }
        });
    }

    private void loadClients() {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setRowCount(0);
        if (iclientService == null) return;
        List<Client> clients = iclientService.getAllClients();
        for (Client c : clients) {
            model.addRow(new Object[]{
                c.getId(), c.getName(), c.getEmail(), c.getPhoneNumber()
            });
        }
    }

    private void deleteClient() {
        String idStr = fieldSearchId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a client from the table first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this client?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(idStr);
                iclientService.deleteClient(id);
                loadClients();
                fieldSearchId.setText("");
                JOptionPane.showMessageDialog(this, "Client deleted successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        labelSearchId = new javax.swing.JLabel();
        fieldSearchId = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        scrollClients = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Delete Clients");
        setPreferredSize(new java.awt.Dimension(650, 400));

        panelTop.setBackground(new java.awt.Color(255, 204, 204));
        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Client"));
        panelTop.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        labelSearchId.setText("Selected ID:");
        panelTop.add(labelSearchId);

        fieldSearchId.setEditable(false);
        fieldSearchId.setColumns(10);
        panelTop.add(fieldSearchId);

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Client");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panelTop.add(btnDelete);

        getContentPane().add(panelTop, java.awt.BorderLayout.PAGE_START);

        scrollClients.setViewportView(tableClients);

        getContentPane().add(scrollClients, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        deleteClient();
    }

    private javax.swing.JButton btnDelete;
    private javax.swing.JTextField fieldSearchId;
    private javax.swing.JLabel labelSearchId;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scrollClients;
    private javax.swing.JTable tableClients;
}