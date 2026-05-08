/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.repository;

import java.util.ArrayList;
import java.util.List;
import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.model.Client;

/**
 * Implementación del repositorio de clientes
 * Gestiona la persistencia de datos de clientes en memoria
 */
public class ClientRepository implements IClientRepository {
    private final List<Client> clients;
    
    public ClientRepository() {
        this.clients = new ArrayList<>();
    }
    
    @Override
    public boolean save(Client cliente) {
        if (cliente == null) {
            return false;
        }
        return clients.add(cliente);
    }

    @Override
    public void editClient(int id, Client datosNuevos) {
        for (Client client : clients) {
            if (client.getId() == id) {
                client.setName(datosNuevos.getName());
                client.setLastName(datosNuevos.getLastName());
                client.setEmail(datosNuevos.getEmail());
                client.setPhoneNumber(datosNuevos.getPhoneNumber());
                return;
            }
        }
    }

    @Override
    public void deleteClient(int id) {
        clients.removeIf(client -> client.getId() == id);
    }

    @Override
    public Client findClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
    
    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }
}