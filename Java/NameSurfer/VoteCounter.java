/*
 * File: VoteCounter.java
 * ---------------------
 * A sandcastle program that uses collections to tally votes 
 */

import acm.program.*;
import java.util.*;

public class VoteCounter extends ConsoleProgram {
	public void run() {
		ArrayList<String> votes = new ArrayList<String>();
		votes.add("Zaphod Beeblebrox");
		votes.add("Arthur Dent");
		votes.add("Trillian McMillian");
		votes.add("Zaphod Beeblebrox");
		votes.add("Marvin");
		votes.add("Mr. Zarniwoop");
		votes.add("Trillian McMillian");
		votes.add("Zaphod Beeblebrox");
		printVoteCounts(votes);
		
	}
	
	/*
	 * Your job is to implement this method according to 
	 * the problem specification. 
	 */
	private void printVoteCounts(ArrayList<String> votes) {
		int length = votes.size();
		
		HashMap<String,Integer> votesMap = new HashMap<String,Integer>();
		
		
		int i;
		
		for (i=0; i<length; i++) {
			String vote = votes.get(i);
			
			if (!votesMap.containsKey(vote)) {
				votesMap.put(vote,1);
			} else {
				int currentNumOfVotes = votesMap.get(vote);
				votesMap.put(vote, currentNumOfVotes+1);
			}
			
		}
		
		for (String name : votesMap.keySet()) {
			println("Votes for " + name + ": " + votesMap.get(name));
		}
		
	}
}
