package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import code.classes.Node;

public class BFS extends SearchAlgorithm {
    Queue<Node> queue = new LinkedList<>();

    @Override
    protected void addNode(Node state) {
            queue.add(state);
    }

    @Override
    protected Node removeNode() {
        return queue.remove();
    }

    @Override
    protected boolean isEmpty() {
        return queue.isEmpty();
    }
}
