package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {
	
	@Test
	public void turnShouldEmptyHouse(){
		House house = new House();
		house.sowSeeds();
		Assert.assertEquals(0, house.getSeeds());
	}

	@Test
	public void aSeedShouldBeSownInSubsequentHouseAfterTurn(){
		House house = new House();
		Pit neighbor = house.getNeighbor();
		int seedsBeforeTurn = neighbor.getSeeds();
		house.sowSeeds();
		int seedsAfterTurn = neighbor.getSeeds();
		Assert.assertEquals(seedsBeforeTurn+1, seedsAfterTurn);
	}
	
	@Test
	public void aSeedShouldBeSownInFourthNeighborIfHouseWithFourSeedsWasChosen(){
		House house = new House();
		Pit neighbor4 = house.nthNeighbor(4);
		int seedsBeforeTurn = neighbor4.getSeeds();
		house.sowSeeds();
		int seedsAfterTurn = neighbor4.getSeeds();
		Assert.assertEquals(seedsBeforeTurn+1, seedsAfterTurn);
	}
	
	@Test
	public void noSeedShouldBeSownInFifthNeighborIfHouseWithFourSeedsWasChosen(){
		House house = new House();
		Pit neighbor5 = house.nthNeighbor(5);
		int seedsBeforeTurn = neighbor5.getSeeds();
		house.sowSeeds();
		int seedsAfterTurn = neighbor5.getSeeds();
		Assert.assertEquals(seedsBeforeTurn, seedsAfterTurn);
	}
	
	@Test
	public void houseShouldBeItsOwnFourteenthNeighbor(){
		House house = new House();
		Pit neighbor14 = house.nthNeighbor(14);
		Assert.assertEquals(house, neighbor14);
	}

	@Test
	public void seventhPitShouldBeAStore(){
		House house1 = new House();
		Pit pit7 = house1.nthNeighbor(6);
		Assert.assertTrue(pit7 instanceof Store);
	}
	
	@Test
	public void eighthPitShouldBeAHouse(){
		House house1 = new House();
		Pit pit8 = house1.nthNeighbor(7);
		Assert.assertTrue(pit8 instanceof House);
	}
	
	@Test
	public void fourteenthPitShouldBeAStore(){
		House house1 = new House();
		Pit pit14 = house1.nthNeighbor(13);
		Assert.assertTrue(pit14 instanceof Store);
	}
	
	@Test
	public void firstHouseAndFirstStoreShouldBelongToSameOwner(){
		House house1 = new House();
		Pit store1 = house1.nthNeighbor(6);
		Assert.assertEquals(house1.getOwner(), store1.getOwner());
	}
	
	@Test
	public void opposingHouseShouldBelongToOpponentOfOwner(){
		House house = new House();
		Pit opposing = house.nthNeighbor(7);
		Assert.assertEquals(house.getOwner().getOpponent(), opposing.getOwner());
	}
	
	@Test
	public void duringFirstPlayersTurnItShouldNotBeSecondPlayersTurn(){
		House house = new House();
		Player player1 = house.getOwner();
		Player player2 = player1.getOpponent();
		Assert.assertEquals(!player1.hasTurn(), player2.hasTurn());
	}
	
	@Test
	public void playerTurnShouldEndAfterSowing(){
		House house = new House();
		Player player1 = house.getOwner();
		house.sowSeeds();
		Assert.assertTrue(!player1.hasTurn());
	}
	
	public void secondPlayerTurnShouldStartAfterFirstPlayerTurn(){
		House house = new House();
		Player player1 = house.getOwner();
		Player player2 = player1.getOpponent();
		house.sowSeeds();
		Assert.assertTrue(player2.hasTurn());
	}
	
	public void playerShouldKeepTurnIfLastSeedIsSowedInStore(){
		House house1 = new House();
		House house3 = (House) house1.nthNeighbor(2);
		Player player1 = house3.getOwner();
		house3.sowSeeds();
		Assert.assertTrue(player1.hasTurn());
	}
	
	public void playerShouldSowSeedInOwnStore(){
		House house1 = new House();
		House house3 = (House) house1.nthNeighbor(2);
		Pit store1 = house3.nthNeighbor(4);
		Player player1 = store1.getOwner();
		house3.sowSeeds();
		Assert.assertTrue(player1.hasTurn());
	}
	
}
