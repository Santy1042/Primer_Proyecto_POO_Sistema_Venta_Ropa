package com.tienda.sventasropa.interfaces;

import java.util.List;

import com.tienda.sventasropa.model.Client;

public interface IClientRepository {

    boolean save(Client cliente);

    boolean editClient(int id, Client datosNuevos);

    boolean deleteClient(int id);

    Client findClientById(int id);

    List<Client> getAllClients();
}