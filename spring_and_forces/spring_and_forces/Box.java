package spring_and_forces;

import processing.core.PApplet;
import processing.core.PImage;

public class Box extends SlidingObject {
	PApplet p5;
	PImage box;
	private double width, length;
	private double angle;

	public Box(PApplet _p5, int id, Vector pos, Vector vel, double length, double width, double radius, double angle) {
		super(_p5, id, pos, vel, radius);
		// TODO Auto-generated constructor stub

		p5 = _p5;
		this.length = length;
		this.width = width;
		box = p5.loadImage("box.png");
		this.angle = angle;
	}

	public double getWidth() {
		return this.width;
	}

	public double getLength() {
		return this.length;
	}

	public void move() {
		super.move();
	}

	public void display() {

		p5.push();
		p5.imageMode(p5.CENTER);
		p5.translate((float) this.getPos().getx(), (float) this.getPos().gety());
		double angleinRad = ((((angle) % 360) / 360) * (2 * p5.PI));
		p5.rotate((float) (-angleinRad));
		// p5.image(ball, 0, 0);

		p5.image(box, 0, 0, (float) this.width, (float) this.length);
		p5.pop();

	}

}
