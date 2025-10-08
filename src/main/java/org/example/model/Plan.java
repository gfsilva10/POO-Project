package org.example.model;

<<<<<<< HEAD
public abstract class Plan {
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
=======
import java.io.Serializable;

public abstract class Plan implements Serializable {
    private static final long serialVersionUID = 1L;

>>>>>>> 3c282de (data and main)
    protected String name;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public Plan() {
        this.name = "";
    }
   
    public Plan(String name) {
        this.name = name;
    }

    public Plan(Plan plan) {
        this.name = plan.name;
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Plan{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan)) return false;
        Plan plan = (Plan) o;
        return name.equals(plan.name);
    }
<<<<<<< HEAD

    public abstract Plan clone();

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
=======
>>>>>>> 3c282de (data and main)
}
