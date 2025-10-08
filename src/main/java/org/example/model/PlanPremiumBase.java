package org.example.model;

import java.io.Serializable;

public class PlanPremiumBase extends Plan implements Serializable {
    private static final long serialVersionUID = 1L;

    public PlanPremiumBase() {
        super("PremiumBase");
    }
}