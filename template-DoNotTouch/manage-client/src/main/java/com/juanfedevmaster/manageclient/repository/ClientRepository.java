/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanfedevmaster.manageclient.repository;

import com.juanfedevmaster.manageclient.model.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanfe
 */
public class ClientRepository implements IClientRepository{
    
    private final List<Client> clients;
    
    public ClientRepository() {
        this.clients = new ArrayList<>();
    }
    
    @Override
    public void save(Client cliente) {
        clients.add(cliente);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }
    
}
