package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String name;

	@Email(message = "Favor entrar um email válido")
	private String email;

	@JsonIgnore
	Set<RoleDTO> roles = new HashSet<>();

	@JsonIgnore
	List<ReviewDTO> reviewsDTO = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public List<ReviewDTO> getReviewsDTO() {
		return reviewsDTO;
	}

	public void addReview(ReviewDTO reviewDTO) {
		this.reviewsDTO.add(reviewDTO);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

}
