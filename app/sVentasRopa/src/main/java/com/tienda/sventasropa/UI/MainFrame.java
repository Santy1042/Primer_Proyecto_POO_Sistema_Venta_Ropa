package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.repository.ClientRepository;
import com.tienda.sventasropa.repository.ProductRepository;
import com.tienda.sventasropa.repository.SaleRepository;
import com.tienda.sventasropa.service.ClientService;
import com.tienda.sventasropa.service.ProductService;
import com.tienda.sventasropa.service.SaleService;
import javax.swing.*;
import java.awt.*;

/**
 * @author marco
 */
public class MainFrame extends javax.swing.JFrame {

    private final ClientService clientService;
    private final ProductService productService;
    private final SaleService saleService;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        // Inicializar repositorios
        ClientRepository clientRepository   = new ClientRepository();
        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository       = new SaleRepository();

        // Inicializar servicios
        clientService  = new ClientService(clientRepository);
        productService = new ProductService(productRepository);
        saleService    = new SaleService(saleRepository, productRepository);
        
        initComponents();
        
        // ---------------------------------------------------------
        // CONEXIÓN DE LOS MENÚS (Seguro contra sobreescrituras de NetBeans)
        // ---------------------------------------------------------
        editProductMenuItem.addActionListener(this::openEditProductForm);
        deleteProductMenuItem.addActionListener(this::openDeleteProductForm);
        // (El de AddProduct y ViewAll ya estaban conectados en tu initComponents)
        
        // Personalización visual
        setTitle("Clothing Store - Management System");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        desktopPane.setBackground(new Color(30, 30, 50));
        
        // Personalizar menú bar
        menuBar.setBackground(new Color(40, 40, 65));
        menuBar.setBorderPainted(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        clientsMenu = new javax.swing.JMenu();
        registerClientMenuItem = new javax.swing.JMenuItem();
        viewAllClientsMenuItem = new javax.swing.JMenuItem();
        productsMenu = new javax.swing.JMenu();
        addProductMenuItem = new javax.swing.JMenuItem();
        viewAllProductsMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        editProductMenuItem = new javax.swing.JMenuItem();
        deleteProductMenuItem = new javax.swing.JMenuItem();
        salesMenu = new javax.swing.JMenu();
        createSaleMenuItem = new javax.swing.JMenuItem();
        viewAllSalesMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientsMenu.setText("Clients");

        registerClientMenuItem.setText("Register Client");
        clientsMenu.add(registerClientMenuItem);

        viewAllClientsMenuItem.setText("View All Clients");
        clientsMenu.add(viewAllClientsMenuItem);

        menuBar.add(clientsMenu);

        productsMenu.setText("Products");

        addProductMenuItem.setText("Add Product");
        addProductMenuItem.addActionListener(this::openProductForm);
        productsMenu.add(addProductMenuItem);

        viewAllProductsMenuItem.setText("View All Products");
        viewAllProductsMenuItem.addActionListener(this::openProductsTable);
        productsMenu.add(viewAllProductsMenuItem);
        productsMenu.add(jSeparator1);

        editProductMenuItem.setText("Edit Product");
        productsMenu.add(editProductMenuItem);

        deleteProductMenuItem.setText("Delete Product");
        productsMenu.add(deleteProductMenuItem);

        menuBar.add(productsMenu);

        salesMenu.setText("Sales");

        createSaleMenuItem.setText("Create Sale");
        salesMenu.add(createSaleMenuItem);

        viewAllSalesMenuItem.setText("View All Sales");
        salesMenu.add(viewAllSalesMenuItem);

        menuBar.add(salesMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    // ---------------------------------------------------------
    // MÉTODOS PARA ABRIR LAS VENTANAS INTERNAS (JInternalFrames)
    // ---------------------------------------------------------

    // 1. Abrir formulario de Agregar
    private void openProductForm(java.awt.event.ActionEvent evt) {
        JAddProductForm frame = new JAddProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    
    // 2. Abrir formulario de Editar
    private void openEditProductForm(java.awt.event.ActionEvent evt) {
        JEditProductForm frame = new JEditProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    // 3. Abrir formulario de Eliminar
    private void openDeleteProductForm(java.awt.event.ActionEvent evt) {
        JDeleteProductForm frame = new JDeleteProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    // 4. Abrir la tabla de productos (Asumo que ya tienes esta clase creada)
    private void openProductsTable(java.awt.event.ActionEvent evt) {
        // Asegúrate de que JProductsTable herede de JInternalFrame al igual que los otros formularios
        JProductsTable frame = new JProductsTable(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addProductMenuItem;
    private javax.swing.JMenu clientsMenu;
    private javax.swing.JMenuItem createSaleMenuItem;
    private javax.swing.JMenuItem deleteProductMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editProductMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu productsMenu;
    private javax.swing.JMenuItem registerClientMenuItem;
    private javax.swing.JMenu salesMenu;
    private javax.swing.JMenuItem viewAllClientsMenuItem;
    private javax.swing.JMenuItem viewAllProductsMenuItem;
    private javax.swing.JMenuItem viewAllSalesMenuItem;
    // End of variables declaration//GEN-END:variables
}