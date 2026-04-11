package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.service.ClientService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author marco
 */
public class JEditClientForm extends javax.swing.JInternalFrame {

    private ClientService clientService;

    public JEditClientForm() {
        initComponents();
    }

    public JEditClientForm(ClientService clientService) {
        initComponents();
        this.clientService = clientService;
        initTable();
        loadClients();
    }

    private void initTable() {
        tableClients.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Last Name", "Email", "Phone"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });

        tableClients.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableClients.getSelectedRow() != -1) {
                int row = tableClients.getSelectedRow();
                fieldId.setText(tableClients.getValueAt(row, 0).toString());
                fieldName.setText(tableClients.getValueAt(row, 1).toString());
                fieldLastName.setText(tableClients.getValueAt(row, 2).toString());
                fieldEmail.setText(tableClients.getValueAt(row, 3).toString());
                fieldPhone.setText(tableClients.getValueAt(row, 4).toString());
            }
        });
    }

    private void loadClients() {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setRowCount(0);
        if (clientService == null) return;
        List<Client> clients = clientService.getAllClients();
        for (Client c : clients) {
            model.addRow(new Object[]{
                c.getId(), c.getName(), c.getLastName(), c.getEmail(), c.getPhoneNumber()
            });
        }
    }

    private void updateClient() {
        try {
            if (fieldId.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Seleccione un cliente de la tabla.");
            }
            int id = Integer.parseInt(fieldId.getText().trim());
            Client updatedClient = new Client(id, fieldName.getText().trim(), fieldLastName.getText().trim(), fieldEmail.getText().trim(), fieldPhone.getText().trim());
            clientService.editClient(id, updatedClient);
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
            loadClients();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        fieldId.setText("");
        fieldName.setText("");
        fieldLastName.setText("");
        fieldEmail.setText("");
        fieldPhone.setText("");
        tableClients.clearSelection();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        fieldId = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        fieldLastName = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        fieldEmail = new javax.swing.JTextField();
        labelPhone = new javax.swing.JLabel();
        fieldPhone = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        scrollClients = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();

        // Configuración de la ventana
        setClosable(true); // <--- ESTO PERMITE CERRAR EL FORMULARIO
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Edit Client");
        setPreferredSize(new java.awt.Dimension(700, 520));

        panelTop.setBackground(new java.awt.Color(102, 255, 102));
        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Client Data"));
        panelTop.setLayout(new java.awt.GridLayout(6, 2, 8, 8));

        labelId.setText("ID (Locked):");
        panelTop.add(labelId);
        fieldId.setEditable(false);
        fieldId.setBackground(new java.awt.Color(204, 204, 204));
        panelTop.add(fieldId);

        labelName.setText("Name:");
        panelTop.add(labelName);
        panelTop.add(fieldName);

        labelLastName.setText("Last Name:");
        panelTop.add(labelLastName);
        panelTop.add(fieldLastName);

        labelEmail.setText("Email:");
        panelTop.add(labelEmail);
        panelTop.add(fieldEmail);

        labelPhone.setText("Phone:");
        panelTop.add(labelPhone);
        panelTop.add(fieldPhone);

        btnUpdate.setBackground(new java.awt.Color(153, 153, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Client");
        btnUpdate.addActionListener(evt -> updateClient());
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
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldId;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldLastName;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scrollClients;
    private javax.swing.JTable tableClients;
}