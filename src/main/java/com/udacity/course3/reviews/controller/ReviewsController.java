package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ReviewRepository revRepository;



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
    public ResponseEntity createReviewForProduct(@PathVariable("productId") Integer productId,@RequestBody @Valid Review review) {
        review.setProductId(productId);
        return new ResponseEntity(revRepository.save(review), HttpStatus.OK);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        List<Review> reviews = revRepository.findByProductId(productId);
        for(Review revs : reviews){
            System.out.println(revs.getAuthor());
        }
        return new ResponseEntity(reviews.toArray(), HttpStatus.OK);
    }


}