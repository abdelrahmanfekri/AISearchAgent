package code.SearchAlgorithm;

import java.util.HashSet;
import java.util.Stack;

import code.classes.Node;
import code.classes.Problem;

// Depth Limit Search
class DLS extends SearchAlgorithm {
    Stack<Node> stack = new Stack<>();
    int maxDepth;

    public DLS(int depth) {
        maxDepth = depth;
    }

    @Override
    protected void addNode(Node state) {
        if (state.depth <= maxDepth) {
            stack.push(state);
        }
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

// Iterative deepening search
public class IDS extends SearchAlgorithm {
    int maxDepth = 1000000;

    @Override
    protected Node removeNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeState'");
    }

    @Override
    protected void addNode(Node state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addState'");
    }

    @Override
    protected boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addState'");
    }

    @Override
    public Node search(Problem problem) {
        for (int i = 0; i < maxDepth; i++) {
            DLS dls = new DLS(i);
            Node node = dls.search(problem);
            if (node != null)
                return node;
        }
        return null;
    }

}
