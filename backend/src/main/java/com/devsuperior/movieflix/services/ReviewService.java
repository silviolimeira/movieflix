package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewInsertDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.AuthService;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	private static Logger logger = LoggerFactory.getLogger(ReviewService.class);

	@Autowired
	private AuthService authService;

	@Autowired
	ReviewRepository repository;
	@Autowired
	private MovieRepository movieRepository;

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll() {
		return repository.findAll().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}

	@PreAuthorize("hasAnyRole('MEMBER')")
	@Transactional
	public ReviewInsertDTO insert(ReviewInsertDTO dto) {
		logger.info("Insert Review...");
		Review entity = new Review();
		dto.setUser(new UserDTO(authService.authenticated()));

		Movie movie = movieRepository.getOne(dto.getMovieId());
		entity.setMovie(movie);
		entity.setText(dto.getText());
		entity.setUser(authService.authenticated());

		entity = repository.save(entity);
		dto.setId(entity.getId());
		return dto;
	}

	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<Review> obj = repository.findById(id);
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTO(entity);
	}

}
