package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.AuthService;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	AuthService authService;

	@Autowired
	MovieRepository movieRepository;

	private Long id;

	@NotBlank
	private String text;

	private Long movieId;

	private Long userId;

	public ReviewDTO() {
	}

	public ReviewDTO(@NotBlank String text, Long movieId, Long userId) {
		this.text = text;
		this.movieId = movieId;
		this.userId = userId;
	}

	public ReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		userId = entity.getUser().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		return Objects.equals(id, other.id);
	}

}
