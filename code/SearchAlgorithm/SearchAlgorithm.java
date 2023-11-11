package code.SearchAlgorithm;

import code.enums.Actions;
import code.classes.Node;
import code.classes.Problem;

public abstract class SearchAlgorithm {
    abstract void addNode(Node state);

    abstract Node removeNode();

    abstract boolean isEmpty();

    public Node search(Problem problem) {
        if (problem == null)
            throw new IllegalArgumentException("Problem should not be null");
        Node initialNode = problem.getInitNode();
        addNode(initialNode);
        while (!isEmpty()) {
            Node node = removeNode();
            if (problem.goalTest(node)) {
                return node;
            }
            for (Actions a : problem.getActions(node)) {
                Node newNode = problem.result(node, a);
                addNode(newNode);
            }
        }
        return null;
    }
}
