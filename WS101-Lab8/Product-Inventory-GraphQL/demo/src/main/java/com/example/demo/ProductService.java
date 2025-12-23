package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {
        products.add(new Product(nextId++, "GraphQL Guide", 50.0));
    }

    public List<Product> getAllProducts() { return products; }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(String name, Double price) {
        Product p = new Product(nextId++, name, price);
        products.add(p);
        return p;
    }

    public Optional<Product> updateProduct(Long id, String name, Double price) {
        return getProductById(id).map(p -> {
            p.setName(name);
            p.setPrice(price);
            return p;
        });
    }

    public boolean deleteProduct(Long id) { return products.removeIf(p -> p.getId().equals(id)); }
}