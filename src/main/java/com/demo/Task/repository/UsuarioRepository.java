package com.demo.Task.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    
    private List<String> usuarios = new ArrayList<>();
    
    public UsuarioRepository() {
        // Datos de ejemplo
        usuarios.add("Juan");
        usuarios.add("Maria");
        usuarios.add("Carlos");
    }
    
    public List<String> findAll() {
        return new ArrayList<>(usuarios);
    }
    
    public String save(String nombre) {
        usuarios.add(nombre);
        return nombre;
    }
}