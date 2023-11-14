package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.Stack;

import code.classes.Node;

public class DFS extends SearchAlgorithm {
    Stack<Node> stack = new Stack<>();

    @Override
    void addNode(Node state) {
            stack.push(state);
    }

    @Override
    Node removeNode() {
        return stack.pop();
    }

    @Override
    boolean isEmpty() {
        return stack.isEmpty();
    }
}
