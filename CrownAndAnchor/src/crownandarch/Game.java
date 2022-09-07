
package crownandarch;
import java.io.*;
import java.util.ArrayList;


public class Game 
{
	
	private CADice[] dice = new CADice[3];
	private ArrayList<Bet> bets = new ArrayList<>();
	private Player aPlayer;
	private UserInterface ui = new UserInterface();
	
	public Game() {
		 for (int i = 0; i < dice.length; i++) 
		 {
			 dice[i] = new CADice();
		 }
	}
		
	public void play() throws ClassNotFoundException, IOException
	{
		
		aPlayer = restartGame();
		boolean cont = false;
		char roundResult;
		
		do 
		{			
			aPlayer.incRound(1);
			placeBets();
			rolldice();
			ui.displayMessage("Rolling dice...");
			ui.displayMessage("Dice 1:- " + dice[0].getCADice());
			ui.displayMessage("Dice 2:- " + dice[1].getCADice());
			ui.displayMessage("Dice 3:- " + dice[2].getCADice());

			int winnings = workOutWinnings();
			updatePlayerStake(0, winnings);			
			System.out.println("");
			ui.displayMessage("You won: £"+winnings);
			ui.displayMessage("Banker now has: £" + aPlayer.getBanker());
			ui.displayMessage("Stake is now at: £" + aPlayer.getStake());
			System.out.println("");
		
			roundResult = endRound();//checks banker stake
			
			if(roundResult == 'c' && ui.getYesOrNo("Play another round? (Y/N)") == 'y') //if game not over and user wants to play another round
			{ 
				cont = true;
			}
			else 
			{
				cont = false;
			}		
		}while (cont);
		
		if(roundResult == 'c') //if user doesn't want to continue continue and game not over
		{
			saveGame(aPlayer); // save data
		}	
	}
	
	private Player restartGame() throws IOException, ClassNotFoundException 
	{
		String name = ui.getName();
		
		try 
		{
			
			FileInputStream inFile = new FileInputStream(name + ".ser");
			ObjectInputStream inPlayer = new ObjectInputStream(inFile);
			aPlayer = (Player) inPlayer.readObject();
			inPlayer.close();
			inFile.close();
			ui.displayMessage("Previous game found for this player with:"
							+ "\nStake: £" + aPlayer.getStake()
							+ "\nBanker: £" + aPlayer.getBanker()
							+ "\nRounds played: " + aPlayer.getRound());
			if(ui.getYesOrNo("Load this game? (Y/N)") == 'n') 
			{
				aPlayer = new Player(name, ui.getStake());
			}
			else 
			{
				ui.displayMessage(aPlayer.getName() + "'s game loaded.\nStake: " + aPlayer.getStake() + "\nBanker: " + aPlayer.getBanker() + "\nRounds played: " + aPlayer.getRound());
			}
		} catch (FileNotFoundException e) 
		{
			ui.displayMessage("No save file found for this name");
			ui.displayMessage("Creating new save game...");
			aPlayer = new Player(name, ui.getStake());
		}
		catch (IOException | ClassNotFoundException e) 
		{
			ui.displayMessage("No save file found for this name");
			ui.displayMessage("Creating new save game...");
			aPlayer = new Player(name, ui.getStake());
		}
		return aPlayer;	
	}
	
	private int workOutWinnings() 
	{
		int winnings = 0;

		for (Bet bet: bets) 
		{	
			ui.displayMessage(bet.displayBet());
			winnings += bet.workOutWinnings(dice);
		}		
		return winnings;
	}
		
	private void placeBets() //get bets and add them to array
	{
		
		bets.clear(); //initialise array every time method called
		
		char proceed = 'y';
		
		do //get bet details from user
		{			
			char betType = ui.getBetType();
			String whatOn = ui.getWhatOn();
			int amt = ui.getAmount(aPlayer.getStake(), betType, whatOn);
			
			switch(betType) //Add bet to array and decrement stake by bet amount
			{
			case '1': Ordinary tempOrd = new Ordinary(betType, whatOn, amt);			  		  
					  bets.add(tempOrd); updatePlayerStake(amt,0); 
					  ui.displayMessage(tempOrd.displayBet());
			break;
			case '2': DoubleOrNothing tempDoN = new DoubleOrNothing(betType, whatOn, amt);
					  bets.add(tempDoN); updatePlayerStake(amt, 0); 
					  ui.displayMessage(tempDoN.displayBet());
			break;			
			case '3': AllIn tempAi = new AllIn(betType, whatOn, amt);					 
			  		  bets.add(tempAi); updatePlayerStake(amt, 0); 
					  ui.displayMessage(tempAi.displayBet());
			break;
			default:
			}
			ui.displayMessage("");
			ui.displayMessage("bet of £" + amt + " placed on " + whatOn);
			ui.displayMessage("Your stake is now £" + aPlayer.getStake());
			ui.displayMessage("");
			proceed = ui.getYesOrNo("Would you like to place any more bets? (Y/N)");//asks user if any more bets

		}while (proceed == 'y');
	}
	
	private void updatePlayerStake(int amt, int winnings) //edits bet/winnings from player stake and banker
	{
		
		if (aPlayer.getBanker() - winnings <=0) //banker can't pay out more than available
		{
			aPlayer.incStake(aPlayer.getBanker());
			aPlayer.decBanker(aPlayer.getBanker());
		}
		else 
		{
			aPlayer.decStake(amt);
			aPlayer.incBanker(amt);
			aPlayer.incStake(winnings);
			aPlayer.decBanker(winnings);	
		}
	}

	private void rolldice() //randomise values in dice array
	{
				 
		 for (int i = 0; i < dice.length; i++) 
		 {
			 
			 dice[i].throwDice();
			 dice[i].getCADice();
		 }
		 

	}

	private char endRound() 
	{
		int stake = aPlayer.getStake();
		int banker = aPlayer.getBanker();
		char result = '0';
				
		if (stake <= 0 ) 
		{
			ui.displayMessage("YOU LOSE - you've run out of money.\nGAME OVER");
			result = 'l';
		}
		else if (banker <=0) 
		{
			ui.displayMessage("YOU WON - Banker has run out of money.\nGAME OVER");
			aPlayer.incStake(100);
			result = 'w';
		}
		else 
		{
			ui.displayMessage("End of the round. You have: £" + stake +"\nBanker has: £" + banker);
			result = 'c';
		}
		
		return result;
	
	}

	private void saveGame(Player aPlayer) 
	{	
	    if (ui.getYesOrNo("Save before quitting? (Y/N)") == 'y') 
	    {
	    	try 
	    	{
	    		FileOutputStream fileOut = new FileOutputStream(aPlayer.getName().toLowerCase() + ".ser");
	    		ObjectOutputStream outPlayer = new ObjectOutputStream(fileOut);
	    		outPlayer.writeObject(aPlayer);
	    		outPlayer.close();
	    		fileOut.close();
	    	}
	    	catch (IOException e) 
	    	{
	      		e.printStackTrace();
	      	}
	    }
	    else 
	    {
	    	return;
	    }
	
	}

}