package br.com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.Movie;
import br.com.app.domain.WinnersByYear;
import br.com.app.exception.FilmeNaoEncontradoException;
import br.com.app.exception.FilmeVencedorException;
import br.com.app.repository.GetDataRepository;
import br.com.app.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GetDataRepository getDataRepository;
	
	public Movie save(Movie movie) {
		movie.getStudios().forEach(std -> std.addMovie(movie));
		movie.getProducers().forEach(prd -> prd.addMovie(movie));
		return this.movieRepository.save(movie);
	}
	
	public Movie findByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
			
	public List<Movie> getWinnerByYear(int year) {
		return movieRepository.findWinnerByYear(year);
	}
	
	public void delete(Long id) throws Exception {
		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			if (movie.get().isWinner())
				throw new FilmeVencedorException();
			this.movieRepository.deleteById(id);
		} else {
			throw new FilmeNaoEncontradoException();
		}
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
	
	public List<WinnersByYear> getWinnersByYear() {
		return getDataRepository.getWinnersByYear();
	}		
		
}
