package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.AuthService;

public class ReviewInsertDTO implements Serializable {
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

	public ReviewInsertDTO() {
	}

	public ReviewInsertDTO(@NotBlank String text, Long movieId, UserDTO userDTO) {
		this.text = text;
		this.movieId = movieId;
		this.user = userDTO;
	}

	public ReviewInsertDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		user = new UserDTO(entity.getUser());
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

	public void setUser(UserDTO userDTO) {
		user = userDTO;
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
		ReviewInsertDTO other = (ReviewInsertDTO) obj;
		return Objects.equals(id, other.id);
	}

}
