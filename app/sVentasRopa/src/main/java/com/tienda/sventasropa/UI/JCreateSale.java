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

    public JCreateSale() {
        initComponents();
    }

    public JCreateSale(ISaleService saleService, IClientRepository clientRepository, IProductRepository productRepository) {
        this.saleService = saleService;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        
        initComponents();
        setupRenderers();
        loadClients();
        loadProducts();
        updateReceipt(); // Inicializa el área de texto
        UITheme.apply(this);
    }

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
                    setText(p.getProductName() + " (Stock: " + p.getProductStock() + ") - $" + p.getProductPrice());
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
            JOptionPane.showMessageDialog(this, "Seleccione un cliente primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (currentSale == null) {
            Client selectedClient = (Client) clientCombo.getSelectedItem();
            
            int nextId = saleService.getNextSaleId(); 
            
            currentSale = saleService.createSale(nextId, selectedClient);
            clientCombo.setEnabled(false);
        }

        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            Product selectedProduct = (Product) productCombo.getSelectedItem();

            if (selectedProduct == null) return;
            if (quantity <= 0) throw new NumberFormatException();

            if (selectedProduct.getProductStock() < quantity) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.", "Error de Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SaleDetail detail = new SaleDetail(selectedProduct.getProductId(), selectedProduct, quantity);
            
            // Si modificaste Sale.java para agrupar, la línea de abajo hará la magia automáticamente.
            currentSale.addDetail(detail);
            
            // Restar stock visualmente (debería delegarse al servicio/repo al finalizar, pero lo dejamos como tu lógica original)
            selectedProduct.setProductStock(selectedProduct.getProductStock() - quantity);

            updateReceipt();
            quantityField.setText("1");
            
            // Refrescar combo para ver el stock actualizado
            productCombo.repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveSale() {
        if (currentSale == null || !currentSale.hasDetails()) {
            JOptionPane.showMessageDialog(this, "Agregue productos a la venta antes de finalizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            saleService.saveSale(currentSale);
            JOptionPane.showMessageDialog(this, "Venta #" + currentSale.getId() + " guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        currentSale = null;
        clientCombo.setEnabled(true);
        updateReceipt();
        loadProducts(); // Recargar para restaurar stock en caso de error o refrescar BD
    }

    // --- NUEVO MÉTODO PARA DIBUJAR EL TICKET ---
    private void updateReceipt() {
        if (currentSale == null || !currentSale.hasDetails()) {
            areaReceipt.setText("Aún no hay productos en la venta actual...");
            totalLabel.setText("Total a Pagar: $0.00");
            return;
        }

        StringBuilder receipt = new StringBuilder();
        receipt.append("==================================================\n");
        receipt.append("                  TICKET DE VENTA                 \n");
        receipt.append("==================================================\n");
        receipt.append("CLIENTE: ").append(currentSale.getClient().getName()).append(" ").append(currentSale.getClient().getLastName()).append("\n");
        receipt.append("FECHA:   ").append(currentSale.getDate().toString()).append("\n");
        receipt.append("--------------------------------------------------\n");
        receipt.append("ARTÍCULOS:\n\n");

        for (SaleDetail detail : currentSale.getDetails()) {
            receipt.append(" • ").append(detail.toString()).append("\n");
        }

        receipt.append("\n--------------------------------------------------\n");
        receipt.append(String.format("SUBTOTAL: $%.2f\n", currentSale.getSubtotal()));
        receipt.append("==================================================\n");

        areaReceipt.setText(receipt.toString());
        totalLabel.setText(String.format("Total a Pagar: $%.2f", currentSale.getTotal()));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        PanelSaleDetail = new javax.swing.JPanel();
        clientCombo = new javax.swing.JComboBox<>();
        productCombo = new javax.swing.JComboBox<>();
        quantityField = new javax.swing.JTextField();
        addProductBtn = new javax.swing.JButton();
        ScrollReceipt = new javax.swing.JScrollPane();
        areaReceipt = new javax.swing.JTextArea();
        southPanel = new javax.swing.JPanel();
        totalLabel = new javax.swing.JLabel();
        saveSaleBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Nueva Venta");
        setPreferredSize(new java.awt.Dimension(650, 500));

        PanelSaleDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Venta"));
        PanelSaleDetail.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        PanelSaleDetail.add(new JLabel("Cliente Seleccionado:"));
        PanelSaleDetail.add(clientCombo);
        
        PanelSaleDetail.add(new JLabel("Producto a Agregar:"));
        PanelSaleDetail.add(productCombo);
        
        PanelSaleDetail.add(new JLabel("Cantidad:"));
        quantityField.setText("1");
        PanelSaleDetail.add(quantityField);
        
        PanelSaleDetail.add(new JLabel("")); // Espacio vacío
        addProductBtn.setBackground(new java.awt.Color(51, 153, 255));
        addProductBtn.setForeground(java.awt.Color.WHITE);
        addProductBtn.setText("Agregar al Ticket");
        addProductBtn.addActionListener(e -> addProductToSale());
        PanelSaleDetail.add(addProductBtn);

        // Configuración del JTextArea
        areaReceipt.setEditable(false);
        areaReceipt.setColumns(20);
        areaReceipt.setRows(10);
        areaReceipt.setFont(new java.awt.Font("Monospaced", 0, 14));
        areaReceipt.setBackground(new java.awt.Color(250, 250, 250));
        ScrollReceipt.setViewportView(areaReceipt);
        ScrollReceipt.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de Compra"));

        totalLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        saveSaleBtn.setBackground(new java.awt.Color(0, 153, 51));
        saveSaleBtn.setForeground(java.awt.Color.WHITE);
        saveSaleBtn.setFont(new java.awt.Font("Segoe UI", 1, 14));
        saveSaleBtn.setText("FINALIZAR VENTA");
        saveSaleBtn.setPreferredSize(new java.awt.Dimension(200, 40));
        saveSaleBtn.addActionListener(e -> saveSale());

        southPanel.setLayout(new java.awt.BorderLayout(0, 10));
        southPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(totalLabel, java.awt.BorderLayout.NORTH);
        southPanel.add(saveSaleBtn, java.awt.BorderLayout.SOUTH);

        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));
        getContentPane().add(PanelSaleDetail, java.awt.BorderLayout.NORTH);
        getContentPane().add(ScrollReceipt, java.awt.BorderLayout.CENTER);
        getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private javax.swing.JButton addProductBtn;
    private javax.swing.JComboBox<Object> clientCombo;
    private javax.swing.JPanel PanelSaleDetail;
    private javax.swing.JScrollPane ScrollReceipt;
    private javax.swing.JComboBox<Object> productCombo;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton saveSaleBtn;
    private javax.swing.JTextArea areaReceipt;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JPanel southPanel;
}