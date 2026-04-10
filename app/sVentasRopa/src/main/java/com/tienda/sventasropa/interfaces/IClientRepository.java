/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Client;
import java.util.List;

/**
 * Repository interface for Client persistence.
 */
public interface IClientRepository {
    void save(Client cliente);
    List<Client> getAllClients();
}
