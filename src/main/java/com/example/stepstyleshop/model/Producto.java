package com.example.stepstyleshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotEmpty(message = "El nombre del producto es obligatorio.")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres.")
    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo.")
    @Min(value = 0, message = "El precio debe ser mayor que 0.")
    private BigDecimal precio;

    @NotNull(message = "El stock no puede ser nulo.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;

    @Column(name = "imagen_url")
    private String imagenUrl;

    // Relación: Un producto puede estar en muchos detalles de venta
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detallesVenta;
}