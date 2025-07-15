package com.example.stepstyleshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios") // Asegúrate que apunte a la nueva tabla
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") // Coincide con la columna PK
    private Integer idUsuario;

    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String direccion;
    private String telefono;
    private String rol;
}