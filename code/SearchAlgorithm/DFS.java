package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.Stack;

import code.classes.Node;

public class DFS extends SearchAlgorithm {
    Stack<Node> stack = new Stack<>();
    HashSet<String> visited = new HashSet<>();

    @Override
    void addNode(Node state) {
        if (!visited.contains(state.toString())) {
            visited.add(state.toString());
            stack.push(state);
        } else {
            System.out.println("State already visited");
        }
    }

    @Override
    Node removeNode() {
        visited.remove(stack.peek().toString());
        return stack.pop();
    }

    @Override
    boolean isEmpty() {
        return stack.isEmpty();
    }
}
