package org.example.model;

public class PlanPremiumTop extends Plan {
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
    private String planType;
    private double price;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------

    public PlanPremiumTop() {
        super();
        this.planType = "";
        this.price = 0.0;
    }

    public PlanPremiumTop(String name, String planType, double price) {
        super(name);
        this.planType = planType;
        this.price = price;
    }

    public PlanPremiumTop(Plan plan) {
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
        return "PlanPremiumTop{" +
                "planType='" + planType + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanPremiumTop)) return false;
        PlanPremiumTop that = (PlanPremiumTop) o;
        return Double.compare(that.price, price) == 0 && planType.equals(that.planType);
    }

    @Override
    public Plan clone() {
        return new PlanPremiumTop(this);
    }
}
