/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.service;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.repository.ClientRepository;
<<<<<<< HEAD
=======
import com.tienda.sventasropa.interfaces.IClientRepository;
>>>>>>> e02e24e (Modulo De Productos Aprobado)
import java.util.List;

/**
 *
 * @author Santy
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
