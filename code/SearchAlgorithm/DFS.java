package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.Stack;

import code.classes.Node;

public class DFS extends SearchAlgorithm {
    Stack<Node> stack = new Stack<>();

    @Override
    protected void addNode(Node state) {
            stack.push(state);
    }

    @Override
    protected Node removeNode() {
        return stack.pop();
    }

    @Override
    protected boolean isEmpty() {
        return stack.isEmpty();
    }
}
