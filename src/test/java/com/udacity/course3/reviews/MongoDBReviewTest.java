package com.udacity.course3.reviews;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.domain.Comment;
import com.udacity.course3.reviews.domain.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDBReviewTest {
    @Autowired
    ReviewRepository revR;
    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    public void test() {
        // given
        List<Comment> comL = new ArrayList<>();
        Comment comment = new Comment("Billy","I disagree with your review");
        comL.add(comment);

        Review rev = new Review("John Doe","I hated it.");

        // when
        revR.save(rev);

        // then
        System.out.println(revR.findByAuthor("John Doe").get().getAuthor());
    }
}