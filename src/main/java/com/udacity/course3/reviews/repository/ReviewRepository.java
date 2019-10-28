package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    Optional<Review> findByAuthor(String author);
    Optional<Review> findById(String id);

    //@Query("{'address.country': ?0}")
    List<Review> findByProductId(Integer prodId);
}
