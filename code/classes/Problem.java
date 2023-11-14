package code.classes;

import java.util.ArrayList;

import code.enums.Actions;

public class Problem {
    public  int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    public static int amountRequestFood, delayRequestFood;
    public static int amountRequestMaterials, delayRequestMaterials;
    public static int amountRequestEnergy, delayRequestEnergy;

    public  int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;

    public  int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

    public String initial;

    public int numOfExpandedNodes = 0;

    public Problem(String initialState) {
        this.initial = initialState;
    }

    public boolean goalTest(Node state) {
        return state.prosperity >= 100 && state.money_spent <= 100000;
    }

    public Node getInitNode() {
        String[] split1 = initial.split(";");
        Node initState = new Node();

        initState.prosperity = Integer.parseInt(split1[0]);

        String[] split2 = split1[1].split(",");
        initState.food = Integer.parseInt(split2[0]);
        initState.material = Integer.parseInt(split2[1]);
        initState.energy = Integer.parseInt(split2[2]);

        String[] split3 = split1[2].split(",");
        unitPriceFood = Integer.parseInt(split3[0]);
        unitPriceMaterials = Integer.parseInt(split3[1]);
        unitPriceEnergy = Integer.parseInt(split3[2]);

        String[] split41 = split1[3].split(",");
        String[] split42 = split1[4].split(",");
        String[] split43 = split1[5].split(",");
        amountRequestFood = Integer.parseInt(split41[0]);
        delayRequestFood = Integer.parseInt(split41[1]);
        amountRequestMaterials = Integer.parseInt(split42[0]);
        delayRequestMaterials = Integer.parseInt(split42[1]);
        amountRequestEnergy = Integer.parseInt(split43[0]);
        delayRequestEnergy = Integer.parseInt(split43[1]);

        String[] split5 = split1[6].split(",");
        priceBUILD1 = Integer.parseInt(split5[0]);
        foodUseBUILD1 = Integer.parseInt(split5[1]);
        materialsUseBUILD1 = Integer.parseInt(split5[2]);
        energyUseBUILD1 = Integer.parseInt(split5[3]);
        prosperityBUILD1 = Integer.parseInt(split5[4]);

        String[] split6 = split1[7].split(",");
        priceBUILD2 = Integer.parseInt(split6[0]);
        foodUseBUILD2 = Integer.parseInt(split6[1]);
        materialsUseBUILD2 = Integer.parseInt(split6[2]);
        energyUseBUILD2 = Integer.parseInt(split6[3]);
        prosperityBUILD2 = Integer.parseInt(split6[4]);

        return initState;
    }

    public String failureString() {
        return "NOSOLUTION";
    }

    public ArrayList<Actions> getActions(Node state) {
        // given a state it should return possible action allowed for this state
        ArrayList<Actions> list = new ArrayList<>();
        if(state.food ==0 || state.material == 0 || state.energy == 0){
            return list;
        }

        // Request resources action
        if (!isDelay(state)) {
            list.add(Actions.RequestFood);
            list.add(Actions.RequestMaterials);
            list.add(Actions.RequestEnergy);
        } else {
            list.add(Actions.WAIT);
        }

        // Build1 action
        if (state.food >= foodUseBUILD1 && state.material >= materialsUseBUILD1 && state.energy >= energyUseBUILD1) {
            list.add(Actions.BUILD1);
        }

        // Build2 action
        if (state.food >= foodUseBUILD2 && state.material >= materialsUseBUILD2 && state.energy >= energyUseBUILD2) {
            list.add(Actions.BUILD2);
        }

        return list;
    }

    public boolean isDelay(Node state) {
        /*
         * Check if there is at least on delayed request
         */
        int lastFoodAction = state.lastFoodAction();
        int lastMaterialsAction = state.lastMaterialAction();
        int lastEnergyAction = state.lastEnergyAction();
        return (lastFoodAction < delayRequestFood || lastMaterialsAction < delayRequestMaterials
                || lastEnergyAction < delayRequestEnergy);
    }

    public Node result(Node state, Actions action) {
        /*
         * Given a state and an action it should return the new state after applying the
         * action
         */

        Node newState = new Node();
        newState.parent = state;
        newState.action = action;
        newState.prosperity = state.prosperity;
        newState.food = state.food;
        newState.material = state.material;
        newState.energy = state.energy;
        newState.money_spent = state.money_spent;
        newState.depth = state.depth + 1;
        newState.setLastEnergyAction(state.lastEnergyAction + 1);
        newState.setLastFoodAction(state.lastFoodAction + 1);
        newState.setLastMaterialAction(state.lastMaterialAction + 1);

        // add delayed food resources
        int lastFoodAction = newState.lastFoodAction();
        if (lastFoodAction == delayRequestFood) {
            newState.increaseFood(amountRequestFood);
        }

        // add delayed material resources
        int lastMaterialAction = newState.lastMaterialAction();
        if (lastMaterialAction == delayRequestMaterials) {
            newState.increaseMaterial(amountRequestMaterials);
        }

        // add delayed energy resources
        int lastEnergyAction = newState.lastEnergyAction();
        if (lastEnergyAction == delayRequestEnergy) {
            newState.increaseEnergy(amountRequestEnergy);
        }

        switch (action) {
            case BUILD1:
                newState.setFood(newState.food - foodUseBUILD1, this);
                newState.setMaterial(newState.material - materialsUseBUILD1, this);
                newState.setEnergy(newState.energy - energyUseBUILD1, this);
                newState.setProsperity(newState.prosperity + prosperityBUILD1);
                newState.setMoney_spent(newState.money_spent + priceBUILD1);
                break;
            case BUILD2:
                newState.setFood(newState.food - foodUseBUILD2, this);
                newState.setMaterial(newState.material - materialsUseBUILD2, this);
                newState.setEnergy(newState.energy - energyUseBUILD2, this);
                newState.setProsperity(newState.prosperity + prosperityBUILD2);
                newState.setMoney_spent(newState.money_spent + priceBUILD2);
                break;
            case RequestFood:
                newState.setFood(newState.food - 1, this);
                newState.setMaterial(newState.material - 1, this);
                newState.setEnergy(newState.energy - 1, this);
                newState.lastFoodAction = 0;
                break;
            case RequestMaterials:
                newState.setFood(newState.food - 1, this);
                newState.setMaterial(newState.material - 1, this);
                newState.setEnergy(newState.energy - 1, this);
                newState.lastMaterialAction = 0;
                break;
            case RequestEnergy:
                newState.setFood(newState.food - 1, this);
                newState.setMaterial(newState.material - 1, this);
                newState.setEnergy(newState.energy - 1, this);
                newState.lastEnergyAction = 0;
                break;
            case WAIT:
                newState.setFood(newState.food - 1, this);
                newState.setMaterial(newState.material - 1, this);
                newState.setEnergy(newState.energy - 1, this);
                break;
        }
        numOfExpandedNodes++;
        return newState;
    }

    public String getPlan(Node node) {
        /*
         * 
         * Given the resulting node should return actions from
         * the initial node till reaching the resulting node
         */
        ArrayList<Actions> list = new ArrayList<>();
        while (node.parent != null) {
            list.add(node.action);
            node = node.parent;
        }
        StringBuilder actions = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            actions.append(list.get(i)).append(",");
        }
        return actions.toString().substring(0, actions.length() - 1);
    }

    public String getMoneyCoString(Node node) {
        /*
         * Given the resulting node should return the money spent from
         * the initial node till reaching the resulting node
         */
        return String.valueOf(node.money_spent);
    }

    public String getNumOfExpandedNodes() {
        /*
         * Returns the number of expanded nodes
         */
        return String.valueOf(numOfExpandedNodes);
    }

    public static void main(String[] args){
        String initialState10= "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";
        Problem p = new Problem(initialState10);
        Node node = p.getInitNode();
        System.out.println(node);
        Actions[] actions = {
                 Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT

                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy,Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.BUILD2
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestMaterials, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestEnergy, Actions.WAIT, Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.RequestFood,Actions.WAIT
                , Actions.BUILD2};
        for(Actions a: actions){
            node = p.result(node, a);
            System.out.println(node);
        }
    }

}
