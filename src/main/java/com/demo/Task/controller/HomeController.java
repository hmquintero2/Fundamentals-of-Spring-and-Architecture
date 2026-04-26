package com.demo.Task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Bienvenido a la aplicación de ejercicios");
        response.put("ejercicios", Map.of(
                "1. Inyección de Dependencias (IoC)", "http://localhost:8000/api/ioc/usuarios",
                "2. Manejo de Excepciones", "http://localhost:8000/api/exceptions/usuario/99",
                "3. Interceptores HTTP", "http://localhost:8000/api/private/datos (requiere token)"
        ));
        response.put("token_prueba", "Bearer mi-token-secreto-123");
        return response;
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // No retorna nada para evitar el warning
    }
}