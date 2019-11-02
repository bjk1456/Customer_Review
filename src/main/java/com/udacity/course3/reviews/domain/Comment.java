package com.udacity.course3.reviews.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Document("comments")
public class Comment {
    private String author;
    private String content;

    public Comment() {

    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    private String reviewId;

    public Comment(
            final String author,
            final String content) {
        this.author = author;
        this.content = content;
        this.reviewId = reviewId;
    }
}