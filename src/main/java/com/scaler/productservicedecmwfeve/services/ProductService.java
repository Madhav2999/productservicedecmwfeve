package com.scaler.productservicedecmwfeve.services;

import com.scaler.productservicedecmwfeve.Dtos.FakeStoreProductDto;
import com.scaler.productservicedecmwfeve.Exceptions.ProductNotExistException;
import com.scaler.productservicedecmwfeve.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService
{
    Product getSingleProduct(long id) throws ProductNotExistException;
    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product replaceProduct(long id,Product product);

    Product updateProduct(Long id,Product product);

    void deleteProduct(long id);
}
