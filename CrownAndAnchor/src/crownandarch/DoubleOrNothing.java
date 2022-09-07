package crownandarch;

public class DoubleOrNothing extends Bet{



	public DoubleOrNothing(char typeOfBet, String whatsOn, int amount) {
		super(typeOfBet, whatsOn, amount);
		typeOfBet  = '2';
	}

	@Override
	public String displayBet() {
		return("You chave choosen double or nothing bet of amount" + amount + "on  " + whatOn);

	}
	public int workOutWinnings(CADice[] dice) {

		int matches=0;

		int win=0;

		
		for(int count = 0; count < 3; count++) { //3 dice throws
			if(dice[count].getCADice().equalsIgnoreCase(whatOn))
			matches ++;
		}

			if (matches == 2)
			{
				win = (amount + amount * 4);
			}
			else if (matches == 3)
			{
				win = (amount + amount * 4); //??

			}
		
		return win;
	}

}
