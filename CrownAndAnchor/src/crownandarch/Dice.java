package crownandarch;
public class Dice {
	//attributes
	private int diceFace;

	//constructors
	//click in source, and then towards the button Generate Constructor Using Fields
	//generate getters and setters
	public Dice() {
	throwDice();
	}
	public Dice(int value) {
	diceFace = value;
	}

	public int getDice() {
	return diceFace;
	}

	public void throwDice() {
	double randomNo = Math.random();
	randomNo = 6 * randomNo;
	randomNo++;
	diceFace = (int) randomNo;
	}

	
	
}




	

	