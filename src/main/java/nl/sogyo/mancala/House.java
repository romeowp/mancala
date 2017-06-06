package nl.sogyo.mancala;

public class House extends Pit {
	
	static final int pitsPerPlayer = 7;
	
	House(){
		this(new int[]{4,4,4,4,4,4,0,4,4,4,4,4,4,0});
	}
	
	House(int[] initialSeeds){
		Player owner = new Player();
		this.setNeighbor(new House(initializePit(initialSeeds, owner), this, owner));
	}

	House(int[] initialSeeds, House firstHouse, Player owner){
		initialSeeds = initializePit(initialSeeds, owner);
		if((initialSeeds.length-1)%pitsPerPlayer == 0){
			this.setNeighbor(new Store(initialSeeds, firstHouse, owner));
		}else{
			this.setNeighbor(new House(initialSeeds, firstHouse, owner));
		}
	}
	
	void sowSeeds(){
		int seeds = this.getSeeds();
		this.emptyStockpile();
		this.getNeighbor().takeAndPass(seeds);
	}
	
	void passAlong(int remainingSeeds){
		this.takeAndPass(remainingSeeds);
	}
	
	void finalAct(){
		House opposing = (House) this.identifyOpposing(0);
		if(this.getSeeds() == 1 && opposing.getSeeds() > 0){
			this.handOverSeeds();
			opposing.handOverSeeds();
		}
		if(housesEmpty(this.getOwner(), 0)){
			this.getFinalScore();
			// End of the game; current player wins if more than half of the seeds are in their store;
		}
		this.getOwner().endTurn();
	}
	
	House identifyOpposing(int distanceToStore){
		return this.getNeighbor().identifyOpposing(++distanceToStore);
	}
	
	void handOverSeeds(){
		int seeds = this.getSeeds();
		this.emptyStockpile();
		this.getNeighbor().passToStore(seeds);
	}
	
	void passToStore(int seeds){
		this.getNeighbor().passToStore(seeds);
	}
	
	boolean housesEmpty(Player player, int housesChecked){
		if(this.getOwner() == player){
			if(this.getSeeds() > 0){
				return false;
			}
			if(this.getSeeds() == 0){
				housesChecked++;
			}
			if(housesChecked == pitsPerPlayer-1){
				return true;
			}
		}
		return this.getNeighbor().housesEmpty(player, housesChecked);
	}
	
	int getFinalScore(){
		return this.getNeighbor().getFinalScore();
	}
}
