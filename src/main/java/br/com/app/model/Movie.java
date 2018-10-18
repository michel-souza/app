package br.com.app.model;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class Movie {
	
	private int year;
	private String title;
	private List<String> studios;
	private List<String> producers;
	private boolean winner;
	
	private Movie(int year, String title, List<String> studios, List<String> producers, boolean winner) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}
	
	public Movie() {
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
	
	public List<String> getStudios() {
		return studios;
	}
	
	public void setStudios(List<String> studios) {
		this.studios = studios;
	}
	
	public List<String> getProducers() {
		return producers;
	}
	
	public void setProducers(List<String> producers) {
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
		private List<String> studios;
		private List<String> producers;
		private boolean winner;
	
		public MovieBuilder year(int year) {
			this.year = year;
			return this;
		}
		
		public MovieBuilder title(String title){
			this.title = title;
			return this;
		}
		
		public MovieBuilder studios(List<String> studios) {
			this.studios = studios;
			return this;
		}
		
		public MovieBuilder producers(List<String> producers) {
			this.producers = producers;
			return this;
		}
		
		public MovieBuilder winner(boolean winner){
			this.winner = winner;
			return this;
		}
	
		public Movie build() {
			Movie movie = new Movie(year, title, studios, producers, winner);
			return movie;		
		}
	}
}
