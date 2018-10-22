package br.com.app.domain;

import java.util.ArrayList;
import java.util.List;

public class ProducerWinner {
	
	private String producerName;
	private int yearWinner;
	private List<Long> years = new ArrayList<>();
	
	public ProducerWinner() {
	}
	
	public ProducerWinner(String name, int year) {
		this.producerName = name;
		this.yearWinner = year;
		this.years.add(Long.valueOf(year));
	}
	
	public String getProducerName() {
		return producerName;
	}
	
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	public int getYearWinner() {
		return yearWinner;
	}
	
	public void setYearWinner(int yearWinner) {
		this.yearWinner = yearWinner;
	}

	public List<Long> getYears() {
		return years;
	}

	public void setYears(List<Long> years) {
		this.years = years;
	}
	
	public void addYear(Long year) {
		this.years.add(year);
	}

}
