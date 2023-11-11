package code.classes;
import code.enums.Actions;

public class Node{
    
    // state
    int food, material, energy, prosperity, money_spent;
    
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
    }

    public Node() {
        this.parent = null;
    }

    public void setFood(int food) {
        if (food > 50 || food < 0) {
            throw new IllegalArgumentException("food must be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.food - food) * Problem.unitPriceFood);
        this.food = food;
    }

    public void setMaterial(int material) {
        if (material > 50 || material < 0) {
            throw new IllegalArgumentException(" material should be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.material - material) * Problem.unitPriceMaterials);
        this.material = material;
    }

    public void setEnergy(int e) {
        if (e > 50 || e < 0) {
            throw new IllegalArgumentException("energy should be between 0 and 50");
        }
        setMoney_spent(this.money_spent + Math.abs(this.energy - e) * Problem.unitPriceEnergy);
        this.energy = e;
    }

    public void setProsperity(int p) {
        if (p > 100 || p < 0) {
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
        return prosperity;
    }

    public int h2() {
        return prosperity + food + material + energy;
    }

    public int f1() {
        return h1() + money_spent;
    }

    public int f2() {
        return h2() + money_spent;
    }

    public String toString() {
        return "food: " + food + "material: " + material + "energy: " + energy + "prosperity: " + prosperity
                + "cost: " + money_spent + "action: " + action;
    }

}
