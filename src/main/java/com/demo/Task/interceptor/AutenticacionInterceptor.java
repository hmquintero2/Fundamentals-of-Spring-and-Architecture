package com.demo.Task.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutenticacionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        String authToken = request.getHeader("Authorization");
        
        System.out.println("🔐 [AUTH] Verificando autenticación para: " + request.getRequestURI());
        
        // Simular autenticación (en realidad validarías el token)
        if (authToken == null || !authToken.startsWith("Bearer ")) {
            System.out.println("🔐 [AUTH] ❌ Acceso denegado - Token no válido");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"No autorizado - Token requerido\"}");
            return false; // Bloquear la ejecución
        }
        
        String token = authToken.substring(7); // Quitar "Bearer "
        if (!token.equals("mi-token-secreto-123")) {
            System.out.println("🔐 [AUTH] ❌ Token inválido: " + token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Token inválido\"}");
            return false;
        }
        
        System.out.println("🔐 [AUTH] ✅ Acceso permitido");
        return true;
    }
}