package com.devsuperior.movieflix.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devsuperior.movieflix.dto.GenreDTO;

@Entity
@Table(name = "tb_genre")
public class Genre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Genre() {
	}

	public Genre(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Genre(GenreDTO dto) {
		id = dto.getId();
		name = dto.getName();
	}

	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<Movie> movies = new ArrayList<>();

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Genre genre = (Genre) o;

		return Objects.equals(id, genre.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public List<Movie> getMovies() {
		return movies;
	}
}
