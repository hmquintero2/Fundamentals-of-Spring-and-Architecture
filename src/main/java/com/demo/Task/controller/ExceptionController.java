package com.demo.Task.controller;


import com.demo.Task.exception.ErrorValidacionException;
import com.demo.Task.exception.UsuarioNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exceptions")
public class ExceptionController {
    
    @GetMapping("/usuario/{id}")
    public String getUsuario(@PathVariable Long id) {
        if (id == 1) {
            return "Usuario encontrado: Juan";
        }
        // Esto activará el GlobalExceptionHandler
        throw new UsuarioNotFoundException("Usuario con ID " + id + " no existe");
    }
    
    @PostMapping("/validar")
    public String validarDatos(@RequestBody String dato) {
        if (dato == null || dato.trim().isEmpty()) {
            // Excepción local (manejada por el controlador)
            throw new IllegalArgumentException("El dato no puede estar vacío");
        }
        
        if (dato.length() < 3) {
            // Excepción global
            throw new ErrorValidacionException("El dato debe tener al menos 3 caracteres");
        }
        
        return "Dato válido: " + dato;
    }
    
    // Manejo de excepción LOCAL (solo para este controlador)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public String handleLocalException(IllegalArgumentException ex) {
        return "ERROR LOCAL: " + ex.getMessage();
    }
}