package br.com.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.app.model.Movie;

public interface MovieRepository extends CrudRepository<Movie,Long> {

}
