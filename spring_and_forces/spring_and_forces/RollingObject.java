package spring_and_forces;

import processing.core.PApplet;

public class RollingObject extends SlidingObject {

	private double angularVel;
	private double angularMom;
	PApplet p5;
	double a = 0;

	public RollingObject(PApplet _p5, int id, Vector pos, Vector vel, double radius) {
		super(_p5, id, pos, vel, radius);
		p5 = _p5;
		// TODO Auto-generated constructor stub
		// define code for angular vel, and angular mom
	}

	public double getAngularVel() {
		return this.angularVel;
	}
	public double getAngle() {
		return this.a;
	}

	public void setAngularVel(double angularVel) {
		this.angularVel = angularVel;
	}

	public double getAngularMom() {
		return this.angularMom;
	}

	public void setAngularMom(double angularMom) {
		this.angularMom = angularMom;

	}

	public void display() {
		p5.push();
		p5.imageMode(p5.CENTER);
		p5.translate((float) this.getPos().getx(), (float) this.getPos().gety());
		p5.rotate((float) (((a) % 360) / (2 * p5.PI)));
	}

	public void move() {
		super.move();
		double sign = this.getVel().getx() / Math.abs(this.getVel().getx());
		this.setAngularVel(this.getVel().magnitude() * sign / this.radius);
		a = (a + this.getAngularVel());
		System.out.println("in rollingobj avel= " + this.getAngularVel() + this.getVel());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
