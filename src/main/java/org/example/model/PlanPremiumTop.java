package org.example.model;

/**
 * Class that represents the Premium Top subscription plan.
 */
public class PlanPremiumTop extends Plan {

    /**
     * Default constructor for the Premium Top plan.
     */
    public PlanPremiumTop() {
        super();
    }

    /**
     * Copy constructor for the Premium Top plan.
     * @param plan Plan to be copied
     */
    public PlanPremiumTop(Plan plan) {
        super(plan);
    }

    /**
     * Gets the plan type.
     * @return Plan type ("Premium Top")
     */
    @Override
    public String getPlanType() {
        return "Premium Top";
    }

    /**
     * Returns a string representation of the Premium Top plan.
     * @return String with plan information
     */
    @Override
    public String toString() {
        return "Plan Type: Premium Top";
    }

    /**
     * Checks if two Premium Top plans are equal.
     * @param obj Object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }

    /**
     * Creates a copy of the Premium Top plan.
     * @return New PlanPremiumTop object (clone)
     */
    @Override
    public Plan clone() {
        return new PlanPremiumTop(this);
    }

    /**
     * Calculates the updated points according to the Premium Top plan.
     * @param points Current points
     * @return Updated points
     */
    @Override
    public int getUpdatedPoints(int points) {
        return points + (int) (points * 0.025); // 2.5% of current points
    }
}