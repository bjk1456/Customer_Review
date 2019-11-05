package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.ReviewDoc;
import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.MongoReviewsRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private MongoReviewsRepository mongoRev;

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
        int reviewIdInt;
        try {
            reviewIdInt = Integer.parseInt(reviewId);
        }
        catch (NumberFormatException e)
        {
            reviewIdInt = 0;
        }
        Optional<Review> reviewModel = revRepository.findByReviewId(reviewIdInt);
        Optional<ReviewDoc> reviewDoc = mongoRev.findById(reviewId);

        if (reviewModel.isPresent()) {
            comment.setReview(reviewModel.get());
            return new ResponseEntity(comRepository.save(comment), HttpStatus.OK);
        } else if(reviewDoc.isPresent()){
            Set<Comment> comments;
            if(reviewDoc.get().getComments() != null){
                comments = reviewDoc.get().getComments();
            } else{
                comments = new HashSet();
            }
            comments.add(comment);
            reviewDoc.get().setComments(comments);
            return new ResponseEntity(mongoRev.save(reviewDoc.get()), HttpStatus.OK);
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
        int reviewIdInt;
        try {
            reviewIdInt = Integer.parseInt(reviewId);
        }
        catch (NumberFormatException e)
        {
            reviewIdInt = 0;
        }
        Optional<ReviewDoc> reviewDoc = mongoRev.findById(reviewId);
        Optional<Review> reviewModel = revRepository.findByReviewId(reviewIdInt);

        if(reviewModel.isPresent()){
            return new ResponseEntity(reviewModel.get().getComments(), HttpStatus.OK);
        } else if(reviewDoc.isPresent()){
            return new ResponseEntity(reviewDoc.get().getComments(), HttpStatus.OK);
        } else
            {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }
}