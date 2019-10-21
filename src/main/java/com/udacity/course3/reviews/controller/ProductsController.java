package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductRepository repository;

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Product newProduct(@RequestBody Product product){
        return repository.save(product);
    }

        /**
        System.out.println("Inside of ProductsController ... createProduct ...");
        Product prod = new Product();
        prod.setName("Big one");
        repository.save(prod);

        Optional<Product> theP = repository.findByName("Big One");

        theP.ifPresent(value -> System.out.println("The big one is " + value.getName()));
         */




        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Optional<Product> theP = repository.findById(id);
        if(theP.isPresent()){
            //return theP.get();
            return new ResponseEntity<Product>(theP.get(), HttpStatus.OK);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND);

    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> listProducts() {
        return repository.findAll();

    }
}