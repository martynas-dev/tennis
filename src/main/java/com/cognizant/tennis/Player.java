package com.cognizant.tennis;

public class Player {

	private String name;
	private Score score;
	
	public Player(String name) {
		this.name = name;
		score = Score.LOVE;
	}
	
	public void incrScore() {
		score = score.getNext();
	}

	public Score getScore() {
		return score;
	}
	
	public int getScoreInt() {
		return score.value();
	}

	public String getName() {
		return name;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
}
