/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.service;


import java.util.ArrayList;
import java.util.List;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.model.Client;
import com.tienda.sventasropa.interfaces.IClientService;


/**
 *
 * 
 */
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerClient(Client client) {


        if (client == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (findClientById(client.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con esa cedula.");
        }
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
    
    }


    @Override
    public void editClient(int id, Client datosNuevos) {
        Client encontrado = findClientById(id);
        if (datosNuevos == null) {
            throw new IllegalArgumentException("Los datos no pueden ser nulos.");
        }
        if (encontrado == null) {
            throw new IllegalArgumentException("No se encontro el cliente.");
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
        encontrado.setName(datosNuevos.getName());
        encontrado.setLastName(datosNuevos.getLastName());
        encontrado.setPhoneNumber(datosNuevos.getPhoneNumber());
        encontrado.setEmail(datosNuevos.getEmail());
    }

    @Override
    public void deleteClient(int id) {

        Client encontrado = findClientById(id);
        if (encontrado == null) {
            throw new IllegalArgumentException("No se encontro el cliente.");
        }
        
    }

    @Override
    public Client findClientById(int id) {
        if (id < 0){
            throw new IllegalArgumentException("El ID del cliente no puede ser negativo.");
        } 
        Client client = clientRepository.findClientById(id);
        return client;
    }

}
