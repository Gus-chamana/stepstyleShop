package com.example.stepstyleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/generar-pass") // Cambiamos la ruta para más claridad
    public String generarPassword(@RequestParam String raw) {

        // Generamos un nuevo hash para la contraseña proporcionada
        String nuevoHash = passwordEncoder.encode(raw);

        System.out.println("--- GENERADOR DE CONTRASEÑAS ---");
        System.out.println("Contraseña en texto plano: " + raw);
        System.out.println("NUEVO HASH GENERADO: " + nuevoHash);
        System.out.println("---------------------------------");

        return "<h1>Nuevo Hash Generado:</h1><h2>" + nuevoHash + "</h2><p>Copia este valor y pégalo en tu archivo sqlStepstyle.sql para el usuario correspondiente.</p>";
    }
}