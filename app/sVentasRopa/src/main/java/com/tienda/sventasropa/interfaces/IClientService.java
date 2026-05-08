package com.tienda.sventasropa.interfaces;

import java.util.List;

import java.util.List;

import com.tienda.sventasropa.model.Client;

public interface IClientService {

    void registerClient(Client cliente);

    void editClient(int id, Client datosNuevos);

    void deleteClient(int id);

    Client findClientById(int id);
<<<<<<< feature/Brigitte/customers

    List<Client> getAllClients();
}
=======
    List<Client> getAllClients();
>>>>>>> develop
}
