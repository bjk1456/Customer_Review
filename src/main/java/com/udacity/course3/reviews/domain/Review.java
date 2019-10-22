package com.udacity.course3.reviews.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Integer reviewId;

    @NotBlank
    @Size(max = 350)
    @Column(name="author")
    private String author;

    @Column(name="content")
    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id_fk")
    private Product product;

    @OneToMany(mappedBy="review",fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Review() {}

    public void setId(Integer reviewId){this.reviewId = reviewId;}

    public Integer getId(){return reviewId;}

    public void setAuthor(String author){this.author = author;}

    public String getAuthor(){return author;}

    public void setContent(String content){this.content = content;}

    public String getContent(){return content;}

    public void setProduct(Product product){this.product = product;}

    public Product getProduct() {return product;}

    public void setComment(Set<Comment> comments){this.comments = comments;}

    public Set<Comment> getComments() {return comments;}

}
