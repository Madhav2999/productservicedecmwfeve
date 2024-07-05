package com.scaler.productservicedecmwfeve.controllers;

import com.scaler.productservicedecmwfeve.Dtos.FakeStoreProductDto;
import com.scaler.productservicedecmwfeve.Dtos.Role;
import com.scaler.productservicedecmwfeve.Dtos.UserDto;
import com.scaler.productservicedecmwfeve.Exceptions.ProductNotExistException;
import com.scaler.productservicedecmwfeve.ProductservicedecmwfeveApplication;
import com.scaler.productservicedecmwfeve.commons.AuthenticationCommons;
import com.scaler.productservicedecmwfeve.models.Category;
import com.scaler.productservicedecmwfeve.models.Product;
import com.scaler.productservicedecmwfeve.services.ProductService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Qualifier("selfProductService")
    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationCommons authenticationCommons;
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("AuthorizationToken") String token)
    {

//        UserDto userDto = authenticationCommons.validateToken(token);
//        if(userDto == null)
//        {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        boolean isAdmin = false;
//        for(Role role:userDto.getRoles())
//        {
//            if(role.getName().equals("ADMIN"))
//            {
//                isAdmin = true;
//                break;
//            }
//        }
//        if(!isAdmin)
//        {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotExistException
    {

       return productService.getSingleProduct(id);
    }
    @PostMapping()
    public Product addNewProduct(@RequestBody Product product)
    {
        return productService.addNewProduct(product);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id,Product product)
    {
        return productService.updateProduct(id,product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id,@RequestBody Product product)
    {
        return productService.replaceProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id)
    {
         productService.deleteProduct(id);
    }
}
