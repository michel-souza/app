package br.com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.app.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
	
	public List<Movie> findWinnerByYear(int year);
	

}
