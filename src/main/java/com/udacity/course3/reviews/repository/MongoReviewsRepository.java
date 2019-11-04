package com.udacity.course3.reviews.repository;


import com.udacity.course3.reviews.document.ReviewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoReviewsRepository extends MongoRepository<ReviewDoc, String> {

    Optional<ReviewDoc> findByAuthor(String author);
    Optional<ReviewDoc> findById(String id);

    //@Query("{'address.country': ?0}")
    List<ReviewDoc> findByProductId(Integer prodId);
}