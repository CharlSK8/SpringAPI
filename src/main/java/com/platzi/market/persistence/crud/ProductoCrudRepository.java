package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoria(int IdCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int IdCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int IdCategoria, boolean estado);
}
