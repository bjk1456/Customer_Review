package com.udacity.course3.reviews.document;


import com.udacity.course3.reviews.model.Comment;
import lombok.Setter;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document("review")
public class ReviewDoc {
    @Id
    private String id;
    private String author;
    private String content;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    private Set<Comment> comments;
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ReviewDoc(
            final String author,
            final String content,
            final Set<Comment> comments){
        this.author = author;
        this.content = content;
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}