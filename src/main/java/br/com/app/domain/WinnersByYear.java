package br.com.app.domain;

public class WinnersByYear {
	
	private Long count;
	private int year;
	
	public WinnersByYear() {
	}
	
	public WinnersByYear(int year, Long count) {
		this.count = count;
		this.year = year;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
