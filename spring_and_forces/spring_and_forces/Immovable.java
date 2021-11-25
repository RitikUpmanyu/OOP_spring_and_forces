package spring_and_forces;

import processing.core.PApplet;
import processing.core.PConstants;

public class Immovable extends Obj {
    private double frictionCoeff;

    public Immovable(PApplet _p5, int id, Vector pos, Vector vel, double radius, double frictionCoeff) {
        super(_p5, id, pos, radius, vel);
        this.frictionCoeff = frictionCoeff;

        // TODO Auto-generated constructor stub
    }

    public double getFrictionCoeff() {
        return this.frictionCoeff;
    }

    public void setFrictionCoeff(double frictionCoeff) {
        this.frictionCoeff = frictionCoeff;
    }

}
