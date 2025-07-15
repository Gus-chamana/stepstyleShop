package com.example.stepstyleshop.controller;

import com.example.stepstyleshop.model.Producto;
import com.example.stepstyleshop.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService; // Asegúrate de tener este servicio.

    // Muestra el formulario para añadir un nuevo producto.
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("pageTitle", "Agregar Nuevo Producto");
        return "admin/gestionProductos";
    }

    // Procesa el guardado del producto.
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto,
                                  BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/gestionProductos";
        }
        productoService.save(producto);
        attributes.addFlashAttribute("success", "¡Producto guardado exitosamente!");
        return "redirect:/admin/dashboard";
    }
}