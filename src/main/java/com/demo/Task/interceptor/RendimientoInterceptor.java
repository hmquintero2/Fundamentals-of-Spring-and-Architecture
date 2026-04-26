package com.demo.Task.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RendimientoInterceptor implements HandlerInterceptor {
    
    private Map<String, Integer> contadorEndpoints = new ConcurrentHashMap<>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        String endpoint = request.getRequestURI();
        
        // Contar peticiones por endpoint
        contadorEndpoints.merge(endpoint, 1, Integer::sum);
        
        System.out.println("⚡ [RENDIMIENTO] Endpoint: " + endpoint + 
                          " | Total peticiones: " + contadorEndpoints.get(endpoint));
        
        // Guardar timestamp inicio
        request.setAttribute("perfStartTime", System.nanoTime());
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, 
                               HttpServletResponse response, 
                               Object handler, 
                               Exception ex) throws Exception {
        
        long startNano = (Long) request.getAttribute("perfStartTime");
        long endNano = System.nanoTime();
        long durationMicros = (endNano - startNano) / 1000;
        
        System.out.println("⚡ [RENDIMIENTO] URI: " + request.getRequestURI() + 
                          " | Duración: " + durationMicros + " µs" +
                          " | Status: " + response.getStatus());
        
        // Advertencia si es lento
        if (durationMicros > 1000000) { // 1 segundo
            System.out.println("⚠️ [RENDIMIENTO] Endpoint LENTO: " + 
                              request.getRequestURI() + " tomó " + durationMicros + " µs");
        }
    }
}