package br.com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.domain.Movie;
import br.com.app.domain.StudioWinnerCount;
import br.com.app.domain.WinnersByYear;
import br.com.app.repository.MovieRepository;
import br.com.app.repository.WinnersByYearRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private WinnersByYearRepository winnersByYearRepository;
	
	public Movie save(Movie movie) {
		return this.movieRepository.save(movie);
	}
	
	public void delete(Movie movie) throws Exception {
		if (movie.isWinner())
			throw new Exception("Filmes vencedores não podem ser excluidos!");
		delete(movie.getId());
	}
	
	public List<Movie> getWinnerByYear(int year) {
		return movieRepository.findWinnerByYear(year);
	}
	
	private void delete(Long id) {		
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
	
	public List<WinnersByYear> getWinnersByYear() {
		return winnersByYearRepository.getWinnersByYear();
	}
	
	public List<StudioWinnerCount> getStudiosWinners() {
		return winnersByYearRepository.getStudiosByCountWinner();
	}
		
}
