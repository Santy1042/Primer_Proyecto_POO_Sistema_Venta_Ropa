package com.tienda.sventasropa.UI;
import com.tienda.sventasropa.interfaces.ISaleService;
import com.tienda.sventasropa.model.Sale;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.time.format.DateTimeFormatter;


public class JDeleteSale extends javax.swing.JInternalFrame{
    private final ISaleService iSaleService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor NetBeans
     */
    public JDeleteSale() {
        this.iSaleService = null;
        initComponents();
        setSize(600, 450);
        setMinimumSize(new Dimension(600, 450));
        setResizable(true);
    }

    /**
     * Constructor para MainFrame
     */
    public JDeleteSale(ISaleService saleService) {
        this.iSaleService = saleService;
        initComponents();
        UITheme.apply(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblClient = new javax.swing.JLabel();
        txtClient = new javax.swing.JTextField();
        lblProducts = new javax.swing.JLabel();
        txtProducts = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Delete Sale");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Delete Existing Sale");

        lblId.setText("Sale ID:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblClient.setText("Client:");

        txtClient.setEditable(false);

        lblProducts.setText("Products:");

        txtProducts.setEditable(false);

        lblQuantity.setText("Quantity:");

        txtQuantity.setEditable(false);

        lblTotal.setText("Total:");

        txtTotal.setEditable(false);

        lblDate.setText("Date:");

        txtDate.setEditable(false);

        btnDelete.setText("Delete Sale");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(txtProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, Short.MAX_VALUE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClient)
                    .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducts)
                    .addComponent(txtProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            if (iSaleService != null) {
                var saleOpt = iSaleService.findSale(id);
                if (saleOpt.isPresent()) {
                    Sale sale = saleOpt.get();
                    
                    String clientName = sale.getClient() != null ? 
                            sale.getClient().getName() + " " + (sale.getClient().getLastName() != null ? sale.getClient().getLastName() : "") :
                            "N/A";
                    txtClient.setText(clientName.trim());
                    
                   
                    String products = sale.getDetails().stream()
                            .map(detail -> {
                                String productName = detail.getProduct() != null ? detail.getProduct().getProductName() : "Desconocido";
                                return productName + " x" + detail.getQuantity();
                            })
                            .collect(java.util.stream.Collectors.joining(", "));
                    txtProducts.setText(products.isEmpty() ? "N/A" : products);
                    
               
                    int totalQuantity = sale.getDetails().stream()
                            .mapToInt(com.tienda.sventasropa.model.SaleDetail::getQuantity)
                            .sum();
                    txtQuantity.setText(String.valueOf(totalQuantity));
                    
                
                    txtTotal.setText(String.format("$%.2f", sale.getTotal()));
                    
                
                    txtDate.setText(sale.getDate().format(DATE_FORMATTER));
                    
                  
                    txtId.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró una venta con el ID especificado.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
                    btnClearActionPerformed(null);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID numérico válido para buscar.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
      
        if (txtClient.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor busque una venta antes de eliminarla.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            
            
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta venta?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
    
                if (iSaleService != null) {
                    iSaleService.deleteSale(id);
                    JOptionPane.showMessageDialog(this, "Venta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    btnClearActionPerformed(null); //Elimina el campo
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtId.setText("");
        txtId.setEditable(true); // Desbloqueamos el ID
        txtClient.setText("");
        txtProducts.setText("");
        txtQuantity.setText("");
        txtTotal.setText("");
        txtDate.setText("");
        txtId.requestFocus();
    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblProducts;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtClient;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtProducts;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}