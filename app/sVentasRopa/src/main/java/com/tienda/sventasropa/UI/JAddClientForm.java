package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 * @author marco
 */
public class JAddClientForm extends javax.swing.JInternalFrame {

    private IClientService iclientService;

    public JAddClientForm() {
        initComponents();
    }

    public JAddClientForm(IClientService iclientService) {
        initComponents();
        this.iclientService = iclientService;
        initTable();
        loadClients();
        UITheme.apply(this); 
    }

    private void initTable() {
        tableClients.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Last Name", "Email", "Phone"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        });
    }

    private void loadClients() {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setRowCount(0);
        if (iclientService == null) return;
        
        for (Client cliente : iclientService.getAllClients()) {
            model.addRow(new Object[]{
                cliente.getId(), 
                cliente.getName(), 
                cliente.getLastName(), 
                cliente.getEmail(), 
                cliente.getPhoneNumber()
            });
        }
    }

    private void saveClient() {
        try {
            int id = Integer.parseInt(fieldId.getText().trim());
            String name = fieldName.getText().trim();
            String lastName = fieldLastName.getText().trim();
            String email = fieldEmail.getText().trim();
            String phone = fieldPhone.getText().trim();

            if (name.isEmpty() || lastName.isEmpty()) {
                throw new Exception("Name and Last Name are required.");
            }

            Client newClient = new Client(id, name, lastName, email, phone);
            iclientService.registerClient(newClient);

            loadClients();
            clearFields();
            JOptionPane.showMessageDialog(this, "Client saved successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
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
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        scrollClients = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Client Management");
        setPreferredSize(new java.awt.Dimension(800, 500));

        // Ajustado a 6 filas para incluir Last Name
        panelTop.setBackground(new java.awt.Color(0, 204, 204));
        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder("Client Data"));
        panelTop.setLayout(new java.awt.GridLayout(6, 2, 8, 8));

        labelId.setText("ID:");
        panelTop.add(labelId);
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

        btnSave.setBackground(new java.awt.Color(102, 102, 255));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save Client");
        btnSave.addActionListener(evt -> saveClient());
        panelTop.add(btnSave);

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

    // Variables declaration
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
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