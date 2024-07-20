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
