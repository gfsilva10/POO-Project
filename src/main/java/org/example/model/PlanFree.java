package org.example.model;

/**
 * Class that represents the free subscription plan.
 */
public class PlanFree extends Plan {

    /**
     * Default constructor for the free plan.
     */
    public PlanFree() {
        super();
    }

    /**
     * Copy constructor for the free plan.
     * @param plan Plan to be copied
     */
    public PlanFree(Plan plan) {
        super(plan);
    }

    /**
     * Gets the plan type.
     * @return Plan type ("Free")
     */
    @Override
    public String getPlanType() {
        return "Free";
    }

    /**
     * Returns a string representation of the free plan.
     * @return String with plan information
     */
    @Override
    public String toString() {
        return "Plan Type: Free";
    }

    /**
     * Checks if two free plans are equal.
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
     * Creates a copy of the free plan.
     * @return New PlanFree object (clone)
     */
    @Override
    public Plan clone() {
        return new PlanFree(this);
    }

    /**
     * Calculates the updated points according to the free plan.
     * @param points Current points
     * @return Updated points
     */
    @Override
    public int getUpdatedPoints(int points) {
        return points + 5;
    }
}