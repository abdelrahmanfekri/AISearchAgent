package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import code.classes.Node;

public class BFS extends SearchAlgorithm {
    Queue<Node> queue = new LinkedList<>();
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
