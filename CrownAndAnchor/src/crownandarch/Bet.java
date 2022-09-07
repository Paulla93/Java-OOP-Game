package crownandarch;

public abstract class Bet {

	protected char typeOfBet;
	protected String whatOn;
	protected int amount;

	public Bet (String symbol,int value ) {

		whatOn = symbol;
		amount = value;
	}

	public Bet(char typeOfBet, String whatsOn, int amount) {

		this.typeOfBet = typeOfBet;
		this.whatOn = whatsOn;
		this.amount = amount;

	}
	public abstract String displayBet();

	public abstract int workOutWinnings(CADice[] dice);

}


