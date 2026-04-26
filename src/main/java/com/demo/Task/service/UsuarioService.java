package com.demo.Task.service;

import com.demo.Task.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    // INYECCIÓN POR CONSTRUCTOR (mejor práctica)
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public List<String> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public String crearUsuario(String nombre) {
        return usuarioRepository.save(nombre);
    }
}
