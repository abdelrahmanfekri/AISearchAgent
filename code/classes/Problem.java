package code.classes;

import java.util.ArrayList;

import code.enums.Actions;

public class Problem {
    public static int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    public static int amountRequestFood, delayRequestFood;
    public static int amountRequestMaterials, delayRequestMaterials;
    public static int amountRequestEnergy, delayRequestEnergy;

    public static int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;

    public static int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

    public String initial;

    public int numOfExpandedNodes = 0;

    public Problem(String initialState) {
        this.initial = initialState;
    }

    public boolean goalTest(Node state) {
        return state.prosperity == 100;
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
        unitPriceEnergy = Integer.parseInt(split3[0]);
        unitPriceMaterials = Integer.parseInt(split3[1]);
        unitPriceFood = Integer.parseInt(split3[2]);

        String[] split4 = split1[3].split(";");
        String[] split41 = split4[0].split(",");
        String[] split42 = split4[1].split(",");
        String[] split43 = split4[2].split(",");
        amountRequestFood = Integer.parseInt(split41[0]);
        delayRequestFood = Integer.parseInt(split41[1]);
        amountRequestMaterials = Integer.parseInt(split42[0]);
        delayRequestMaterials = Integer.parseInt(split42[1]);
        amountRequestEnergy = Integer.parseInt(split43[0]);
        delayRequestEnergy = Integer.parseInt(split43[1]);

        String[] split5 = split1[4].split(",");
        priceBUILD1 = Integer.parseInt(split5[0]);
        foodUseBUILD1 = Integer.parseInt(split5[1]);
        materialsUseBUILD1 = Integer.parseInt(split5[2]);
        energyUseBUILD1 = Integer.parseInt(split5[3]);
        prosperityBUILD1 = Integer.parseInt(split5[4]);

        String[] split6 = split1[5].split(",");
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

        // Build1 action
        if (state.food >= foodUseBUILD1 && state.material >= materialsUseBUILD1 && state.energy >= energyUseBUILD1) {
            list.add(Actions.BUILD1);
        }

        // Build2 action
        if (state.food >= foodUseBUILD2 && state.material >= materialsUseBUILD2 && state.energy >= energyUseBUILD2) {
            list.add(Actions.BUILD2);
        }

        // Request resources action
        if (!isDelay(state)) {
            list.add(Actions.RequestEnergy);
            list.add(Actions.RequestFood);
            list.add(Actions.RequestMaterials);
        } else {
            list.add(Actions.WAIT);
        }
        return list;
    }

    public boolean isDelay(Node state) {
        /*
         * Check if there is at least on delayed request
         */
        int lastFoodAction = lastFoodAction(state);
        int lastMaterialsAction = lastMaterialAction(state);
        int lastEnergyAction = lastEnergyAction(state);
        return !(lastFoodAction > delayRequestFood && lastMaterialsAction > delayRequestMaterials
                && lastEnergyAction > delayRequestEnergy);
    }

    public int lastFoodAction(Node state) {
        /*
         * Returns the last food request action
         */
        int lastFoodAction = 0;
        while (state.parent != null) {
            lastFoodAction++;
            if (state.action == Actions.RequestFood) {
                break;
            }
            state = state.parent;
        }
        return lastFoodAction;
    }

    public int lastMaterialAction(Node state) {
        /*
         * Return last material request action
         */
        int lastMaterialAction = 0;
        while (state.parent != null) {
            lastMaterialAction++;
            if (state.action == Actions.RequestMaterials) {
                break;
            }
            state = state.parent;
        }
        return lastMaterialAction;
    }

    public int lastEnergyAction(Node state) {
        /*
         * Returns the last performed request energy action
         */
        int lastEnergyAction = 0;
        while (state.parent != null) {
            lastEnergyAction++;
            if (state.action == Actions.RequestEnergy) {
                break;
            }
            state = state.parent;
        }
        return lastEnergyAction;
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

        // add delayed food resources
        int lastFoodAction = lastFoodAction(newState);
        if (lastFoodAction == delayRequestFood) {
            newState.setFood(newState.food + amountRequestFood);
        }

        // add delayed material resources
        int lastMaterialAction = lastMaterialAction(newState);
        if (lastMaterialAction == delayRequestMaterials) {
            newState.setMaterial(newState.material + amountRequestMaterials);
        }

        // add delayed energy resources
        int lastEnergyAction = lastEnergyAction(newState);
        if (lastEnergyAction == delayRequestEnergy) {
            newState.setEnergy(lastEnergyAction + amountRequestEnergy);
        }

        switch (action) {
            case BUILD1:
                newState.setFood(newState.food - foodUseBUILD1);
                newState.setMaterial(newState.material - materialsUseBUILD1);
                newState.setEnergy(newState.energy - energyUseBUILD1);
                newState.setProsperity(newState.prosperity + prosperityBUILD1);
                newState.setMoney_spent(newState.money_spent + priceBUILD1);
                break;
            case BUILD2:
                newState.setFood(newState.food - foodUseBUILD2);
                newState.setMaterial(newState.material - materialsUseBUILD2);
                newState.setEnergy(newState.energy - energyUseBUILD2);
                newState.setProsperity(newState.prosperity + prosperityBUILD2);
                newState.setMoney_spent(newState.money_spent + priceBUILD2);
                break;
            case RequestFood:
                newState.setFood(newState.food - 1);
                newState.setMaterial(newState.material - 1);
                newState.setEnergy(newState.energy - 1);
                break;
            case RequestMaterials:
                newState.setFood(newState.food - 1);
                newState.setMaterial(newState.material - 1);
                newState.setEnergy(newState.energy - 1);
                break;
            case RequestEnergy:
                newState.setFood(newState.food - 1);
                newState.setMaterial(newState.material - 1);
                newState.setEnergy(newState.energy - 1);
                break;
            case WAIT:
                newState.setFood(newState.food - 1);
                newState.setMaterial(newState.material - 1);
                newState.setEnergy(newState.energy - 1);
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

}
