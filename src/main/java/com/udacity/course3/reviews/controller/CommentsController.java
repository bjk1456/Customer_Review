package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Comment;
import com.udacity.course3.reviews.domain.Product;
import com.udacity.course3.reviews.domain.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ReviewRepository revRepository;
    @Autowired
    private CommentRepository comRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,@RequestBody Comment comment) {
        System.out.println("Inside createCommentForReview ... reviewId == " + reviewId);
        Optional<Review> review = revRepository.findByReviewId(reviewId);
        if (review.isPresent()) {
            System.out.println("The review is ... product.get().getContent() " + review.get().getContent());
            comment.setReview(review.get());
            //review.setProduct(product.get()); //.setId(product.get().getId());
            //comRepository.save(comment);
            //product.ifPresent(value -> System.out.println("The ifPresent is " + value.getName()));
            return new ResponseEntity(comRepository.save(comment), HttpStatus.OK);
        } else {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        System.out.println("Inside of listCommentsForReview ... reviewId == " + reviewId);
        Optional<Review> review = revRepository.findByReviewId(reviewId);
        return new ResponseEntity(review.get().getComments(), HttpStatus.OK);
    }
}