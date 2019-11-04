package com.udacity.course3.reviews.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @NotEmpty(message = "Please provide a name.")
    @Size(max = 350)
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="product",fetch = FetchType.LAZY)
    private Set<Review> reviews;

    public Product(){}

    public void setId(Integer productId){this.productId = productId;}

    public Integer getId(){return productId;}

    public void setName(String name){this.name = name;}

    public String getName(){return name;}

    public void setReviews(Set<Review> reviews){this.reviews = reviews;}

    public Set<Review> getReviews(){return reviews;}

}