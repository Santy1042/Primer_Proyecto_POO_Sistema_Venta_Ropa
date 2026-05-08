package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.model.Product;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario para la creación de ventas.
 * @author Christopher / Marco
 */
public class JCreateSale extends javax.swing.JInternalFrame {

    private ISaleService saleService;
    private IClientRepository clientRepository;
    private IProductRepository productRepository;
    private Sale currentSale;
    private int nextSaleId = 0;

    public JCreateSale() {
        initComponents();
    }

    public JCreateSale(ISaleService saleService, IClientRepository clientRepository, IProductRepository productRepository) {
        this.saleService = saleService;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        
        initComponents();
        setupRenderers(); // Fix para las "direcciones de memoria"
        loadClients();
        loadProducts();
        updateTotalLabel();
        UITheme.apply(this);
    }

    /**
     * Configura cómo se ven los objetos dentro de los ComboBoxes
     */
    private void setupRenderers() {
        clientCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Client) {
                    Client c = (Client) value;
                    setText(c.getName() + " " + c.getLastName());
                }
                return this;
            }
        });

        productCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Product) {
                    Product p = (Product) value;
                    setText(p.getProductName() + " (Stock: " + p.getProductStock() + ")");
                }
                return this;
            }
        });
    }

    private void loadClients() {
        if (clientRepository == null) return;
        clientCombo.removeAllItems();
        List<Client> clients = clientRepository.getAllClients();
        for (Client client : clients) {
            clientCombo.addItem(client);
        }
    }

    private void loadProducts() {
        if (productRepository == null) return;
        productCombo.removeAllItems();
        List<Product> products = productRepository.getAllProducts();
        for (Product product : products) {
            productCombo.addItem(product);
        }
    }

    private void addProductToSale() {
        if (clientCombo.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente primero.");
            return;
        }

        if (currentSale == null) {
            Client selectedClient = (Client) clientCombo.getSelectedItem();
            currentSale = saleService.createSale(nextSaleId++, selectedClient);
            clientCombo.setEnabled(false); // Bloquear cliente una vez iniciada la venta
        }

        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            Product selectedProduct = (Product) productCombo.getSelectedItem();

            if (selectedProduct == null) return;
            if (quantity <= 0) throw new NumberFormatException();

            if (selectedProduct.getProductStock() < quantity) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.");
                return;
            }

            SaleDetail detail = new SaleDetail(selectedProduct.getProductId(), selectedProduct, quantity);
            currentSale.addDetail(detail);
            
            selectedProduct.setProductStock(selectedProduct.getProductStock() - quantity);

            DefaultTableModel model = (DefaultTableModel) saleDetailsTable.getModel();
            model.addRow(new Object[]{
                selectedProduct.getProductName(),
                quantity,
                String.format("$%.2f", selectedProduct.getProductPrice()),
                String.format("$%.2f", detail.getSubtotal())
            });

            updateTotalLabel();
            quantityField.setText("1");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void saveSale() {
        if (currentSale == null || !currentSale.hasDetails()) {
            JOptionPane.showMessageDialog(this, "Agregue productos a la venta.");
            return;
        }

        try {
            saleService.saveSale(currentSale);
            JOptionPane.showMessageDialog(this, "Venta #" + currentSale.getId() + " guardada.");
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void resetForm() {
        currentSale = null;
        clientCombo.setEnabled(true);
        ((DefaultTableModel) saleDetailsTable.getModel()).setRowCount(0);
        updateTotalLabel();
        loadProducts(); // Recargar para actualizar stock visualmente
    }

    private void updateTotalLabel() {
        double total = (currentSale != null) ? currentSale.getTotal() : 0.0;
        totalLabel.setText(String.format("Total a Pagar: $%.2f", total));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Inicialización de componentes (resumido para legibilidad)
        PanelSaleDetail = new javax.swing.JPanel();
        clientCombo = new javax.swing.JComboBox<>();
        productCombo = new javax.swing.JComboBox<>();
        quantityField = new javax.swing.JTextField();
        addProductBtn = new javax.swing.JButton();
        ScrollDetailsTable = new javax.swing.JScrollPane();
        saleDetailsTable = new javax.swing.JTable();
        totalLabel = new javax.swing.JLabel();
        saveSaleBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Nueva Venta");
        setPreferredSize(new java.awt.Dimension(600, 450));

        PanelSaleDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Venta"));

        // Layout y adición de componentes
        PanelSaleDetail.setLayout(new java.awt.GridLayout(4, 2, 10, 10));
        PanelSaleDetail.add(new JLabel("Cliente:"));
        PanelSaleDetail.add(clientCombo);
        PanelSaleDetail.add(new JLabel("Producto:"));
        PanelSaleDetail.add(productCombo);
        PanelSaleDetail.add(new JLabel("Cantidad:"));
        quantityField.setText("1");
        PanelSaleDetail.add(quantityField);
        
        addProductBtn.setText("Agregar a la Lista");
        addProductBtn.addActionListener(e -> addProductToSale());
        PanelSaleDetail.add(new JLabel(""));
        PanelSaleDetail.add(addProductBtn);

        saleDetailsTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Producto", "Cant.", "Precio", "Subtotal"}
        ));
        ScrollDetailsTable.setViewportView(saleDetailsTable);

        totalLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        saveSaleBtn.setBackground(new java.awt.Color(0, 153, 51));
        saveSaleBtn.setForeground(Color.WHITE);
        saveSaleBtn.setText("FINALIZAR VENTA");
        saveSaleBtn.addActionListener(e -> saveSale());

        // Layout principal
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));
        getContentPane().add(PanelSaleDetail, java.awt.BorderLayout.NORTH);
        getContentPane().add(ScrollDetailsTable, java.awt.BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new java.awt.BorderLayout());
        southPanel.add(totalLabel, java.awt.BorderLayout.NORTH);
        southPanel.add(saveSaleBtn, java.awt.BorderLayout.SOUTH);
        getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private javax.swing.JButton addProductBtn;
    private javax.swing.JComboBox<Object> clientCombo;
    private javax.swing.JPanel PanelSaleDetail;
    private javax.swing.JScrollPane ScrollDetailsTable;
    private javax.swing.JComboBox<Object> productCombo;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton saveSaleBtn;
    private javax.swing.JTable saleDetailsTable;
    private javax.swing.JLabel totalLabel;
}