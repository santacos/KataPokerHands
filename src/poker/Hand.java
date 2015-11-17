package poker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class Hand {
	private Vector<Card> cards;
	private Mode mode;
	private Set<Suit> suits = EnumSet.noneOf(Suit.class);
	private Vector<Rank> ranks = new Vector<Rank>();
	//private Multiset<Rank> ranks = EnumMultiset.create(Rank.class);
	private Set<Rank> distinctRank = EnumSet.noneOf(Rank.class);
	private HashMap<Rank, Integer> cache = new HashMap<Rank, Integer>();
	private Rank firstRank = null;
	
	private RankComparator comparatorRank = new RankComparator();
	
	public Hand(Vector<Card> cards){
		
		for(Card c:cards){
			suits.add(c.suit);
			ranks.add(c.rank);
			distinctRank.add(c.rank);
			
			if(cache.containsKey(c.rank)){
				Integer value = cache.get(c.rank);
				cache.put(c.rank, value+1);
			}else{
				cache.put(c.rank,1);
			}
			
			
		}
		
		//sort by count and then rank. 3 3 3 5 4
		ranks.sort(comparatorRank);
		
		
		System.out.println("--cache--");
		int i=0;
		for(Entry<Rank,Integer> r:cache.entrySet()){
			System.out.println("rank : "+r.getKey().toString() + " value: "+r.getValue().intValue());
			if(i==0)firstRank = r.getKey();
			i++;
		}	
		
		//check mode
		boolean isFlush = (suits.size()==1)? true :false;
		int countDistinct = distinctRank.size();
		
		
		//all different rank
		if(countDistinct==5){
			if(isConsecutive(ranks)){
				mode = isFlush? Mode.Straight_flush : Mode.Flush;
			}else{
				mode = isFlush? Mode.Flush : Mode.High_card;
			}
		}
		else if (countDistinct == 4) {
			mode = Mode.Pair;
		}
		
		else if (countDistinct == 3) {
			mode = cache.get(firstRank).intValue() == 2 ? Mode.Two_pair : Mode.Three_of_a_kind;
			
		}
		else {
			mode = cache.get(firstRank).intValue() == 3 ? Mode.Full_house : Mode.Four_of_a_kind;
		}
	}
	
	public boolean isConsecutive(Vector<Rank> rank){
		//case Ace,one,two,three,four
		if(rank.firstElement().equals(Rank.ACE)&& rank.get(1).equals(Rank.FIVE)){
			int second = rank.get(1).ordinal();
			int last = rank.lastElement().ordinal();
			if(second-last==3)return true;
			return false;
		}
		
		int first = rank.firstElement().ordinal();
		int last = rank.lastElement().ordinal();
		if(first-last==4) return true;
		return false;
	}
	
	public int getHighestRank(){
		return firstRank.ordinal();
	}
	
	public int getSuitsize(){
		return suits.size();
	}
	
	public int getRanksize(){
		return ranks.size();
	}
	
	public int getDistinctRankSize(){
		return distinctRank.size();
	}
	
	public Enum<Mode> getMode(){
		return mode;
	}
	
	public Vector<Rank> getRank(){
		return ranks;
	}
	
	protected static class RankComparator implements Comparator {
        /**
         * Method for compairing two objects, TypeSafeEnums in this case. The
         * comparison is made on the ordinal values.
         * @param a First object to be compared
         * @param b Second object to be compared.
         * @return The value 0 if <tt>a</tt> is numerically equal to <tt>b</tt>; a value less than 0 if
         * <tt>a</tt> is numerically less than <tt>b</tt>; and a value greater than 0  if <tt>a</tt>
         * is numerically greater than <tt>b</tt>.
         */
        public int compare(Object a, Object b) {
            return ((Rank)b).ordinal() - ((Rank)a).ordinal();
        }
    }
	
	
	
	
	
}
