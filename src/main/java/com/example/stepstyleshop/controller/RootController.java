package com.example.stepstyleshop.controller;

import com.example.stepstyleshop.service.DashboardService;
import com.example.stepstyleshop.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "index";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Map<String, Object> dashboardData = dashboardService.getDashboardData();

        model.addAttribute("totalUsuarios", dashboardData.get("totalUsuarios"));
        model.addAttribute("ventasMes", dashboardData.get("ventasMes"));
        model.addAttribute("productosStockBajo", dashboardData.get("productosStockBajo"));

        return "admin/panelAdmin";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "access-denied";
    }
}