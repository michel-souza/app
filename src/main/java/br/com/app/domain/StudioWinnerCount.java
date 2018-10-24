package br.com.app.domain;

public class StudioWinnerCount {
	
	private Long winCount;
	private String name;
	
	public StudioWinnerCount() {
	}
	
	public StudioWinnerCount(String name, Long winCount) {
		this.name = name;
		this.winCount = winCount;
	}

	public Long getWinCount() {
		return winCount;
	}

	public void setWinCount(Long winCount) {
		this.winCount = winCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
