package com.scaler.productservicedecmwfeve.services;

import com.scaler.productservicedecmwfeve.Dtos.FakeStoreProductDto;
import com.scaler.productservicedecmwfeve.Exceptions.ProductNotExistException;
import com.scaler.productservicedecmwfeve.models.Category;
import com.scaler.productservicedecmwfeve.models.Product;
import com.scaler.productservicedecmwfeve.repository.CategoryRespository;
import com.scaler.productservicedecmwfeve.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRespository categoryRespository;
    @Override
    public Product getSingleProduct(long id) throws ProductNotExistException {
        Optional<Product>product = productRepository.findById(id);
        if(product.isEmpty())
        {
            throw new ProductNotExistException("product does not exist");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category>categoryOptional = categoryRespository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty())
        {
            product.setCategory(categoryRespository.save(product.getCategory()));
        }
        else
        {
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }


    @Override
    public Product replaceProduct(long id, Product product) {
        Optional<Product>product1 = productRepository.findById(id);
        if(product1.isEmpty())
        {
            return addNewProduct(product);
        }
        else
        {
            if(product.getName()!=null)
            {
                product1.get().setName(product.getName());
            }
            if(product.getDescription()!=null)
            {
                product1.get().setDescription(product.getDescription());
            }
            if(product.getPrice()!=null)
            {
                product1.get().setPrice(product.getPrice());
            }
            Optional<Category>category = categoryRespository.findByName(product.getCategory().getName());
            if(category.isEmpty())
            {
                product1.get().setCategory(categoryRespository.save(product.getCategory()));
            }
            else
            {
                product1.get().setCategory(category.get());
            }
        }
        return product1.get();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product>product1 = productRepository.findById(id);
        if(product1.isEmpty())
        {
            return productRepository.save(product1.get());
        }
        else
        {
            if(product.getName()!=null)
            {
                product1.get().setName(product.getName());
            }
            if(product.getDescription()!=null)
            {
                product1.get().setDescription(product.getDescription());
            }
            if(product.getPrice()!=null)
            {
                product1.get().setPrice(product.getPrice());
            }
            if(product.getCategory()!=null)
            {
                product1.get().setCategory(product.getCategory());
            }
        }
        return productRepository.save(product1.get());
    }

    @Override
    public void deleteProduct(long id) {
         productRepository.deleteById(id);
    }

}
