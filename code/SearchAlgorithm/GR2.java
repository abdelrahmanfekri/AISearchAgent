package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

import code.classes.Node;

// Greedy search with second heuristic
public class GR2 extends SearchAlgorithm {
    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.h2() - o2.h2());

    @Override
    void addNode(Node state) {
            queue.add(state);
    }

    @Override
    Node removeNode() {
        return queue.remove();
    }

    @Override
    boolean isEmpty() {
        return queue.isEmpty();
    }

}
