/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.model;

/**
 *
 * @author Santy
 */
public class Product {
    private int productId;
    private String productName;
    private String productSize;
    private String productColor;
    private double productPrice;
    private int productStock;

    public Product(int productId, String productName, String productSize, String productColor, double productPrice, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productSize = productSize;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    
    
}
