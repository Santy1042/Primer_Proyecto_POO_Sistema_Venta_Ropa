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
 * @author marco / Equipo unificado
 */
public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private ClientService clientService;
    private ProductService productService;
    private SaleService saleService;

    public MainFrame() {
        // Inicializar repositorios (Datos)
        ClientRepository clientRepository   = new ClientRepository();
        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository       = new SaleRepository();

        // Inicializar servicios (Lógica)
        clientService  = new ClientService(clientRepository);
        productService = new ProductService(productRepository);
        saleService    = new SaleService(saleRepository, productRepository);

        initComponents();
    }

    private void initComponents() {
        setTitle("Clothing Store - Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        // Configurar DesktopPane (Fondo)
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(30, 30, 50));
        setContentPane(desktopPane);

        // Configurar Barra de Menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 65));
        menuBar.setBorderPainted(false);

        // ── MENÚ CLIENTES ──
        JMenu menuClients = new JMenu("  Clients  ");
        menuClients.setForeground(Color.WHITE);
        menuClients.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuAddClient    = createMenuItem("  Register Client");
        JMenuItem menuEditClient   = createMenuItem("  Edit Client");
        JMenuItem menuDeleteClient = createMenuItem("  Delete Client");

        menuAddClient.addActionListener(e    -> openClientForm());
        menuEditClient.addActionListener(e   -> openEditClientForm());
        menuDeleteClient.addActionListener(e -> openDeleteClientForm());

        menuClients.add(menuAddClient);
        menuClients.add(menuEditClient);
        menuClients.add(menuDeleteClient);
        menuBar.add(menuClients);

        // ── MENÚ PRODUCTOS (Unificado con lo anterior) ──
        JMenu menuProducts = new JMenu("  Products  ");
        menuProducts.setForeground(Color.WHITE);
        menuProducts.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuAddProduct    = createMenuItem("  Add Product");
        JMenuItem menuEditProduct   = createMenuItem("  Edit Product");
        JMenuItem menuDeleteProduct = createMenuItem("  Delete Product");
        JMenuItem menuViewProducts  = createMenuItem("  View All Products");

        menuAddProduct.addActionListener(e    -> openAddProductForm());
        menuEditProduct.addActionListener(e   -> openEditProductForm());
        menuDeleteProduct.addActionListener(e -> openDeleteProductForm());
        menuViewProducts.addActionListener(e  -> openProductsTable());

        menuProducts.add(menuAddProduct);
        menuProducts.add(menuEditProduct);
        menuProducts.add(menuDeleteProduct);
        menuProducts.addSeparator(); // Separador visual
        menuProducts.add(menuViewProducts);
        menuBar.add(menuProducts);

        // ── MENÚ VENTAS ──
        JMenu menuSales = new JMenu("  Sales  ");
        menuSales.setForeground(Color.WHITE);
        menuSales.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // ── TÍTULO DE LA APLICACIÓN AL FINAL ──
        JLabel titleLabel = new JLabel("   Clothing Store System   ");
        titleLabel.setForeground(new Color(180, 180, 220));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menuBar.add(Box.createHorizontalGlue()); // Empuja el título a la derecha
        menuBar.add(titleLabel);
        menuBar.add(Box.createRigidArea(new Dimension(15, 0))); // Margen derecho
        
        setJMenuBar(menuBar);
    }

    // Método auxiliar para estilizar los items del menú
    private JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(new Color(50, 50, 75));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.setBorderPainted(false);
        return item;
    }

    // ==========================================
    // MÉTODOS PARA ABRIR FORMULARIOS CLIENTES
    // ==========================================
    private void openClientForm() {
        JAddClientForm frame = new JAddClientForm(clientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openEditClientForm() {
        JEditClientForm frame = new JEditClientForm(clientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openDeleteClientForm() {
        JDeleteClientForm frame = new JDeleteClientForm(clientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    
    // ==========================================
    // MÉTODOS PARA ABRIR FORMULARIOS PRODUCTOS
    // ==========================================
    private void openAddProductForm() {
        JAddProductForm frame = new JAddProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openEditProductForm() {
        JEditProductForm frame = new JEditProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openDeleteProductForm() {
        JDeleteProductForm frame = new JDeleteProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openProductsTable() {
        JProductsTable frame = new JProductsTable(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    // ==========================================
    // MÉTODOS PARA ABRIR FORMULARIOS VENTAS
    // ==========================================

    // ==========================================
    // MÉTODO MAIN
    // ==========================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}