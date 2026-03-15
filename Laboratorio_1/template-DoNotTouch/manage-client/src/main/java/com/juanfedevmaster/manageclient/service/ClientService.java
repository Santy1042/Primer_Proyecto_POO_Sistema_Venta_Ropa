/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanfedevmaster.manageclient.service;

import com.juanfedevmaster.manageclient.model.Client;
import com.juanfedevmaster.manageclient.repository.ClientRepository;
import com.juanfedevmaster.manageclient.repository.IClientRepository;
import java.util.List;

/**
 *
 * @author juanfe
 */
public class ClientService {
    private final IClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void registrarCliente(int id, String name, String email, String phoneNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        Client client = new Client(id, name, email, phoneNumber);
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}
