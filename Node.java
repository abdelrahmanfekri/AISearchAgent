public class Node {
    int food, material, energy, prosperity, cost;
    Node parent;
    Actions action;

    public Node(int food, int material, int energy, int prosperity, int cost, Node parent) {
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.prosperity = prosperity;
        this.cost = cost;
        this.parent = parent;
    }

    public Node() {
        this.parent = null;
    }

    public void setFood(int food) {
        if (food > 50 || food < 0) {
            throw new IllegalArgumentException("food must be between 0 and 50");
        }
        setCost(this.cost + Math.abs(this.food - food) * Main.unitPriceFood);
        this.food = food;
    }

    public void setMaterial(int material) {
        if (material > 50 || material < 0) {
            throw new IllegalArgumentException(" material should be between 0 and 50");
        }
        setCost(this.cost + Math.abs(this.material - material) * Main.unitPriceMaterials);
        this.material = material;
    }

    public void setEnergy(int e) {
        if (e > 50 || e < 0) {
            throw new IllegalArgumentException("energy should be between 0 and 50");
        }
        setCost(this.cost + Math.abs(this.energy - e) * Main.unitPriceEnergy);
        this.energy = e;
    }

    public void setProsperity(int p) {
        if (p > 100 || p < 0) {
            throw new IllegalArgumentException("prosperity should be between 0 and 100");
        }
        this.prosperity = p;
    }

    public void setCost(int c) {
        if (c < 0 || c > 100000) {
            throw new IllegalArgumentException("cost should be positive and less than 100000");
        }
        this.cost = c;
    }

    public int h1() {
        return prosperity;
    }

    public int h2() {
        return prosperity + food + material + energy;
    }

    public String toString() {
        return "food: " + food + "material: " + material + "energy: " + energy + "prosperity: " + prosperity
                + "cost: " + cost + "action: " + action;
    }

}
