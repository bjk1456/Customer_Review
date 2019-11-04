package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.ReviewDoc;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.MongoReviewsRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ReviewRepository revRepository;
    @Autowired
    private ProductRepository prodRepository;

    @Autowired
    private MongoReviewsRepository mongoRev;


    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,@RequestBody @Valid Review review) {
        Optional<Product> product = prodRepository.findByProductId(productId);
        if (product.isPresent()) {
            ArrayList ret = new ArrayList();
            review.setProduct(product.get());
            product.ifPresent(value -> System.out.println("The ifPresent is " + value.getName()));
            ret.add(revRepository.save(review));
            ReviewDoc reviewDoc= new ReviewDoc(review.getAuthor(),review.getContent(),review.getComments());
            ret.add(mongoRev.save(reviewDoc));
            return new ResponseEntity(ret,HttpStatus.OK);
        } else {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> product = prodRepository.findById(productId);
        return new ResponseEntity(product.get().getReviews(), HttpStatus.OK);
    }
}