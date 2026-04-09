/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.model;

/**
 * Line item for a sale.
 */
public class SaleDetail {
    private Product product;
    private int quantity;
    private double unitPrice;

    public SaleDetail() {}

    public SaleDetail(Product product, int quantity, double unitPrice) {
        if (product == null) throw new IllegalArgumentException("product required");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        if (unitPrice < 0) throw new IllegalArgumentException("unit price must be >= 0");
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        this.quantity = quantity;
    }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getSubtotal() { return unitPrice * quantity; }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = $" + getSubtotal();
    }
}
