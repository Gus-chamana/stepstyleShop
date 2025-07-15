package com.example.stepstyleshop.controller;

import com.example.stepstyleshop.model.Usuario;
import com.example.stepstyleshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/miPerfil")
    public String userProfile(Model model, Principal principal) {
        // 'principal.getName()' contiene el correo del usuario que ha iniciado sesión
        if (principal == null) {
            // Si por alguna razón no hay un usuario autenticado, redirigir al login
            return "redirect:/login";
        }

        // Busca al usuario en la base de datos usando su correo
        String correo = principal.getName();
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        // Agrega el objeto usuario al modelo para que la vista pueda usar sus datos
        model.addAttribute("usuario", usuario);

        return "user/miPerfil";
    }
}