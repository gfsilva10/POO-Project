package org.example.model;

import java.io.Serializable;

public class PlanFree extends Plan implements Serializable {
    private static final long serialVersionUID = 1L;

    public PlanFree() {
        super("Free");
    }
}