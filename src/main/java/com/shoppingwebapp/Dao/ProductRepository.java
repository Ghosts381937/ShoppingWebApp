package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    public Iterable<Product> findByNameContaining(String name);
}
