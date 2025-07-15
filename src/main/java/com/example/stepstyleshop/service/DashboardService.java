package com.example.stepstyleshop.service;

import com.example.stepstyleshop.repository.ProductoRepository;
import com.example.stepstyleshop.repository.UsuarioRepository; // <-- CAMBIO AQUÍ
import com.example.stepstyleshop.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private UsuarioRepository usuarioRepository; // <-- CAMBIO AQUÍ

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();

        // 1. Obtener total de usuarios desde UsuarioRepository
        long totalUsuarios = usuarioRepository.count(); // <-- CAMBIO AQUÍ
        data.put("totalUsuarios", totalUsuarios);

        // 2. Obtener ventas del mes (esto no cambia)
        BigDecimal ventasMes = ventaRepository.findTotalVentasDelMes();
        data.put("ventasMes", (ventasMes != null) ? ventasMes : BigDecimal.ZERO);

        // 3. Obtener número de productos con stock bajo (esto no cambia)
        long productosStockBajo = productoRepository.countByStockLessThan(10);
        data.put("productosStockBajo", productosStockBajo);

        return data;
    }
}