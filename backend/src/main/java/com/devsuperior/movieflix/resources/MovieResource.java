package com.devsuperior.movieflix.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewMovieDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	private static Logger logger = LoggerFactory.getLogger(MovieResource.class);

	@Autowired
	MovieService movieService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<Page<MovieGenreDTO>> find(@RequestParam(name = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable) {
		if (genreId == 0)
			return ResponseEntity.ok().body(movieService.findAllOrderByTitle(pageable));
		else
			return ResponseEntity.ok().body(movieService.find(genreId, pageable));
	}

	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewMovieDTO>> findReviews(@PathVariable Long id) {
		MovieDTO movieDTO = movieService.findById(id);

		List<ReviewMovieDTO> result = movieDTO.getReviewsDTO().stream().map(r -> {
			UserDTO userDTO = userService.findById(r.getUserId());
			// (Long id, @NotBlank String text, Long movieId, UserDTO user) {

			return new ReviewMovieDTO(r.getId(), r.getText(), r.getMovieId(), userDTO);
		}).collect(Collectors.toList());

		return ResponseEntity.ok().body(result);
	}

}
