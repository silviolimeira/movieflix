package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	MovieRepository repository;

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findAllOrderByTitle(Pageable pageable) {
		Page<Movie> page = repository.findAll(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "title")));

		return page.map(x -> new MovieGenreDTO(x));

	}

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> find(Long genreId, Pageable pageable) {
		Page<Movie> list = repository.find(genreId, pageable);
		return list.map(x -> new MovieGenreDTO(x));
	}

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity, entity.getReviews());
	}

}
