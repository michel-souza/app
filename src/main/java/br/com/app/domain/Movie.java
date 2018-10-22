package br.com.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({@NamedQuery(name = "Movie.findWinnerByYear", query = "select m from Movie m where m.winner = 'Y' and year = ?1")})
public class Movie {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private int year;
	@Column(nullable = false)
	@NotNull
	private String title;
	@OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE, orphanRemoval = false)
	private List<Studio> studios;
	@OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE, orphanRemoval = false)
	private List<Producer> producers;
	@Column
	private boolean winner;
	
	private Movie(int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Studio> getStudios() {
		return studios;
	}
	
	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}
	
	public List<Producer> getProducers() {
		return producers;
	}
	
	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public static class MovieBuilder {
		private int year;
		private String title;
		private List<Studio> studios;
		private List<Producer> producers;
		private boolean winner;
	
		public MovieBuilder year(int year) {
			this.year = year;
			return this;
		}
		
		public MovieBuilder title(String title){
			this.title = title;
			return this;
		}
		
		public MovieBuilder studios(List<Studio> studios) {
			this.studios = studios;
			return this;
		}
		
		public MovieBuilder producers(List<Producer> producers) {
			this.producers = producers;
			return this;
		}
		
		public MovieBuilder winner(boolean winner) {
			this.studios.forEach(std -> std.setWinner(winner));
			this.winner = winner;
			return this;
		}
	
		public Movie build() {
			Movie movie = new Movie(year, title, studios, producers, winner);
			return movie;		
		}
	}
}
