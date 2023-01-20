package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * It's a service class that uses the ProductRepository class to get data from the database
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

   public Optional<Product> getProduct(int poroductId){
        return productRepository.getProduct(poroductId);
   }

   public Product save (Product product){
        return productRepository.save(product);
   }

   public boolean delete(int productId){
       return getProduct(productId).map(product -> {
           productRepository.delete(productId);
           return true;
       }).orElse(false);
    }

}
