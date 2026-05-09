package com.tienda.sventasropa.UI;

import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Sale;
import com.tienda.sventasropa.model.SaleDetail;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;

public class JSalesTable extends javax.swing.JInternalFrame {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private ISaleService isalesService;

    public JSalesTable() {
        initComponents();
        initSalesTable();
        loadSalesFromService();
        UITheme.apply(this); // Aplicando el tema oscuro
    }

    public JSalesTable(ISaleService salesService) {
        this.isalesService = salesService;
        initComponents();
        initSalesTable();
        loadSalesFromService();
        UITheme.apply(this); // Aplicando el tema oscuro
    }

    public void setIsalesService(ISaleService salesService) {
        if (salesService == null) {
            throw new IllegalArgumentException("El servicio de ventas no puede ser nulo");
        }
        this.isalesService = salesService;
    }

    public void loadSales(Object[][] sales) {
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        model.setRowCount(0);
        if (sales == null || sales.length == 0) {
            return;
        }
        for (Object[] row : sales) {
            model.addRow(row);
        }
    }

    public void loadSalesFromService() {
        try {
            if (isalesService == null) {
                return;
            }
            List<Sale> sales = isalesService.getAllSales();
            
            DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
            model.setRowCount(0);

            if (sales != null && !sales.isEmpty()) {
                for (Sale sale : sales) {
                    model.addRow(saleToRow(sale));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private Object[] saleToRow(Sale sale) {
        try {
            if (sale == null || sale.getDetails() == null || sale.getDetails().isEmpty()) {
                return new Object[]{
                    sale != null ? sale.getId() : "N/A",
                    sale != null && sale.getClient() != null ? sale.getClient().getName() : "N/A",
                    "N/A",
                    0,
                    "$0.00",
                    sale != null && sale.getDate() != null ? sale.getDate().format(DATE_FORMATTER) : "N/A"
                };
            }

            String products = sale.getDetails().stream()
                    .map(detail -> {
                        String productName = detail.getProduct() != null ? detail.getProduct().getProductName() : "Desconocido";
                        return productName + " x" + detail.getQuantity();
                    })
                    .collect(Collectors.joining(", "));
            
            int totalQuantity = sale.getDetails().stream()
                    .mapToInt(SaleDetail::getQuantity)
                    .sum();
            
            String clientName = sale.getClient() != null ? 
                    sale.getClient().getName() + " " + (sale.getClient().getLastName() != null ? sale.getClient().getLastName() : "") :
                    "N/A";
            
            return new Object[]{
                sale.getId(),
                clientName.trim(),
                products.isEmpty() ? "N/A" : products,
                totalQuantity,
                String.format("$%.2f", sale.getTotal()),
                sale.getDate().format(DATE_FORMATTER)
            };
        } catch (Exception e) {
            return new Object[]{"ERROR", "ERROR", e.getMessage(), 0, "$0.00", "ERROR"};
        }
    }

    public void refreshTable() {
        loadSalesFromService();
    }

    private void initSalesTable() {
        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Venta ID", "Cliente", "Productos", "Cantidad Total", "Total", "Fecha"}
        ) {
            Class<?>[] types = new Class[]{Integer.class, String.class, String.class, Integer.class, String.class, String.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        
        TableColumn idColumn = salesTable.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(60);
        
        TableColumn clientColumn = salesTable.getColumnModel().getColumn(1);
        clientColumn.setPreferredWidth(120);
        
        TableColumn productColumn = salesTable.getColumnModel().getColumn(2);
        productColumn.setPreferredWidth(200);
        
        TableColumn quantityColumn = salesTable.getColumnModel().getColumn(3);
        quantityColumn.setPreferredWidth(80);
        
        TableColumn totalColumn = salesTable.getColumnModel().getColumn(4);
        totalColumn.setPreferredWidth(80);
        
        TableColumn dateColumn = salesTable.getColumnModel().getColumn(5);
        dateColumn.setPreferredWidth(140);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanelShowSales = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial de Ventas");
        setPreferredSize(new java.awt.Dimension(800, 400));

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Venta ID", "Cliente", "Productos", "Cantidad Total", "Total", "Fecha"}
        ));
        salesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jPanelShowSales.setViewportView(salesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowSales, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShowSales, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }

    private javax.swing.JScrollPane jPanelShowSales;
    private javax.swing.JTable salesTable;
}