/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.model;

/**
 * Detalle de línea para una venta.
 */
public class SaleDetail {
    private int productId;
    private int quantity;
    private Product product;

    public SaleDetail(int productId, Product product, int quantity) {
        if (productId <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor a 0");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        this.productId = productId;
        this.product = product;
        this.quantity = quantity;
    }

    public int getProductId() { return productId; }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public double getUnitPrice() {
        if (product == null) {
            throw new IllegalStateException("El producto no está asignado");
        }
        return product.getProductPrice();
    }

    public double getSubtotal() { return getUnitPrice() * quantity; }

    @Override
    public String toString() {
        String productName = product != null ? product.getProductName() : "Desconocido";
        return productName + " x" + quantity + " = $" + getSubtotal();
    }
}
