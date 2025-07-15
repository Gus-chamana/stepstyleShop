package com.example.stepstyleshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para todas las rutas públicas y de cara al cliente.
 */
@Controller
public class PublicController {



    // --- PÁGINAS DE LA TIENDA (Carpeta /public) ---
    @GetMapping("/shop")
    public String showShopPage() {
        return "public/shop"; // Muestra templates/public/shop.html
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "public/about"; // Muestra templates/public/about.html
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "public/contact"; // Muestra templates/public/contact.html
    }

    @GetMapping("/shop-single")
    public String showShopSinglePage() {
        // Esta página usualmente requiere un ID de producto, pero por ahora la mapeamos directo
        return "public/shop-single"; // Muestra templates/public/shop-single.html
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "public/checkout"; // Muestra templates/public/checkout.html
    }

    @GetMapping("/cart")
    public String showCartPage() {
        return "public/cart"; // Muestra templates/public/cart.html
    }

    // --- PÁGINAS DEL USUARIO (Carpeta /user) ---
    @GetMapping("/mi-perfil")
    public String showUserProfilePage() {
        return "user/miPerfil"; // Muestra templates/user/miPerfil.html
    }


}