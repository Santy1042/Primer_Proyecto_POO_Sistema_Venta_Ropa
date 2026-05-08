package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.interfaces.ISaleRepository;
import com.tienda.sventasropa.interfaces.ISalePersistence;
import com.tienda.sventasropa.model.Sale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SaleRepository implements ISaleRepository {
    private final List<Sale> storage;
    private final ISalePersistence persistence;

    public SaleRepository(ISalePersistence persistence) {
        this.persistence = persistence;
        this.storage = new ArrayList<>(this.persistence.loadSales());
    }

    @Override
    public void save(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        storage.add(sale);
        persistence.saveSales(storage);
    }

    @Override
    public List<Sale> list() {
        return Collections.unmodifiableList(new ArrayList<>(storage));
    }

    @Override
    public Optional<Sale> findById(int id) {
        return storage.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public void update(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        storage.stream()
                .filter(s -> s.getId() == sale.getId())
                .findFirst()
                .ifPresentOrElse(
                    s -> {
                        int index = storage.indexOf(s);
                        storage.set(index, sale);
                        persistence.saveSales(storage);
                    },
                    () -> {
                        throw new IllegalArgumentException("No se encontró una venta con el ID especificado");
                    }
                );
    }

    @Override
    public void delete(int id) {
        storage.removeIf(s -> s.getId() == id);
        persistence.saveSales(storage);
    }
}
