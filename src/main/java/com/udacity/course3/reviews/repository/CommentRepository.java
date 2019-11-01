package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByAuthor(String author);
    List<Comment> findByReviewId(String reviewId);

}
