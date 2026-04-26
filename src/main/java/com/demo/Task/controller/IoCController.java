package com.demo.Task.controller;


import com.demo.Task.service.UsuarioService;
import com.demo.Task.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ioc")
public class IoCController {
    
    // INYECCIÓN POR CAMPO (no recomendada, solo para demostración)
    @Autowired
    private UsuarioService usuarioService;
    
    private final ProductoService productoService;
    
    // Inyección por constructor (combinada)
    public IoCController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    @GetMapping("/usuarios")
    public List<String> getUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
    
    @GetMapping("/productos")
    public List<String> getProductos() {
        return productoService.obtenerProductos();
    }
    
    @PostMapping("/usuarios")
    public String crearUsuario(@RequestBody String nombre) {
        return usuarioService.crearUsuario(nombre);
    }
}