/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.UI;

/**
 *
 * @author marco
 */

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

    public MainFrame() {
        ClientRepository clientRepository   = new ClientRepository();
        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository       = new SaleRepository();

        clientService  = new ClientService(clientRepository);
        productService = new ProductService(productRepository);
        saleService    = new SaleService(saleRepository, productRepository);

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

        JMenu menuModules = new JMenu("  Modules  ");
        menuModules.setForeground(Color.WHITE);
        menuModules.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem menuClients  = createMenuItem("  Clients");
        JMenuItem menuProducts = createMenuItem("  Products");
        JMenuItem menuSales    = createMenuItem("  Sales");

        menuClients.addActionListener(e  -> openClientForm());
        menuProducts.addActionListener(e -> openProductForm());
        menuSales.addActionListener(e    -> openSaleForm());

        menuModules.add(menuClients);
        menuModules.add(menuProducts);
        menuModules.addSeparator();
        menuModules.add(menuSales);
        menuBar.add(menuModules);

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

    private void openProductForm() {
        JProductForm frame = new JProductForm(productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void openSaleForm() {
        JSaleForm frame = new JSaleForm(saleService, clientService, productService);
        desktopPane.add(frame);
        frame.setVisible(true);
    }
}