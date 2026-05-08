package com.tienda.sventasropa.interfaces;

import com.tienda.sventasropa.model.Sale;
import java.util.List;

public interface ISalePersistence {
    boolean saveSales(List<Sale> sales);
    List<Sale> loadSales();
}
