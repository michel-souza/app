package br.com.app.domain;

public class StudioWinnerCount {
	
	private Long winnerCount;
	private String name;
	
	public StudioWinnerCount() {
	}
	
	public StudioWinnerCount(String name, Long winnerCount) {
		this.name = name;
		this.winnerCount = winnerCount;
	}

	public Long getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(Long winnerCount) {
		this.winnerCount = winnerCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
