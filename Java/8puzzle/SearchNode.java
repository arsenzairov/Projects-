package hw4.puzzle;


import java.util.Iterator;

/** SearchNode consist of
    1. World State
    2. Number of moves made to reach this world state from the initial state
    3. Reference to the previous search node


    Each SearchNode represents one “move sequence” as defined in the conceptual description of Best-First Search
 */


public class SearchNode implements Comparable<SearchNode> {

    private SearchNode prev;
    private WorldState state;
    private int nMovesFromStart;
    private int estimateDistanceToGoal;

    public SearchNode(SearchNode prev, WorldState state) {
        this.prev = prev;
        this.state = state;

        if (this.prev == null) {
            this.nMovesFromStart = 0;
        } else {
            this.nMovesFromStart = this.prev.nMovesFromStart + 1;
        }

        setEstimateDistanceToGoal(this.state.estimatedDistanceToGoal());
    }

    public WorldState getState() {
        return state;
    }

    private void setEstimateDistanceToGoal(int n) {
        estimateDistanceToGoal = n;
    }

    public int getEstimateDistanceToGoal() {
        return estimateDistanceToGoal;
    }

    public int getNMovesFromStart() {
        return nMovesFromStart;
    }

    public SearchNode getPrev() {
        return prev;
    }


    @Override
    public String toString() {
        return "Node with state: " + this.getState() + " estimated distance to goal is: " + this.getEstimateDistanceToGoal() + " distance from start: " + this.getNMovesFromStart() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SearchNode)) {
            return false;
        }

        SearchNode node = (SearchNode) obj;
        if (this.hashCode() == obj.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    /* Helps to compare two nodes based on the provided optimization
    *
    * M1 - indicates how many moves is the SearchNode from the initial state
    * M2 - indicates how many moves is the second SearchNode from the initial state
    * E1 - indicates the estimated distance of the SearchNode to the goal
    * E2 - indicates the estimated distance of the second SearchNode to the goal
    * (M1 + E1) < (E2 + M2).
    *
    * The former has a lowest priority therefore would be selected first from the Queue.
    * */
     @Override
        public int compareTo(SearchNode o) {
            return  (this.getEstimateDistanceToGoal() + this.getNMovesFromStart()) - (o.nMovesFromStart + o.getEstimateDistanceToGoal());
    }
}
