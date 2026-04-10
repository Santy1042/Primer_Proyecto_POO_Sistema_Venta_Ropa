/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sale aggregate: holds client and line items.
 */
public class Sale {
    private int id;
    private Client client;
    private final List<SaleDetail> details = new ArrayList<>();
    private final LocalDateTime date = LocalDateTime.now();

    public Sale() {}

    public Sale(int id, Client client) {
        this.id = id;
        this.client = client;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public void addDetail(SaleDetail detail) {
        details.add(detail);
    }

    public List<SaleDetail> getDetails() { return Collections.unmodifiableList(details); }

    public boolean hasDetails() { return !details.isEmpty(); }

    public double getTotal() {
        return details.stream().mapToDouble(SaleDetail::getSubtotal).sum();
    }

    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return "Sale#" + id + " client=" + (client != null ? client.getName() : "-") + " total=$" + getTotal();
    }
}
