package br.com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.model.Movie;
import br.com.app.repository.MovieRepository;

@Component
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie save(Movie movie) {
		return this.movieRepository.save(movie);
	}
	
	public void delete(Long id) {
		this.movieRepository.deleteById(id);
	}
	
	public Optional<Movie> get(Long id) {
		return this.movieRepository.findById(id);
	}
	
	public List<Movie> getAll() {
		Iterable<Movie> findAll = this.movieRepository.findAll();
		List<Movie> movies = new ArrayList<Movie>();
		
		findAll.forEach(movies::add);
		
		return movies;
	}
}
