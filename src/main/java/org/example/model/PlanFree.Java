package org.example.model;

public class PlanFree extends Plan {

    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
    private String planType;
    private double price;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public PlanFree() {
        super();
        this.planType = "";
        this.price = 0.0;
    }

    public PlanFree(String name) {
        super(name);
        this.planType = "Free";
        this.price = 0.0;
    }

    public PlanFree(Plan plan) {
        super(plan);
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "PlanFree{" +
                "planType='" + planType + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanFree)) return false;
        PlanFree planFree = (PlanFree) o;
        return Double.compare(planFree.price, price) == 0 && name.equals(planFree.name);
    }

    @Override
    public PlanFree clone() {
        return new PlanFree(this.name);
    }

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
}
