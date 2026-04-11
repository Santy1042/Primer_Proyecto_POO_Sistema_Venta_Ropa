/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.service;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.repository.ClientRepository;
import java.util.List;
import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;

/**
 *
 * @author Santy
 */
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerClient(Client client) {
        // Validaciones
        if (client == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        if (findClientById(client.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con esa cédula.");
        }
        
        // Guardar cliente
        boolean guardado = clientRepository.save(client);
        if (!guardado) {
            throw new RuntimeException("No se pudo guardar el cliente.");
        }
    }

    @Override
    public void editClient(int id, Client datosNuevos) {
        // Validaciones
        if (datosNuevos == null) {
            throw new IllegalArgumentException("Los datos no pueden ser nulos.");
        }
        
        Client encontrado = findClientById(id);
        if (encontrado == null) {
            throw new IllegalArgumentException("No se encontró el cliente con ID: " + id);
        }
        
        if (datosNuevos.getName() == null || datosNuevos.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (datosNuevos.getLastName() == null || datosNuevos.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (datosNuevos.getEmail() == null || datosNuevos.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        
        // Editar cliente
        clientRepository.editClient(id, datosNuevos);
    }

    @Override
    public void deleteClient(int id) {
        Client encontrado = findClientById(id);
        if (encontrado == null) {
            throw new IllegalArgumentException("No se encontró el cliente con ID: " + id);
        }
        
        clientRepository.deleteClient(id);
    }

    @Override
    public Client findClientById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID del cliente no puede ser negativo.");
        }
        return clientRepository.findClientById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}