package com.demo.Task.repository;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductoRepository {
    
    public List<String> findAll() {
        return Arrays.asList("Laptop", "Mouse", "Teclado");
    }
}
