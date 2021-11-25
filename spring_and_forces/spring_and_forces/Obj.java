package spring_and_forces;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Obj {

	private Vector pos;
	private Vector vel;
	private Vector acc;
	// private double life;
	private double mass = 1;
	public double radius;
	private boolean locked = false;
	private PApplet p5;
	public int id = 0;
	// private ArrayList<Object> onTop;

	// PApplet p5;
	// constructor
	public Obj(PApplet _p5, int id, Vector pos, double radius, Vector vel) {
		// double posData[] = { 250, 250 };

		this.pos = pos;
		this.radius = radius;

		this.vel = vel;
		System.out.println("vel=" + vel);
		double[] accData = { 0, 0 };
		acc = new Vector(accData);
		p5 = _p5;
		this.id = id;
	}

	// move the particle one step
	public void move() {
		if (!locked) {

			// if ((this.pos.getx() + this.vel.getx() + radius > 950) || (this.pos.getx() +
			// this.vel.getx() + radius < 50))
			// bounceOffVerticalWall();
			// if ((this.pos.gety() + this.vel.gety() + radius > 450) || (this.pos.gety() +
			// this.vel.gety() + radius < 0))
			// bounceOffHorizontalWall();

			this.vel = this.vel.plus(this.acc);
			this.pos = this.pos.plus(this.vel);
			this.acc = this.acc.scale(0);

		} else {
			this.vel = this.vel.scale(0);
		}

	}

	private void bounceOffVerticalWall() {
		double[] velData = { -1 * this.getVel().getx() * 0.92, this.getVel().gety() };
		Vector vx = new Vector(velData);
		this.setVel(vx);
		System.out.println("workingv" + this.getAcc() + this.getPos());

		if ((this.getPos().getx() > 500 && this.getAcc().getx() > 0)
				|| (this.getPos().getx() < 500 && this.getAcc().getx() < 0)) {
			double[] accData = { -1 * this.getAcc().getx(), 0 };
			Vector ax = new Vector(accData);
			this.applyForce(ax);
		}
	}

	// bounce of horizontal wall by reflecting y-velocity
	private void bounceOffHorizontalWall() {
		double[] velData = { this.getVel().getx(), -1 * this.getVel().gety() * 0.92 };
		Vector vy = new Vector(velData);
		this.setVel(vy);
		if ((this.getPos().gety() > 250 && this.getAcc().gety() > 0)
				|| (this.getPos().gety() < 250 && this.getAcc().gety() < 0)) {
			double[] accData = { 0, -1 * this.getAcc().gety() };
			Vector ay = new Vector(accData);
			this.applyForce(ay);
		}
	}

	public void handleSlope(Slope s) {

		if (this.pos.gety() + radius >= s.getycord(this.pos.getx())) {

			s.applyfric(this, s.applynormal(this, false));
			System.out.println("handleslope" + this.getAcc() + this.getPos());
		}
	}

	public void damp(double x) {

		this.applyForce(this.getVel().scale(x).scale(-1));

	}

	public Vector getPos() {
		return pos;
	}

	public Vector getVel() {
		return vel;
	}

	public void setLocked(boolean x) {
		this.locked = x;
	}

	public boolean getLocked() {
		return locked;
	}

	public Vector getAcc() {
		return acc;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public void setVel(Vector vel) {
		this.vel = vel;
	}

	public void setAcc(Vector acc) {
		this.acc = acc;
	}

	// move the particle one step
	public void applyForce(Vector force) {
		double invMass = 1 / this.mass;
		this.acc = this.acc.plus(force.scale(invMass));
		System.out.println(this.getAcc());
	}

	public void display() {
		// p5.background(500);
		p5.fill(204, 102, 0);
		p5.circle((float) pos.getx(), (float) pos.gety(), (float) radius);
		// this.applyForce(force);
	}

}
