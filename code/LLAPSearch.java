package code;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;

import code.SearchAlgorithm.AS1;
import code.SearchAlgorithm.AS2;
import code.SearchAlgorithm.BFS;
import code.SearchAlgorithm.DFS;
import code.SearchAlgorithm.GR1;
import code.SearchAlgorithm.GR2;
import code.SearchAlgorithm.IDS;
import code.SearchAlgorithm.SearchAlgorithm;
import code.SearchAlgorithm.UFS;
import code.classes.Node;
import code.classes.Problem;
import code.enums.Strategy;

public class LLAPSearch extends SearchAlgorithm {

    public static String solve(String initialState, String strategy, boolean visualize) {
        /*
         * Given an initial state and a strategy it should return the sequence of
         * actions in order to reach the goal state
         * Returns plan;monetaryCost;nodesExpanded
         * plan: sequence of actions.
         * monetaryCost: money spent
         * nodesExpanded: number of nodes expanded
         */
        Problem p = new Problem(initialState, visualize);
        SearchAlgorithm sa;
        switch (Strategy.valueOf(strategy)) {
            case BF:
                sa = new BFS();
                break;
            case DF:
                sa = new DFS();
                break;
            case ID:
                sa = new IDS();
                break;
            case UC:
                sa = new UFS();
                break;
            case GR1:
                sa = new GR1();
                break;
            case AS1:
                sa = new AS1();
                break;
            case GR2:
                sa = new GR2();
                break;
            case AS2:
                sa = new AS2();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }
        Node solution = sa.search(p);
        if (solution == null) {
            return p.failureString();
        }
        if(Problem.showVisitedNodes){
            System.out.println("Goal Path: ");
            ArrayList<Node> list = p.getPath(solution);
            for (Node n : list) {
                System.out.println(n);
            }
        }

        return p.getPlan(solution) + ";" + p.getMoneyCoString(solution) + ";" + p.getNumOfExpandedNodes();
    }

    public static void main(String[] args) {
        /*
         * initialProsperity;
         * initialFood, initialMaterials, initialEnergy;
         * unitPriceFood, unitPriceMaterials, unitPriceEnergy;
         * amountRequestFood, delayRequestFood;
         * amountRequestMaterials, delayRequestMaterials;
         * amountRequestEnergy, delayRequestEnergy;
         * priceBUILD1, foodUseBUILD1,
         * materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
         * priceBUILD2, foodUseBUILD2,
         * materialsUseBUILD2, energyUseBUILD2, prosperityBUILD1;
         */

        String initialState0 = "17;" +
                "49,30,46;" +
                "7,57,6;" +
                "7,1;20,2;29,2;" +
                "350,10,9,8,28;" +
                "408,8,12,13,34;";
        String initialState1 = "50;" +
                "12,12,12;" +
                "50,60,70;" +
                "30,2;19,2;15,2;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";
        String initialState2 = "30;" +
                "30,25,19;" +
                "90,120,150;" +
                "9,2;13,1;11,1;" +
                "3195,11,12,10,34;" +
                "691,7,8,6,15;";
        String initialState3 = "0;" +
                "19,35,40;" +
                "27,84,200;" +
                "15,2;37,1;19,2;" +
                "569,11,20,3,50;"+
                "115,5,8,21,38;" ;

        String initialState4 = "21;" +
                "15,19,13;" +
                "50,50,50;" +
                "12,2;16,2;9,2;" +
                "3076,15,26,28,40;" +
                "5015,25,15,15,38;";
        String initialState5 = "72;" +
                "36,13,35;" +
                "75,96,62;" +
                "20,2;5,2;33,2;" +
                "30013,7,6,3,36;" +
                "40050,5,10,14,44;";
        String initialState6 = "29;" +
                "14,9,26;" +
                "650,400,710;" +
                "20,2;29,2;38,1;" +
                "8255,8,7,9,36;" +
                "30670,12,12,11,36;";
        String initialState7= "1;" +
                "6,10,7;" +
                "2,1,66;" +
                "34,2;22,1;14,2;" +
                "1500,5,9,9,26;" +
                "168,13,13,14,46;";
        String initialState8 = ";" +
                "46,42,46;" +
                "5,32,24;" +
                "13,2;24,1;20,1;" +
                "155,7,5,10,7;" +
                "5,5,5,4,4;";
        String initialState9 = "50;" +
                "20,16,11;" +
                "76,14,14;" +
                "7,1;7,1;7,1;" +
                "359,14,25,23,39;" +
                "524,18,17,17,38;";
        String initialState10= "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";
        String init = "50;"+
                "22,22,22;" +
                "50,60,70;" +
                "30,2;19,1;15,1;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";


        runAlgorithm("BF", initialState10);
        runAlgorithm("DF", initialState10);
        runAlgorithm("ID", initialState10);
        runAlgorithm("UC", initialState10);
        runAlgorithm("GR1", initialState10);
        runAlgorithm("AS1", initialState10);
        runAlgorithm("GR2", initialState10);
        runAlgorithm("AS2", initialState10);

    }

    public static void runAlgorithm(String algorithm, String init){
        long start = System.currentTimeMillis();
        long memoryUsage = getMemoryUsage();
        double cpuUsage = getCPUUsage();
        String solution = LLAPSearch.solve(init, algorithm, false);
        double endCPU = getCPUUsage();
        long end = System.currentTimeMillis();
        System.out.println("Time of " + algorithm + ": " + (end - start));
        System.out.println("Memory Usage: " + (getMemoryUsage() - memoryUsage) + " bytes");
        System.out.println("CPU Usage: " + (endCPU - cpuUsage));
        System.out.println("Plan: " + solution);
    }

    public static long getMemoryUsage(){
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
    public static double getCPUUsage(){
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getSystemLoadAverage() * 100;
    }

    @Override
    protected void addNode(Node state) {

    }

    @Override
    protected Node removeNode() {
        return null;
    }

    @Override
    protected boolean isEmpty() {
        return false;
    }
}
