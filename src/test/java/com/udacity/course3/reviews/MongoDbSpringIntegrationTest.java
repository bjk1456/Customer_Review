package com.udacity.course3.reviews;

import static org.junit.Assert.*;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.udacity.course3.reviews.ReviewsApplication;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@ContextConfiguration(classes = ReviewsApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDbSpringIntegrationTest {

    @BeforeEach
    public void reset(){
        mongoTemplate.dropCollection("review");
    }

    @Autowired MongoTemplate mongoTemplate;
    @DisplayName("Save a comment using MongoDB template then find it via its Author")
    @Test
    public void testFindByCommentAuthor() {
        // given
        Comment com = new Comment();
        com.setId((long) 123);
        com.setAuthor("John Doe");

        // when
        mongoTemplate.save(com, "review");

        assertEquals("John Doe", mongoTemplate.findAll(Comment.class, "review").get(0).getAuthor());
    }

    @DisplayName("Save a comment using MongoDB template then find it via its ReviewId")
    @Test
    public void testFindByCommentReviewId() {
        // given
        Comment com = new Comment();
        long comId = 456L;
        com.setId(comId);
        com.setAuthor("John Doe");
        Review rev = new Review();
        rev.setId(123);
        Set<Comment> s = new HashSet();
        s.add(com);
        rev.setComment(s);
        // when
        mongoTemplate.save(rev, "review");
        //Comment found = (Comment)mongoTemplate.findAll(Review.class, "review").get(0).getComments().toString();
        Review foundR = mongoTemplate.findAll(Review.class, "review").get(0);
        Comment foundC = (Comment) foundR.getComments().toArray()[0];
        assertEquals("John Doe", foundC.getAuthor());
    }

    @DisplayName("Save a review using MongoDB template then find it via its Id")
    @Test
    public void testFindByReviewId() {
        // given
        Review rev = new Review();
        rev.setId(123);
        rev.setAuthor("John Doe");

        // when
        mongoTemplate.save(rev, "review");

        assertEquals("123", mongoTemplate.findAll(Review.class, "review").get(0).getId().toString());
    }

    @DisplayName("Save a review using MongoDB template then find it via its author")
    @Test
    public void testFindByReviewAuthor() {
        // given
        Review rev = new Review();
        rev.setId(123);
        rev.setAuthor("John Doe");

        // when
        mongoTemplate.save(rev, "review");

        assertEquals("John Doe", mongoTemplate.findAll(Review.class, "review").get(0).getAuthor());
    }

    @DisplayName("Save a review using MongoDB template then find it via its productId")
    @Test
    public void testFindByReviewProductId() {
        // given
        Review rev = new Review();
        rev.setId(123);
        rev.setAuthor("John Smith");
        Product p = new Product();
        p.setId(456);
        rev.setProduct(p);

        // when
        mongoTemplate.save(rev, "review");

        assertEquals("456", mongoTemplate.findAll(Review.class, "review").get(0).getProduct().getId().toString());
    }
}