package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;

import com.udacity.course3.reviews.domain.Comment;
import com.udacity.course3.reviews.repository.CommentRepository;
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

@ContextConfiguration(classes = ReviewsApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
@RunWith(SpringRunner.class)
public class MongoDbSpringIntegrationTest {
    @DisplayName("Given object When save object using MongoDB template Then object can be found")
    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");
    }
}