/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.sventasropa.interfaces;

import java.util.List;

import com.tienda.sventasropa.model.Client;

/**
 *
 * 
 */
public interface IClientRepository {
    boolean registerClient (Client cliente);
    void editClient(int id, Client datosNuevos);
    void deleteClient(int id);
    Client findClientById(int id);
    List<Client> getAllClients();
}
