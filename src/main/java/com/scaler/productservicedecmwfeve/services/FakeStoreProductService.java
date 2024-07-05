package com.scaler.productservicedecmwfeve.services;


import com.scaler.productservicedecmwfeve.Dtos.FakeStoreProductDto;
import com.scaler.productservicedecmwfeve.Exceptions.ProductNotExistException;
import com.scaler.productservicedecmwfeve.models.Category;
import com.scaler.productservicedecmwfeve.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class FakeStoreProductService implements ProductService
{
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Product getSingleProduct(long id) throws ProductNotExistException {
      FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
      if(fakeStoreProductDto==null)
      {
          throw new ProductNotExistException("Product with id " + id + " doesn't exist");
      }
        Product p = new Product();
        p.setId(fakeStoreProductDto.getId());
        p.setName(fakeStoreProductDto.getTitle());
        p.setPrice(fakeStoreProductDto.getPrice());
        p.setCategory(new Category());
        p.getCategory().setName(fakeStoreProductDto.getCategory());
        p.setDescription(fakeStoreProductDto.getDescription());
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product>products = new ArrayList<Product>();
        for(int i=0;i< fakeStoreProductDtos.length;i++)
        {
            Product p = new Product();
            p.setId(fakeStoreProductDtos[i].getId());
            p.setName(fakeStoreProductDtos[i].getTitle());
            p.setCategory(new Category());
            p.getCategory().setName(fakeStoreProductDtos[i].getCategory());
            p.setPrice(fakeStoreProductDtos[i].getPrice());
            p.setDescription(fakeStoreProductDtos[i].getDescription());
            products.add(p);
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.POST, requestCallback, responseExtractor, new Object[]{});
        return convertFakestoreproductToProduct(response);
    }


//    public FakeStoreProductDto addNewProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) {
//        restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto, void.class);
//        return fakeStoreProductDto;
//    }
    Product convertFakestoreproductToProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        Product p = new Product();
        p.setId(fakeStoreProductDto.getId());
        p.setPrice(fakeStoreProductDto.getPrice());
        p.setName(fakeStoreProductDto.getTitle());
        p.setDescription(fakeStoreProductDto.getDescription());
        p.setCategory(new Category());
        p.getCategory().setName(fakeStoreProductDto.getCategory());
        return p;
    }
    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakestoreproductToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //Product product1 = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, Product.class);
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakestoreproductToProduct(response);
    }

    @Override
    public void deleteProduct(long id) {
        restTemplate.delete("http://fakestoreapi.com/"+id);
        System.out.println("deleted product with id: "+id);
    }


}
