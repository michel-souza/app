package br.com.app.domain;

public class WinnersByYear {
	
	private Long winnerCount;
	private int year;
	
	public WinnersByYear() {
	}
	
	public WinnersByYear(int year, Long count) {
		this.winnerCount = count;
		this.year = year;
	}

	public Long getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(Long winnercount) {
		this.winnerCount = winnercount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
