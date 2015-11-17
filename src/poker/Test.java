package poker;

import java.util.ArrayList;
import java.util.Vector;

import junit.framework.TestCase;

public class Test extends TestCase {
	
	private Card[] sample_cards = {
			Card.FIVE_DIAMONDS,
			Card.ACE_CLUBS,
			Card.ACE_DIAMONDS,
			Card.ACE_HEARTS,
			Card.ACE_SPADES};
	private Card[] sample_cards2 = {
			Card.FIVE_DIAMONDS,
			Card.ACE_CLUBS,
			Card.EIGHT_CLUBS,
			Card.JACK_HEARTS,
			Card.TEN_SPADES};
	private Card[] con_flush_cards = {
			Card.TWO_SPADES,
			Card.THREE_SPADES,
			Card.FIVE_SPADES,
			Card.SIX_SPADES,
			Card.FOUR_SPADES};
	private Card[] con_cards = {
			Card.FOUR_SPADES,
			Card.TWO_SPADES,
			Card.THREE_CLUBS,
			Card.FIVE_HEARTS,
			Card.SIX_SPADES};
	private Card[] con_ace_cards = {
			Card.ACE_CLUBS,
			Card.TWO_SPADES,
			Card.THREE_CLUBS,
			Card.FOUR_SPADES,
			Card.FIVE_HEARTS};
	private Card[] two_pair = {
			Card.ACE_CLUBS,
			Card.ACE_DIAMONDS,
			Card.EIGHT_CLUBS,
			Card.EIGHT_DIAMONDS,
			Card.KING_CLUBS
	};
	private Vector<Card> cards = new Vector<Card>(5);
	
	private Hand black;
	
	public void testTwoPair(){
		for(Card c:two_pair){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s ,%d %n",c, c.rank,c.rank.ordinal());			
		}
		
		black = new Hand(cards);
		
		System.out.println(black.getRanksize());
		System.out.println(black.getDistinctRankSize());

		System.out.println("Highest: "+black.getHighestRank());
		assertEquals(Mode.Two_pair,black.getMode());
	}
	
	public void testAddition() {
		for(Card c:sample_cards){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s%n",c, c.rank);			
		}
		
		black = new Hand(cards);
		
		System.out.println(black.getRanksize());
		System.out.println(black.getDistinctRankSize());
		assertEquals(4, black.getSuitsize());
		assertEquals(5, black.getRanksize());
		assertEquals(2, black.getDistinctRankSize());
	}
	
	public void testHighCard() {
		clear();
		for(Card c:sample_cards2){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s%n",c, c.rank);			
		}
		
		black = new Hand(cards);
		
		System.out.println(black.getRanksize());
		System.out.println(black.getDistinctRankSize());
		System.out.println(black.getMode());
		assertEquals(4, black.getSuitsize());
		assertEquals(5, black.getRanksize());
		assertEquals(5, black.getDistinctRankSize());
	}
	
	public void testConsecutive(){
		clear();
		for(Card c:con_cards){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s%n",c, c.rank);			
		}
		
		black = new Hand(cards);
		assertEquals(true, black.isConsecutive(black.getRank()));
	}
	
	public void testAceConsecutive(){
		clear();
		for(Card c:con_ace_cards){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s%n",c, c.rank);			
		}
		
		black = new Hand(cards);
		assertEquals(true, black.isConsecutive(black.getRank()));
	}
	
	public void testFivedistinctCard(){
		clear();
		for(Card c:con_cards){
			cards.add(c);
		}
		for(Card c: cards){
			System.out.printf("Your card'rank on %s is %s%n",c, c.rank);			
		}
		
		black = new Hand(cards);
		
		System.out.println(black.getRanksize());
		System.out.println(black.getDistinctRankSize());
		System.out.println(black.getMode());
		assertEquals(3, black.getSuitsize());
		assertEquals(5, black.getRanksize());
		assertEquals(5, black.getDistinctRankSize());
		assertEquals(Mode.Flush, black.getMode());
	}
	
	public void clear(){
		cards.clear();
		black = new Hand(cards);
	}

}
