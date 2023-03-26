package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MovieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	private String synopsis;

	GenreDTO genre;

	@JsonIgnore
	private List<ReviewDTO> reviewsDTO = new ArrayList<>();

	public MovieDTO() {
	}

	public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
	}

	public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis,
			GenreDTO genreDTO) {
		this(id, title, subTitle, year, imgUrl, synopsis);
		this.genre = genreDTO;
	}

	public MovieDTO(Long movieId) {
		id = movieId;
	}

	public MovieDTO(Movie entity) {
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();
		genre = new GenreDTO(entity.getGenre());
	}

	public MovieDTO(Movie entity, List<Review> reviews) {
		this(entity);
		this.reviewsDTO = reviews.stream().map(r -> new ReviewDTO(r)).collect(Collectors.toList());
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<ReviewDTO> getReviewsDTO() {
		return reviewsDTO;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MovieDTO movieDTO = (MovieDTO) o;

		return id.equals(movieDTO.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
