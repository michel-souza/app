package br.com.app.domain;

public class ProducerIntervalWinnerData {
	
	private String producer;
	private int interval;
	private String previousWin;
	private String followingWin;
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public String getPreviousWin() {
		return previousWin;
	}
	
	public void setPreviousWin(String previousWin) {
		this.previousWin = previousWin;
	}
	
	public String getFollowingWin() {
		return followingWin;
	}
	
	public void setFollowingWin(String followingWin) {
		this.followingWin = followingWin;
	}

}
