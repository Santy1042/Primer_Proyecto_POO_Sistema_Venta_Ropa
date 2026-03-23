/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.juanfedevmaster.manageclient.repository;

import com.juanfedevmaster.manageclient.model.Client;
import java.util.List;

/**
 *
 * @author juanfe
 */
public interface IClientRepository {

    void save (Client cliente);

    List<Client> getAllClients();
    
}
