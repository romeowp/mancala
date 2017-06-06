package nl.sogyo.mancala;

public class Player {
	
	boolean myTurn;
	Player opponent;
	
	Player(){
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player opponent){
		this.myTurn = false;
		this.opponent = opponent;	
	}
	
	boolean hasTurn(){
		return this.myTurn;
	}
	
	Player getOpponent(){
		return this.opponent;
	}

	void startTurn(){
		this.myTurn = true;
	}
	
	void endTurn(){
		this.myTurn = false;
		this.opponent.startTurn();
	}
}
