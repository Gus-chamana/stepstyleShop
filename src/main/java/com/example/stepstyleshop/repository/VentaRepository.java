package com.example.stepstyleshop.repository;

import com.example.stepstyleshop.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Calcula la suma total de las ventas realizadas en el mes y año actuales.
     * La consulta es específica para MySQL.
     * @return Un BigDecimal con el total de ventas del mes, o 0 si no hay ventas.
     */
    @Query(value = "SELECT SUM(v.total) FROM ventas v WHERE YEAR(v.fecha_venta) = YEAR(CURRENT_DATE()) AND MONTH(v.fecha_venta) = MONTH(CURRENT_DATE())",
            nativeQuery = true)
    BigDecimal findTotalVentasDelMes();
}