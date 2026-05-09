package com.tienda.sventasropa.repository;

import com.tienda.sventasropa.interfaces.ISaleRepository;
import com.tienda.sventasropa.interfaces.ISalePersistence;
import com.tienda.sventasropa.model.Sale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SaleRepository implements ISaleRepository {
    private final List<Sale> sales;
    private final ISalePersistence persistence;

    public SaleRepository(ISalePersistence persistence) {
        this.persistence = persistence;
        this.sales = this.persistence.loadSales();
    }

    @Override
    public void save(Sale sale) {
        if (sale == null) throw new IllegalArgumentException("La venta no puede ser nula");
        sales.add(sale);
        persistence.saveSales(sales);
    }

    @Override
    public List<Sale> list() {
        return Collections.unmodifiableList(new ArrayList<>(sales));
    }

    @Override
    public Optional<Sale> findById(int id) {
        return sales.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public boolean delete(int id) {
        boolean removed = sales.removeIf(s -> s.getId() == id);
        if (removed) {
            persistence.saveSales(sales);
        }
        return removed;
    }
}
