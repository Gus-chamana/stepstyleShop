package com.example.stepstyleshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para todas las rutas del panel de administración.
 * Todas las URLs aquí definidas comenzarán con /admin.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Muestra la página principal del panel de administración.
     * URL: /admin/panelAdmin
     * @return La vista del panel de admin.
     */
    @GetMapping("/panelAdmin")
    public String showAdminDashboard() {
        return "admin/panelAdmin"; // Muestra templates/admin/panelAdmin.html
    }

    /**
     * Muestra la página para gestionar productos.
     * URL: /admin/gestion-productos
     * @return La vista de gestión de productos.
     */
    @GetMapping("/gestion-productos")
    public String showProductManagement() {
        return "admin/gestionProductos"; // Muestra templates/admin/gestionProductos.html
    }

    /**
     * Muestra la página para gestionar usuarios.
     * URL: /admin/gestion-usuarios
     * @return La vista de gestión de usuarios.
     */
    @GetMapping("/gestion-usuarios")
    public String showUserManagement() {
        return "admin/gestionUsuarios"; // Muestra templates/admin/gestionUsuarios.html
    }

    /**
     * Muestra la página para gestionar ventas.
     * URL: /admin/gestion-ventas
     * @return La vista de gestión de ventas.
     */
    @GetMapping("/gestion-ventas")
    public String showSalesManagement() {
        return "admin/gestionVentas"; // Muestra templates/admin/gestionVentas.html
    }
}