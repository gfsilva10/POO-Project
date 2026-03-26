package org.example.model;

import java.io.Serializable;

/**
 * Abstract class that represents a subscription plan.
 */
public abstract class Plan implements Serializable {

    /**
     * Default constructor for the plan.
     */
    public Plan() {
        // Default constructor
    }

    /**
     * Copy constructor for the plan.
     * @param plan Plan to be copied
     */
    public Plan(Plan plan) {
        // Copy constructor
    }

    /**
     * Gets the plan type.
     * @return Plan type
     */
    public abstract String getPlanType();

    /**
     * Returns a string representation of the plan.
     * @return String with plan information
     */
    public String toString() {
        return "Plan Type: " + getPlanType();
    }

    /**
     * Checks if two plans are equal.
     * @param obj Object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Plan plan = (Plan) obj;
        return getPlanType().equals(plan.getPlanType());
    }

    /**
     * Creates a copy of the plan.
     * @return New Plan object (clone)
     */
    public abstract Plan clone();

    /**
     * Calculates the updated points according to the plan.
     * @param points Current points
     * @return Updated points
     */
    public abstract int getUpdatedPoints(int points);
}