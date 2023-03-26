package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.List;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public class MovieGenreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;

	public MovieGenreDTO() {
	}

	public MovieGenreDTO(Long id, String title, String subTitle, Integer year, String imgUrl) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
	}

	public MovieGenreDTO(Long movieId) {
		id = movieId;
	}

	public MovieGenreDTO(Movie entity) {
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
	}

	public MovieGenreDTO(Movie entity, List<Review> reviews) {
		this(entity);
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MovieGenreDTO movieDTO = (MovieGenreDTO) o;

		return id.equals(movieDTO.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
