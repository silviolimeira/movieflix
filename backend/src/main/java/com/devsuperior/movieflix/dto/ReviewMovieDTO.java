package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.AuthService;

public class ReviewMovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	AuthService authService;

	@Autowired
	MovieRepository movieRepository;

	private Long id;

	@NotBlank
	private String text;

	private Long movieId;

	private UserDTO user;

	public ReviewMovieDTO() {
	}

	public ReviewMovieDTO(Long id, @NotBlank String text, Long movieId, UserDTO user) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.user = user;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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
		ReviewMovieDTO other = (ReviewMovieDTO) obj;
		return Objects.equals(id, other.id);
	}

}
