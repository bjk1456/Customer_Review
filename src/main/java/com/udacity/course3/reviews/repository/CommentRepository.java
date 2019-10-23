package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Optional<Comment> findByAuthor(String author);

    Optional<Comment> findByContent(String s);
}
