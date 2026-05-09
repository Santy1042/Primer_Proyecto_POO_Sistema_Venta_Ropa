package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.interfaces.IProductRepository;
import com.tienda.sventasropa.interfaces.IProductService;
import com.tienda.sventasropa.interfaces.ISaleRepository;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.json.ClientPersistence;
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
    private IClientService iclientService;
    private IProductService iproductService;
    private ISaleService iSaleService;
    
    private IClientRepository iclientRepo;
    private IProductRepository iproductRepo;
    private ISaleRepository isaleRepository;
    
    public MainFrame() {
        ClientPersistence clientPersistence = new ClientPersistence();
        ProductPersistence productPersistence = new ProductPersistence();
        SalePersistence salePersistence = new SalePersistence();
      
        iclientRepo = new ClientRepository(clientPersistence);
        iproductRepo = new ProductRepository(productPersistence);
        isaleRepository = new SaleRepository(salePersistence);

        iclientService = new ClientService(iclientRepo);
        iproductService = new ProductService(iproductRepo);
        iSaleService = new SaleService(isaleRepository, iproductRepo, iclientService);

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
        JAddClientForm frame = new JAddClientForm(iclientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openEditClientForm() {
        JEditClientForm frame = new JEditClientForm(iclientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openDeleteClientForm() {
        JDeleteClientForm frame = new JDeleteClientForm(iclientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openClientsTable() {
        JClientTable frame = new JClientTable(iclientService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    
    private void openAddProductForm() {
        JAddProductForm frame = new JAddProductForm(iproductService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openEditProductForm() {
        JEditProductForm frame = new JEditProductForm(iproductService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openDeleteProductForm() {
        JDeleteProductForm frame = new JDeleteProductForm(iproductService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openProductsTable() {
        JProductsTable frame = new JProductsTable(iproductService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openCreateSaleForm() {
        JCreateSale frame = new JCreateSale(iSaleService, iclientRepo, iproductRepo);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openSalesTable() {
        JSalesTable frame = new JSalesTable(iSaleService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}