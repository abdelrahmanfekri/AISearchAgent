package code;

import code.SearchAlgorithm.AS1;
import code.SearchAlgorithm.AS2;
import code.SearchAlgorithm.BFS;
import code.SearchAlgorithm.DFS;
import code.SearchAlgorithm.GR1;
import code.SearchAlgorithm.GR2;
import code.SearchAlgorithm.IDS;
import code.SearchAlgorithm.SearchAlgorithm;
import code.SearchAlgorithm.UFS;
import code.classes.Node;
import code.classes.Problem;
import code.enums.Strategy;

class Main {

    public static String solve(String initialState, Strategy strategy, boolean visualize) {
        /*
         * Given an initial state and a strategy it should return the sequence of
         * actions in order to reach the goal state
         * Returns plan;monetaryCost;nodesExpanded
         * plan: sequence of actions.
         * monetaryCost: money spent
         * nodesExpanded: number of nodes expanded
         */
        Problem p = new Problem(initialState);
        SearchAlgorithm sa;
        switch (strategy) {
            case BF:
                sa = new BFS();
                break;
            case DF:
                sa = new DFS();
                break;
            case ID:
                sa = new IDS();
                break;
            case UC:
                sa = new UFS();
                break;
            case GR1:
                sa = new GR1();
                break;
            case AS1:
                sa = new AS1();
                break;
            case GR2:
                sa = new GR2();
                break;
            case AS2:
                sa = new AS2();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }
        Node solution = sa.search(p);
        if (solution == null) {
            return p.failureString();
        }

        return p.getPlan(solution) + ";" + p.getMoneyCoString(solution) + ";" + p.getNumOfExpandedNodes();
    }

    public static void main(String[] args) {

    }
}
