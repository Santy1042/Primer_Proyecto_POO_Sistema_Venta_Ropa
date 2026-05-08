package com.tienda.sventasropa.interfaces;

import java.util.List;

import com.tienda.sventasropa.model.Client;


public interface IClientPersistence {
    boolean saveClients(List<Client> clients);
    List<Client> loadClients();
}