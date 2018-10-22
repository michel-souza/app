package br.com.app.domain;

public class ProducerIntervalWinner {
	
	private ProducerIntervalWinnerData min;
	private ProducerIntervalWinnerData max;
	
	public ProducerIntervalWinner() {
		min = new ProducerIntervalWinnerData();
		max = new ProducerIntervalWinnerData();
	}

	public ProducerIntervalWinnerData getMin() {
		return min;
	}
	
	public void setMin(ProducerIntervalWinnerData min) {
		this.min = min;
	}
	
	public ProducerIntervalWinnerData getMax() {
		return max;
	}
	
	public void setMax(ProducerIntervalWinnerData max) {
		this.max = max;
	}

}
