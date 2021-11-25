package spring_and_forces;

import processing.core.PApplet;
import processing.core.PImage;

public class Cylinder extends RollingObject {

	// instance variables

	PApplet p5;
	PImage cylinder;

	// constructor
	public Cylinder(PApplet _p5, int id, Vector pos, Vector vel, double radius) {
		super(_p5, id, pos, vel, radius);

		// double[] ve = { StdRandom.uniform(-15, 15), StdRandom.uniform(-15, 15) };
		// Vector v = new Vector(ve);
		// this.setVel(v);
		p5 = _p5;
		cylinder = p5.loadImage("cylinder.png");
	}

	// bounce of vertical wall by reflecting x-velocity

	// move the ball one step
	public void move() {
		super.move();
	}

	// // draw the ball
	// public void setup() {
	//
	// }

	// identical use to draw in Prcessing IDE
	public void display() {

		super.display();

		p5.image(cylinder, 0, 0, (float) radius * 2, (float) radius * 2);

		p5.pop();
	}

}
