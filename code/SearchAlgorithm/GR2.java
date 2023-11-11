package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

import code.classes.Node;

// Greedy search with second heuristic
public class GR2 extends SearchAlgorithm {
    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.h2() - o2.h2());
    HashSet<String> visited = new HashSet<>();

    @Override
    void addNode(Node state) {
        if (!visited.contains(state.toString())) {
            visited.add(state.toString());
            queue.add(state);
        } else {
            System.out.println("State already visited");
        }
    }

    @Override
    Node removeNode() {
        visited.remove(queue.peek().toString());
        return queue.remove();
    }

    @Override
    boolean isEmpty() {
        return queue.isEmpty();
    }

}
