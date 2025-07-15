package com.example.stepstyleshop.service;

import com.example.stepstyleshop.model.Usuario;

public interface UsuarioService {
    /**
     * Guarda un nuevo usuario en la base de datos.
     * La contraseña se encriptará antes de guardarse.
     * @param usuario El objeto Usuario a guardar.
     * @return El usuario guardado.
     */
    Usuario save(Usuario usuario);
}