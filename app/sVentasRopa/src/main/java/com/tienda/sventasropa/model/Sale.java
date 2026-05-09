package com.tienda.sventasropa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sale {
    private int id;
    private Client client;
    private final List<SaleDetail> details = new ArrayList<>();
    private LocalDateTime date;
    private double subtotal;

    public Sale(int id, Client client) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        }
        this.id = id;
        this.client = client;
        this.date = LocalDateTime.now();
        this.subtotal = 0;
    }

    // Constructor adicional para cargar desde persistencia
    public Sale(int id, Client client, LocalDateTime date) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        }
        this.id = id;
        this.client = client;
        this.date = date;
        this.subtotal = 0;
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser mayor a 0");
        }
        this.id = id;
    }

    public Client getClient() { return client; }
    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        this.client = client;
    }

    public void addDetail(SaleDetail detail) {
        if (detail == null) {
            throw new IllegalArgumentException("El detalle de venta no puede ser nulo");
        }
        details.add(detail);
        actualizarSubtotal();
    }

    public List<SaleDetail> getDetails() { return Collections.unmodifiableList(details); }

    public boolean hasDetails() { return !details.isEmpty(); }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return getSubtotal();
    }

    private void actualizarSubtotal() {
        subtotal = details.stream().mapToDouble(SaleDetail::getSubtotal).sum();
    }

    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return "Venta#" + id + " cliente=" + (client != null ? client.getName() : "-") + " total=$" + getTotal();
    }
}
