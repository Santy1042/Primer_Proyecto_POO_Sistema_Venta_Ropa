package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
/**
 * @author Apolo
 */
>>>>>>> origin/develop
public interface ISaleRepository {
    void save(Sale sale);
    List<Sale> list();
    Optional<Sale> findById(int id);
    void update(Sale sale);
    void delete(int id);
}
