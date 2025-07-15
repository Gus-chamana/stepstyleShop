package com.example.stepstyleshop.repository;

import com.example.stepstyleshop.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Cuenta los productos cuyo stock es inferior a un umbral específico.
     * @param stockLimit El límite de stock para considerar como bajo.
     * @return El número de productos con stock bajo.
     */
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock < ?1")
    long countByStockLessThan(int stockLimit);

    /**
     * NUEVO MÉTODO: Busca los productos más recientes.
     * Ordena los productos por su ID en orden descendente y devuelve
     * una lista limitada por el objeto Pageable.
     * @param pageable Objeto que contiene la información de paginación (ej. "los primeros 3").
     * @return Una lista de los productos más recientes.
     */
    @Query("SELECT p FROM Producto p ORDER BY p.idProducto DESC")
    List<Producto> findFeatured(Pageable pageable);
}