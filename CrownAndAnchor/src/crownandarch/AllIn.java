package crownandarch;

public class AllIn extends Bet{



	public AllIn(char typeOfBet, String whatsOn, int amount) {
		super(typeOfBet, whatsOn, amount);
		typeOfBet  = '3';
	}

	@Override
	public String displayBet() {

		return("You chave choosen all in bet of amount" + amount + "on  " + whatOn);

	}

	public int workOutWinnings(CADice[] dice) {

		int matches=0;

		int win=0;

		for(int i = 0;i < 3;i++) // 3 dice throws
		{


			if (matches == 3)
			{
				win = (amount + amount * 9);
			}

		}

		return win;
	}

}

