package com.tienda.sventasropa.repository;

import java.util.ArrayList;
import java.util.List;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.model.Client;

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
    public boolean editClient(int id, Client datosNuevos) {

        for (Client client : clients) {

            if (client.getId() == id) {

                client.setName(datosNuevos.getName());
                client.setLastName(datosNuevos.getLastName());
                client.setEmail(datosNuevos.getEmail());
                client.setPhoneNumber(datosNuevos.getPhoneNumber());

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteClient(int id) {

        return clients.removeIf(client -> client.getId() == id);
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