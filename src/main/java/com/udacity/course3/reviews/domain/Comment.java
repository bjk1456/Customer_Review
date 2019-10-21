package com.udacity.course3.reviews.domain;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long commentId;

    @NotBlank
    @Size(max = 350)
    @Column(name="author")
    private String author;

    @Column(name="content")
    private String content;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    public Comment() {}

    public void setId(Long productId){this.commentId = commentId;}

    public Long getId(){return commentId;}

    public void setAuthor(String author){this.author = author;}

    public String getAuthor(){return author;}

    public void setContent(String content){this.content = content;}

    public String getContent(){return content;}

    public void setReview(Review review){this.review = review;}

    public Review getReview() {return review;}

}