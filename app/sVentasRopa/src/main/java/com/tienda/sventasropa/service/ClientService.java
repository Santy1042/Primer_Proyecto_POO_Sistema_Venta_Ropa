package com.tienda.sventasropa.service;

import java.util.List;

import com.tienda.sventasropa.interfaces.IClientRepository;
import com.tienda.sventasropa.interfaces.IClientService;
import com.tienda.sventasropa.model.Client;

public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerClient(Client client) {

        validateClient(client);

        if (findClientById(client.getId()) != null) {

            throw new IllegalArgumentException(
                "Ya existe un cliente con esa cédula."
            );
        }

        boolean guardado = clientRepository.save(client);

        if (!guardado) {

            throw new RuntimeException(
                "No se pudo guardar el cliente."
            );
        }
    }

    @Override
    public void editClient(int id, Client datosNuevos) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                "El ID del cliente debe ser mayor que 0."
            );
        }

        validateClient(datosNuevos);

        Client encontrado = findClientById(id);

        if (encontrado == null) {

            throw new IllegalArgumentException(
                "No se encontró el cliente con ID: " + id
            );
        }

        boolean editado = clientRepository.editClient(id, datosNuevos);

        if (!editado) {

            throw new RuntimeException(
                "No se pudo editar el cliente."
            );
        }
    }

    @Override
    public void deleteClient(int id) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                "El ID del cliente debe ser mayor que 0."
            );
        }

        Client encontrado = findClientById(id);

        if (encontrado == null) {

            throw new IllegalArgumentException(
                "No se encontró el cliente con ID: " + id
            );
        }

        boolean eliminado = clientRepository.deleteClient(id);

        if (!eliminado) {

            throw new RuntimeException(
                "No se pudo eliminar el cliente."
            );
        }
    }

    @Override
    public Client findClientById(int id) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                "El ID del cliente debe ser mayor que 0."
            );
        }

        return clientRepository.findClientById(id);
    }

    @Override
    public List<Client> getAllClients() {

        return clientRepository.getAllClients();
    }

    private void validateClient(Client client) {

        if (client == null) {

            throw new IllegalArgumentException(
                "El cliente no puede ser nulo."
            );
        }

        if (client.getId() <= 0) {

            throw new IllegalArgumentException(
                "La cédula debe ser mayor que 0."
            );
        }

        if (client.getName() == null ||
            client.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "El nombre es obligatorio."
            );
        }

        if (client.getLastName() == null ||
            client.getLastName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "El apellido es obligatorio."
            );
        }

        if (client.getEmail() == null ||
            client.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "El email es obligatorio."
            );
        }

        if (!isValidEmail(client.getEmail())) {

            throw new IllegalArgumentException(
                "Formato de email inválido."
            );
        }

        if (client.getPhoneNumber() == null ||
            client.getPhoneNumber().trim().isEmpty()) {

            throw new IllegalArgumentException(
                "El teléfono es obligatorio."
            );
        }

        if (!isValidPhone(client.getPhoneNumber())) {

            throw new IllegalArgumentException(
                "El teléfono solo debe contener números."
            );
        }
    }

    private boolean isValidEmail(String email) {

        return email.matches(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        );
    }

    private boolean isValidPhone(String phone) {

        return phone.matches("\\d+");
    }
}