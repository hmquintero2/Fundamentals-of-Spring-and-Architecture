package com.demo.Task.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InterceptorTestController {
    
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello World - Ruta pública (sin autenticación)";
    }
    
    @GetMapping("/private/datos")
    public String datosPrivados() {
        // Simular procesamiento para ver el rendimiento
        try {
            Thread.sleep(100); // Simula 100ms de procesamiento
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Datos privados - Requiere autenticación";
    }
    
    @GetMapping("/private/usuarios")
    public String getUsuariosPrivados() {
        // Simular procesamiento lento
        try {
            Thread.sleep(500); // 500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Lista de usuarios (requiere token)";
    }
    
    @PostMapping("/private/crear")
    public String crearRecurso() {
        return "Recurso creado exitosamente";
    }
}