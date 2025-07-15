package com.example.stepstyleshop.controller;

import com.example.stepstyleshop.model.Usuario;
import com.example.stepstyleshop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Devuelve el nombre de la vista de login
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Pasa un objeto Usuario vacío al modelo para el data binding del formulario
        model.addAttribute("usuario", new Usuario());
        return "register"; // Devuelve el nombre de la vista de registro
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("usuario") Usuario usuario) {
        // Llama al servicio para guardar el nuevo usuario (la contraseña se encriptará allí)
        usuarioService.save(usuario);
        // Redirige al login para que el nuevo usuario pueda iniciar sesión
        return "redirect:/login?registrationSuccess";
    }
}