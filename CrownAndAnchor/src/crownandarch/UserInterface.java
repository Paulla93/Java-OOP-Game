package crownandarch;

import java.util.Scanner;

public class UserInterface {
	private Scanner sc;

	public UserInterface() {
		sc = new Scanner(System.in);
	}

	public String getName() {

		//set player name 

		System.out.print("Please enter your name: ");
		System.out.println("\nOnly letters, at least 2 characters long.");
		while (true) {
			String name= sc.nextLine();
			if (name.length() < 2){ //more than 2 characters long
				System.out.println("This name is too short!");
				continue;
			}
			else if (!name.matches("[a-zA-Z]+")){ // no numbers
				System.out.println("Letters only!");
				continue;
			}
			else {
				System.out.println("Hello, " + name); // confirming
				break;
			}
		}
		return null;
	}

	public int getStake() {

		int stake = 0 ;


		

			System.out.print("Please enter your stake: ");
			System.out.println("\nMust be above £1");

			while (!sc.hasNextInt()) { 

				System.out.println("That's not a number!"); //do not accept characters

				sc.next(); 

			} 

			stake = sc.nextInt(); 

	

		System.out.println("Your stake: £" + stake); // confirming



		return stake; 
		//mistake

	}


	public char getBetType() {



		char input = '0';
		do 
		{	
			System.out.println("");
			System.out.println("Please enter our bet type : ");
			System.out.println("Press 1 for Ordinary bet");
			System.out.println("Press 2 for Double or Nothing bet");
			System.out.println("Press 3 for All In bet");
					
			if (sc.hasNext()) 
			{
				 input = sc.next().charAt(0);
				 
				 if (input > '3' || input < '1') 
				 {
					 System.out.println("Invalid input.");
				 }
			}
			else 
			{
				System.out.println("Invalid input.");
			}
		} while (false);
		
		return input;		
	// confirming

	}


	public String getWhatOn() {


		String whatOn = "";
		char num;
		boolean valid = false;
		System.out.println("Please choose the shape you want to bet on:");
		System.out.println("Press 1 for Crown.");
		System.out.println("Press 2 for Heart.");
		System.out.println("Press 3 for Diamond.");
		System.out.println("Press 4 for Spade.");
		System.out.println("Press 5 for Club.");
		System.out.println("Press 6 for Anchor.");
		
		do {
		
			num=sc.next().charAt(0);
			switch(num) {
			case '1':
				whatOn = "Crown";
				System.out.println("You have chosen Crown");
				valid=true;
				break;
			case '2': 
				whatOn = "Heart";
				System.out.println("You have chosen Heart");
				valid=true;
				break;
			case '3' :
				whatOn = "Diamond";
				System.out.println("You have chosen Diamond");
				valid=true;
				break;
			case '4':
				whatOn = "Spade";
				System.out.println("You have chosen Spade");
				valid=true;
				break;
			case '5':
				whatOn = "Club";
				System.out.println("You have chosen Club");
				valid=true;
				break;
			case '6':
				whatOn = "Anchor";
				System.out.println("You have chosen Anchor");
				valid=true;
				break;
			default: System.out.println("Wrong value! Enter 1,2,3,4,5 or 6.");
			valid=false;
			}
			
		} while (!valid); // must be above 0 but not more than 6
		
		return whatOn;
	

	}

	public int getAmount(int stake, char typeOfBet, String whatOn) {

		int amount = 0;
		int min = 1;
		boolean valid = true;
		do {
			switch(typeOfBet) //sets minimum bet amount according to bet type
			{ 
			case '1': 
				break;
			case '2': min = 2; 
			break;
			case '3': min = 3; 
			break;
			default:
			}


			valid = true;
			amount = 0;
			System.out.println("The minimum amount for this type of bet is " + "£" + min);
			System.out.println("");
			System.out.print("Please enter the amount you would like to bet: ");

			if(sc.hasNextInt()) 
			{
				amount = sc.nextInt();
				if(amount >= min) 
				{
					if(amount - stake > 0) 
					{
						System.out.println("ERROR: You don't have that much in your stake.");
						valid = false;		
					}
				}
				else 
				{
					System.out.println("Amount entered is less than the minimum bet amount for this bet type.");
					valid = false;
				}
			}
			else 
			{
				System.out.println("ERROR: Invalid input.");
				valid = false;
				sc.next();
			}	
		}while (!valid);
		return amount;
	}



	public char getYesOrNo(String string) {
		char input = '0';
		boolean valid = true;
		
			do
			{
				valid = true;
				System.out.println(string);
				
				if(sc.hasNext()) 
				{
					input = sc.next().charAt(0);
					
					if(input != 'y' && input != 'n') 
					{
						valid = false;
						System.out.println("ERROR: Invalid input");
					}
				}
				else 
				{
					valid = false;
					System.out.println("ERROR: Invalid input");
				}
			
			}while (!valid);
			
			return input;
		}

	public void displayMessage(String prompt) 
	{
		System.out.println(prompt);
	}


}