package crownandarch;

public class CADice extends Dice{

	public String getCADice() {


		String getDiceSymbol = "";

		switch(getDice()) {

		case 1: getDiceSymbol = "Crown";
		break;
		case 2: getDiceSymbol = "Heart";
		break;
		case 3: getDiceSymbol = "Diamond";
		break;
		case 4: getDiceSymbol = "Spade";
		break;
		case 5: getDiceSymbol = "Club";
		break;
		case 6: getDiceSymbol = "Anchor";
		break;
		default: getDiceSymbol = "";
		}
		return getDiceSymbol;
	}

}
