package code.SearchAlgorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import code.classes.Node;

public class UFS extends SearchAlgorithm {
    PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getCost() - o2.getCost();
        }
    });
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
