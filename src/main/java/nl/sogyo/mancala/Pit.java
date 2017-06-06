package nl.sogyo.mancala;

public abstract class Pit {
	
	private int seeds;
	private Pit neighbor;
	private Player owner;
	
	int getSeeds(){
		return this.seeds;
	}
	
	void emptyStockpile(){
		this.seeds = 0;
	}
	
	void addSeed(){
		this.seeds += 1;
	}
	
	Pit getNeighbor(){
		return this.neighbor;
	}
	
	void setNeighbor(Pit neighbor){
		this.neighbor = neighbor;
	}
	
	Pit nthNeighbor(int nn){
		if(nn > 0){
			return this.neighbor.nthNeighbor(nn-1);
		}
		return this;
	}
	
	Player getOwner(){
		return this.owner;
	}
	
	void setOwner(Player owner){
		this.owner = owner;
	}
	
	int[] initializePit(int[] initialSeeds, Player owner){
		this.seeds = initialSeeds[0];
		this.owner = owner;
		int[] initialSeedsRemaining = new int[initialSeeds.length - 1];
		for(int ii = 0; ii < initialSeedsRemaining.length; ii++){
			initialSeedsRemaining[ii] = initialSeeds[ii+1];			
		}
		return initialSeedsRemaining;
	}
	
	abstract void passAlong(int remainingSeeds);
	
	void takeAndPass(int remainingSeeds){
		remainingSeeds -= 1;
		this.addSeed();
		if(remainingSeeds > 0){
			this.getNeighbor().passAlong(remainingSeeds);
		}else{
			this.finalAct();
		}
	}
	
	abstract void finalAct();
	
	abstract House identifyOpposing(int distanceToStore);
	
	abstract void passToStore(int seeds);
	
	abstract boolean housesEmpty(Player player, int housesChecked);
	
	abstract int getFinalScore();
	
}
