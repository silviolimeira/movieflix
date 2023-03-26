package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class GenreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	@JsonIgnore
	List<MovieDTO> movies = new ArrayList<>();

	public GenreDTO() {
	}

	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieDTO> getMovies() {
		return movies;
	}

	public void addMovie(MovieDTO movieDTO) {
		this.movies.add(movieDTO);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GenreDTO genreDTO = (GenreDTO) o;

		return id.equals(genreDTO.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
