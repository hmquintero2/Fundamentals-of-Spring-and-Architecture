package com.demo.Task.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        System.out.println("📝 [PRE-HANDLE] Método: " + request.getMethod() + 
                          " | URI: " + request.getRequestURI() +
                          " | Hora: " + System.currentTimeMillis());
        
        // Guardar tiempo inicio para medir rendimiento
        request.setAttribute("startTime", System.currentTimeMillis());
        
        return true; // Continúa la ejecución
    }
    
    @Override
    public void postHandle(HttpServletRequest request, 
                          HttpServletResponse response, 
                          Object handler, 
                          ModelAndView modelAndView) throws Exception {
        
        System.out.println("📝 [POST-HANDLE] URI: " + request.getRequestURI() + 
                          " | Status: " + response.getStatus());
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, 
                               HttpServletResponse response, 
                               Object handler, 
                               Exception ex) throws Exception {
        
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("📝 [AFTER-COMPLETION] URI: " + request.getRequestURI() + 
                          " | Duración: " + duration + "ms" +
                          (ex != null ? " | Error: " + ex.getMessage() : ""));
        
        if (ex != null) {
            System.out.println("❌ Error en la ejecución: " + ex.getMessage());
        }
    }
}
