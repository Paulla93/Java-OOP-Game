package crownandarch;

import java.io.Serializable;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int stake;
	private int banker = 100;

	public Player (String name, int stake) {
		this.stake = stake;
		this.name=name;
	}

	public void incStake(int pstake) {
		stake+=pstake;
	}
	public void decStake (int pstake) {
		stake-=pstake;
	}

	public String getName() {
		return name;
	}

	public int getStake() {
		return stake;
	}

	public int getBanker() {
		return banker;
	}

	public void incBanker (int pstake) {
		banker= banker + pstake;
	}
	public void decBanker (int pstake) {
		banker= banker - pstake;
	}

	public void incRound(int i) {
		// TODO Auto-generated method stub
		
	}


	public String getRound() {
		// TODO Auto-generated method stub
		return null;
	}
}









