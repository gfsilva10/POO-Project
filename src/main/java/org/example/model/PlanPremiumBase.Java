package org.example.model;

public class PlanPremiumBase extends Plan {

    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
    private String planType;
    private double price;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public PlanPremiumBase() {
        super();
        this.planType = "";
        this.price = 0.0;
    }

    public PlanPremiumBase(String name, String planType, double price) {
        super(name);
        this.planType = planType;
        this.price = price;
    }

    public PlanPremiumBase(Plan plan) {
        super(plan);
        this.planType = "";
        this.price = 0.0;
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
        return "PlanPremiumBase{" +
                "planType='" + planType + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanPremiumBase)) return false;
        PlanPremiumBase that = (PlanPremiumBase) o;
        return Double.compare(that.price, price) == 0 && name.equals(that.name) && planType.equals(that.planType);
    }

    @Override
    public PlanPremiumBase clone() {
        return new PlanPremiumBase(this.name, this.planType, this.price);
    }

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
}
