package org.example.model;

/**
 * Class that represents the Premium Base subscription plan.
 */
public class PlanPremiumBase extends Plan {

    /**
     * Default constructor for the Premium Base plan.
     */
    public PlanPremiumBase() {
        super();
    }

    /**
     * Copy constructor for the Premium Base plan.
     * @param plan Plan to be copied
     */
    public PlanPremiumBase(Plan plan) {
        super(plan);
    }

    /**
     * Gets the plan type.
     * @return Plan type ("Premium Base")
     */
    @Override
    public String getPlanType() {
        return "Premium Base";
    }

    /**
     * Returns a string representation of the Premium Base plan.
     * @return String with plan information
     */
    @Override
    public String toString() {
        return "Plan Type: Premium Base";
    }

    /**
     * Checks if two Premium Base plans are equal.
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
     * Creates a copy of the Premium Base plan.
     * @return New PlanPremiumBase object (clone)
     */
    @Override
    public Plan clone() {
        return new PlanPremiumBase(this);
    }

    /**
     * Calculates the updated points according to the Premium Base plan.
     * @param points Current points
     * @return Updated points
     */
    @Override
    public int getUpdatedPoints(int points) {
        return points + 10;
    }
}