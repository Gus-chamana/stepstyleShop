package com.example.stepstyleshop.service;

import com.example.stepstyleshop.model.Usuario;
import com.example.stepstyleshop.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // --- LOG DE DIAGNÓSTICO ---
        System.out.println("-------------------------------------------------------");
        System.out.println("--- INTENTANDO AUTENTICAR AL USUARIO ---");
        System.out.println("Buscando usuario con correo: " + correo);
        // -------------------------

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> {
                    // --- LOG DE DIAGNÓSTICO ---
                    System.out.println("!!! ERROR: No se encontró ningún usuario con el correo: " + correo);
                    System.out.println("-------------------------------------------------------");
                    // -------------------------
                    return new UsernameNotFoundException("Usuario con correo " + correo + " no encontrado.");
                });

        // --- LOG DE DIAGNÓSTICO ---
        System.out.println("Usuario encontrado en la BD: " + usuario.getNombre());
        System.out.println("Password (hash) desde la BD: " + usuario.getPassword());
        System.out.println("Rol desde la BD: " + usuario.getRol());
        System.out.println("-------------------------------------------------------");
        // -------------------------

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .roles(usuario.getRol().replace("ROLE_", ""))
                .build();
    }
}