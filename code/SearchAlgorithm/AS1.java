package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

import code.classes.Node;

// A* algorithm search with first heuristic
public class AS1 extends SearchAlgorithm {
    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.f1() - o2.f1());

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
