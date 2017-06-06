package nl.sogyo.mancala;

public class Store extends Pit {

	Store(int[] initialSeeds, House house1, Player player){
		initialSeeds = initializePit(initialSeeds, player);
		if(initialSeeds.length == 0){
			this.setNeighbor(house1);
		}else{
			this.setNeighbor(new House(initialSeeds, house1, player.getOpponent()));
		}
	}
	
	void passAlong(int remainingSeeds){
		if(this.getOwner().hasTurn()){
			this.takeAndPass(remainingSeeds);
		}else{
			this.getNeighbor().takeAndPass(remainingSeeds);
		}
	}
	
	void finalAct(){
		// Do nothing; player keeps turn.
	}
	
	House identifyOpposing(int distanceToStore){
		return (House) this.nthNeighbor(distanceToStore);
	}
	
	void passToStore(int seeds){
		for(int ii = 0; ii < seeds; ii++){
			this.addSeed();
		}
	}
	
	boolean housesEmpty(Player player, int housesChecked){
		return this.getNeighbor().housesEmpty(player, housesChecked);
	}
	
	int getFinalScore(){
		return this.getSeeds();
	}

}
