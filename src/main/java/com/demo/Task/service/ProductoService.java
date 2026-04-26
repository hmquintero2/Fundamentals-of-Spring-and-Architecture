package com.demo.Task.service;

import com.demo.Task.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    
    private ProductoRepository productoRepository;
    
    // INYECCIÓN POR SETTER
    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    public List<String> obtenerProductos() {
        return productoRepository.findAll();
    }
}
