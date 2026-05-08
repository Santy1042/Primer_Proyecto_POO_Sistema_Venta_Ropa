package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import java.util.List;

/**
 * Interfaz para la persistencia de ventas en JSON
 * @author Apolo
 */
public interface ISalePersistence {
    boolean saveSales(List<Sale> sales);
    List<Sale> loadSales();
}
