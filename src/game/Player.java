package game;

import java.util.Random;

public class Player {

	private String name;
	private int koCount;
	
	
	public Player() {
		this("");
	}
	
	public Player(String name) {
		this.name = name;
		koCount = 5;
	}
	
	public int attack() {
		Random random = new Random();
		int rps = random.nextInt(3);
		return rps; 
	}
	
	//get and set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoCount() {
		return koCount;
	}

	public void setKoCount(int koCount) {
		this.koCount = koCount;
	}
	
	
}
