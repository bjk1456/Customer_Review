package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.Comment;
import com.udacity.course3.reviews.domain.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
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
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ReviewRepository revRepo;

    @Autowired
    private CommentRepository comRepo;



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
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") String reviewId,@Valid @RequestBody Comment comment) {
        Optional<Review> review = revRepo.findById(reviewId);
        if (review.isPresent()) {
            /**
            List<Comment> comments;
            if(review.get().getComments() != null){
                comments = review.get().getComments();
            } else{
                comments = new ArrayList();
            */
            comment.setReviewId(review.get().getId());
            return new ResponseEntity(comRepo.save(comment), HttpStatus.OK);
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
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") String reviewId) {
        List<Comment> comment = comRepo.findByReviewId(reviewId);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

}