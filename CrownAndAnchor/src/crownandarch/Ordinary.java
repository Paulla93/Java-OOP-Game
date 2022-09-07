package crownandarch;

public class Ordinary extends Bet {




	public Ordinary(char typeOfBet, String whatsOn, int amount) {
		super(typeOfBet, whatsOn, amount);
		typeOfBet  = '1';
	}


	public int workOutWinnings(CADice[] dice) 
	{		
		int matches = 0;

		int win = 0;

		for(int count = 0; count < 3; count++) { //3 dice throws
			if(dice[count].getCADice().equalsIgnoreCase(whatOn)) {
				matches ++;
				System.out.println(matches + " " + dice[count].getCADice() + " What on " + whatOn);
			}
		}
		if (matches == 1)
		{
			win =  amount * 2;
		}
		else if (matches == 2)
		{
			win =  amount * 3;
		}
		else if (matches == 3)
		{
			win =  amount * 4;
		}
		return win;
	}


	@Override
	public String displayBet() {

		return("You chave choosen ordinary bet of amount" + amount + "on  " + whatOn);

	}
}

