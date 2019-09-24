
package hw4.puzzle;

import java.util.*;

public class Solver {

    /**
     * Current state of the game. Ex: starting word is horse and our goal is to get to nurse.
     */
    private WorldState state;
    private Set<String> visitedNodes;
    private SearchNode result;

    public Solver(WorldState initial) {
        visitedNodes = new HashSet<String>();
        this.state = initial;
        bfs(initial);
    }
    
    private void bfs(WorldState initial) {
        MinPQ<SearchNode> q = new MinPQ<SearchNode>();
        SearchNode parent = new SearchNode(null, initial);
        q.insert(parent);

        while (!q.isEmpty()) {

            SearchNode nodeX = q.delMin();
            WorldState nodeXState = nodeX.getState();

            visitedNodes.add(nodeXState.toString());
            WorldState prevNodeState = nodeX.getPrev() != null ? nodeX.getPrev().getState() : null;

            if (nodeXState.isGoal()) {
                result = nodeX;
                break;
            }

            for (WorldState state: nodeXState.neighbors()) {

                if (prevNodeState != null) {
                    if (prevNodeState.equals(nodeXState)) {continue;}
                }

                if (!visitedNodes.contains(state.toString())) {
                    SearchNode newnode = new SearchNode(nodeX, state);
                    q.insert(newnode);
                }
            }
        }

    }


    /**
     *Returns the minimum number of moves to solve the puzzle
     *starting at the initial WorldState
     */
    public int moves() {
        return result.getNMovesFromStart();
    }

    /**
     *Returns a sequence of moves to solve the puzzle
     *starting at the initial WorldState
     */
    public Iterable<WorldState> solution() {
        Deque<WorldState> stack = new ArrayDeque<WorldState>();
        SearchNode root = result;
        while(root != null) {
            stack.addFirst(root.getState());
            root = root.getPrev();
        }
        return stack;
    }


    public static void main(String[] args) {
        System.out.println("Solver!!! Class");
        String start = "mug";
        String goal = "rugs";

        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);
    }
}
