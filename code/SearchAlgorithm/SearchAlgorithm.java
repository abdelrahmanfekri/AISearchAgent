package code.SearchAlgorithm;

import code.enums.Actions;
import code.classes.Node;
import code.classes.Problem;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SearchAlgorithm {
    abstract void addNode(Node state);

    abstract Node removeNode();

    abstract boolean isEmpty();

    HashSet<String> set = new HashSet<>();
    HashSet<String> expanded = new HashSet<>();

    public Node search(Problem problem) {
        if (problem == null)
            throw new IllegalArgumentException("Problem should not be null");
        Node initialNode = problem.getInitNode();
        addNode(initialNode);
        set.add(initialNode.toString());
        while (!isEmpty()) {
            Node node = removeNode();
            if (problem.goalTest(node)) {
                return node;
            }
            ArrayList<Actions> possibleActions = problem.getActions(node);
            for (Actions a : possibleActions) {
                try {
                    Node newNode = problem.result(node, a);
                    if(problem.goalTest(newNode)){
                        return newNode;
                    }
                    if(!set.contains(newNode.toString())) {
                        set.add(newNode.toString());
                        addNode(newNode);
                    }
                }catch (Exception e){
                    // System.out.println(e);
                }
            }
        }
        return null;
    }
}
