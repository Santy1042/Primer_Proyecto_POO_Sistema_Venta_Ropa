/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.service;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.repository.ClientRepository;
import java.util.List;

/**
 * Servicio de Clientes: lógica de negocio para gestión de clientes
 * @author Santy
 */
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        if (clientRepository == null) {
            throw new IllegalArgumentException("El repositorio de clientes no puede ser nulo");
        }
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerClient(Client cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (cliente.getName() == null || cliente.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        clientRepository.save(cliente);
    }

    @Override
    public void editClient(int id, Client datosNuevos) {
        if (datosNuevos == null) {
            throw new IllegalArgumentException("Los datos del cliente no pueden ser nulos");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }
        Client existing = clientRepository.findClientById(id);
        if (existing == null) {
            throw new IllegalArgumentException("No existe cliente con ID: " + id);
        }
        clientRepository.editClient(id, datosNuevos);
    }

    @Override
    public void deleteClient(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }
        Client existing = clientRepository.findClientById(id);
        if (existing == null) {
            throw new IllegalArgumentException("No existe cliente con ID: " + id);
        }
        clientRepository.deleteClient(id);
    }

    @Override
    public Client findClientById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }
        return clientRepository.findClientById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}
