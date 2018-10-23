package br.com.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "studio")
@NamedQuery(name = "studio.getStudioByName", query = "select s from Studio s where lower(s.name) = lower(?1)")
public class Studio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@ManyToMany(mappedBy = "studios", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Movie> movies = new ArrayList<>();
	@Column
	private boolean winner;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
