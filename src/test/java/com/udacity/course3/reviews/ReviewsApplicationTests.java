package com.udacity.course3.reviews;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired private EntityManager entityManager;
	@Autowired private TestEntityManager testEntityManager;
	@Autowired private ProductRepository productRepository;
	@Autowired private CommentRepository commentRepository;
	@Autowired private ReviewRepository reviewRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindByproductName(){
		Product product = new Product();
		product.setName("Hammer");
		entityManager.persist(product);
		Optional<Product> actual = productRepository.findByName("Hammer");

		assertThat(actual).isNotNull();
		assertThat(actual.get().getName())
				.isEqualTo(product.getName());
	}

	@Test
	public void testFindByproductId(){
		Product product = new Product();
		product.setName("Hammer");
		entityManager.persist(product);
		Optional<Product> actual = productRepository.findById(1);

		assertThat(actual).isNotNull();
		assertThat(actual.get().getName())
				.isEqualTo(product.getName());
	}

	@Test
	public void testFindAllproducts(){
		Product hammer = new Product();
		hammer.setName("Hammer");
		entityManager.persist(hammer);
		Product nail = new Product();
		nail.setName("Nail");
		entityManager.persist(nail);
		List<Product> actuals = productRepository.findAll();

		assertThat(actuals).isNotNull();
		assertThat(actuals.get(0).getName().equals(hammer.getName()));
		assertThat(actuals.get(1).getName().equals(nail.getName()));
	}

	@Test
	public void testFindByCommentAuthor(){

		Comment comment = new Comment();
		comment.setAuthor("John Doe");
		comment.setContent("I hate the hammer.");

		entityManager.persist(comment);
		Optional<Comment> actual = commentRepository.findByAuthor("John Doe");

		assertThat(actual).isNotNull();
		assertThat(actual.get().getAuthor())
				.isEqualTo(comment.getAuthor());
		assertThat(actual.get().getContent())
				.isEqualTo(comment.getContent());
	}

	@Test
	public void testFindByReviewContent(){

		Review review = new Review();
		review.setAuthor("John Doe");
		review.setContent("I like the hammer.");

		entityManager.persist(review);
		Optional<Review> actual = reviewRepository.findByContent("I like the hammer.");

		assertThat(actual).isNotNull();
		assertThat(actual.get().getAuthor())
				.isEqualTo(review.getAuthor());
		assertThat(actual.get().getContent())
				.isEqualTo(review.getContent());
	}

	@Test
	public void testFindByReviewId(){

		Review review = new Review();
		review.setAuthor("John Doe");
		review.setContent("I like the hammer.");

		entityManager.persist(review);
		Optional<Review> actual = reviewRepository.findById(1);

		assertThat(actual).isNotNull();
		assertThat(actual.get().getAuthor())
				.isEqualTo(review.getAuthor());
		assertThat(actual.get().getContent())
				.isEqualTo(review.getContent());
	}

}