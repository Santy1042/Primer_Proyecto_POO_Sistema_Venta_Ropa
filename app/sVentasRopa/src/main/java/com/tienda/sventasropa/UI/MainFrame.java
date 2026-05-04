package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.json.ProductPersistence;
import com.tienda.sventasropa.json.SalePersistence;
import com.tienda.sventasropa.repository.ClientRepository;
import com.tienda.sventasropa.repository.ProductRepository;
import com.tienda.sventasropa.repository.SaleRepository;
import com.tienda.sventasropa.service.ClientService;
import com.tienda.sventasropa.service.ProductService;
import com.tienda.sventasropa.service.SaleService;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private ClientService clientService;
    private ProductService productService;
    private SaleService saleService;
    
    private ClientRepository clientRepo;
    private ProductRepository productRepo;

    public MainFrame() {
        ProductPersistence productPersistence = new ProductPersistence();
        SalePersistence salePersistence = new SalePersistence();

        clientRepo = new ClientRepository();
        productRepo = new ProductRepository(productPersistence);
        SaleRepository saleRepository = new SaleRepository(salePersistence);

        clientService = new ClientService(clientRepo);
        productService = new ProductService(productRepo);
        saleService = new SaleService(saleRepository, productRepo, clientService);

        initComponents();
    }

    private void initComponents() {
        setTitle("Clothing Store - Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(30, 30, 50));
        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 65));
        menuBar.setBorderPainted(false);

        JMenu menuClients = new JMenu("   Clients   ");
        menuClients.setForeground(Color.WHITE);
        menuClients.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuAddClient = createMenuItem("   Register Client");
        JMenuItem menuEditClient = createMenuItem("   Edit Client");
        JMenuItem menuDeleteClient = createMenuItem("   Delete Client");
        JMenuItem menuViewClients = createMenuItem("   View All Clients");

        menuAddClient.addActionListener(e -> openClientForm());
        menuEditClient.addActionListener(e -> openEditClientForm());
        menuDeleteClient.addActionListener(e -> openDeleteClientForm());
        menuViewClients.addActionListener(e -> openClientsTable());

        menuClients.add(menuAddClient);
        menuClients.add(menuEditClient);
        menuClients.add(menuDeleteClient);
        menuClients.addSeparator();
        menuClients.add(menuViewClients);
        menuBar.add(menuClients);

        JMenu menuProducts = new JMenu("   Products   ");
        menuProducts.setForeground(Color.WHITE);
        menuProducts.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuAddProduct = createMenuItem("   Add Product");
        JMenuItem menuEditProduct = createMenuItem("   Edit Product");
        JMenuItem menuDeleteProduct = createMenuItem("   Delete Product");
        JMenuItem menuViewProducts = createMenuItem("   View All Products");

        menuAddProduct.addActionListener(e -> openAddProductForm());
        menuEditProduct.addActionListener(e -> openEditProductForm());
        menuDeleteProduct.addActionListener(e -> openDeleteProductForm());
        menuViewProducts.addActionListener(e -> openProductsTable());

        menuProducts.add(menuAddProduct);
        menuProducts.add(menuEditProduct);
        menuProducts.add(menuDeleteProduct);
        menuProducts.addSeparator();
        menuProducts.add(menuViewProducts);
        menuBar.add(menuProducts);

        JMenu menuSales = new JMenu("   Sales   ");
        menuSales.setForeground(Color.WHITE);
        menuSales.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuCreateSale = createMenuItem("   Create New Sale");
        JMenuItem menuViewSales = createMenuItem("   View Sales History");

        menuCreateSale.addActionListener(e -> openCreateSaleForm());
        menuViewSales.addActionListener(e -> openSalesTable());
        
        menuSales.add(menuCreateSale);
        menuSales.addSeparator();
        menuSales.add(menuViewSales);
        menuBar.add(menuSales);

        JLabel titleLabel = new JLabel("   Clothing Store System   ");
        titleLabel.setForeground(new Color(180, 180, 220));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(titleLabel);
        menuBar.add(Box.createRigidArea(new Dimension(15, 0)));
        
        setJMenuBar(menuBar);
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(new Color(50, 50, 75));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.setBorderPainted(false);
        return item;
    }

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

    private void openClientsTable() {
        JClientTable frame = new JClientTable(clientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    
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

    private void openCreateSaleForm() {
        JCreateSale frame = new JCreateSale(saleService, clientRepo, productRepo);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openSalesTable() {
        JSalesTable frame = new JSalesTable(saleService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}