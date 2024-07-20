package code.classes;

import code.enums.Actions;

public class Node {

    // state
    int food, material, energy, prosperity, money_spent;

    int lastFoodAction = 3, lastMaterialAction = 3, lastEnergyAction = 3;
    Node parent;
    Actions action;
    public int depth;

    public Node(int food, int material, int energy, int prosperity, int cost, Node parent, int depth) {
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.prosperity = prosperity;
        this.money_spent = cost;
        this.parent = parent;
        this.depth = depth;
        lastFoodAction = Problem.delayRequestFood;
        lastMaterialAction = Problem.delayRequestMaterials;
        lastEnergyAction = Problem.delayRequestEnergy;
    }

    public Node() {
        this.parent = null;
    }

    public void increaseFood(int amount) {
        food += amount;
        food = Math.min(food, 50);
    }

    public void increaseMaterial(int amount) {
        material += amount;
        material = Math.min(material, 50);
    }

    public void increaseEnergy(int amount) {
        energy += amount;
        energy = Math.min(energy, 50);
    }

    public void setLastFoodAction(int lastFoodAction) {
        this.lastFoodAction = Math.min(lastFoodAction, 3);
    }

    public void setLastMaterialAction(int lastMaterialAction) {
        this.lastMaterialAction = Math.min(lastMaterialAction, 3);
    }

    public void setLastEnergyAction(int lastEnergyAction) {
        this.lastEnergyAction = Math.min(lastEnergyAction, 3);
    }

    public void setFood(int food, Problem p) {
        if (food > 50 || food < 0) {
            throw new IllegalArgumentException("food must be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.food - food) * p.unitPriceFood);
        this.food = food;
    }

    public void setMaterial(int material, Problem p) {
        if (material > 50 || material < 0) {
            throw new IllegalArgumentException(" material should be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.material - material) * p.unitPriceMaterials);
        this.material = material;
    }

    public void setEnergy(int e, Problem p) {
        if (e < 0 || e > 50) {
            throw new IllegalArgumentException("energy should be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.energy - e) * p.unitPriceEnergy);
        this.energy = e;
    }

    public void setProsperity(int p) {
        if (p < 0) {
            throw new IllegalArgumentException("prosperity should be between 0 and 100");
        }
        this.prosperity = p;
    }

    public void setMoney_spent(int c) {
        if (c < 0 || c > 100000) {
            throw new IllegalArgumentException("cost should be positive and less than 100000");
        }
        this.money_spent = c;
    }

    public int getCost() {
        return money_spent;
    }

    public int h1() {
        return (100 - prosperity);
    }

    public int h2() {
        return (100 - prosperity) * (Problem.priceBUILD2 / Problem.prosperityBUILD2 / 2
                + Problem.priceBUILD1 / Problem.prosperityBUILD1 / 2);
    }

    public int f1() {
        return h1() + money_spent;
    }

    public int f2() {
        return h2() + money_spent;
    }

    public int lastFoodAction() {
        return lastFoodAction;
    }

    public int lastMaterialAction() {
        return lastMaterialAction;
    }

    public int lastEnergyAction() {
        return lastEnergyAction;
    }

    public String toHash() {
        return "#" + food + "#" + material + "#" + energy
                + "#" + lastFoodAction() + "#" + lastMaterialAction() + "#" + lastEnergyAction();
    }

    public String toString() {
        return "(" + food + " , " + material + " , " + energy + " , " + money_spent + " , " + action + " , "
                + lastFoodAction() + " , " + lastMaterialAction() + " , " + lastEnergyAction() + ")";
    }

}
