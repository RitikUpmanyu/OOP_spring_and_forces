package spring_and_forces;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PConstants;

public class Slope extends Immovable {

	private double angle;
	PApplet p5;
	PImage slope;
	private static double radius = 0;
	private static double[] data = { 500, 700 };
	private static Vector pos = new Vector(data);
	private double angleinRad;
	private static double[] veldata = { 0, 0 };
	private static Vector vel = new Vector(veldata);

	public Slope(PApplet _p5, int id, double frictionCoeff, double angle) {
		super(_p5, id, pos, vel, radius, frictionCoeff);

		this.angle = angle;
		p5 = _p5;
		if (this.getFrictionCoeff() > 0.8)
			this.setFrictionCoeff(0.8);
		slope = p5.loadImage("ground.png");
		angle = (angle) % 360;
		if (angle > 90 && angle <= 180) {
			angle = 90 - (angle - 90);
		} else if (angle > 180 && angle <= 270) {
			angle -= 180;
		} else if (angle > 270 && angle <= 360) {
			angle = 270 - (angle - 270);
		}

		// TODO Auto-generated constructor stub
	}

	public double getAngle() {
		return this.angle;
	}

	public double getycord(double x) {
		double y = pos.gety() - 250 / Math.cos(angleinRad) + Math.tan(angleinRad) * (-1) * (x - pos.getx());
		return y;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Vector applynormal(Obj o, boolean apply) {

		// double[] velData = { (o.getVel().magnitude() / Math.sqrt(2)) *
		// Math.cos(angleinRad),
		// (o.getVel().magnitude() / Math.sqrt(2)) * Math.sin(angleinRad) };
		// Vector vx = new Vector(velData);
		// // o.setVel(vx);

		double[] posData = { o.getPos().getx(), this.getycord(o.getPos().getx()) - o.radius };
		Vector opos = new Vector(posData);

		double normForce = (o.getAcc().getx() * Math.sin(angleinRad) + o.getAcc().gety() * Math.cos(angleinRad)) * (-1);
		double[] normData = { normForce * Math.sin(angleinRad), normForce * Math.cos(angleinRad) };
		Vector force = new Vector(normData);
		if (apply == false) {
			o.setPos(opos);
			System.out.println("normal " + force);

			o.applyForce(force);
		}
		if (this.getAngle() == 0) {
			double[] data = { o.getVel().getx(), 0 };
			Vector vel = new Vector(data);
			o.setVel(vel);
		}

		return force;
	}

	public void applyfric(Obj o, Vector normal) {
		double fricForce = this.getFrictionCoeff() * normal.magnitude();
		if (o.getAcc().magnitude() < fricForce) {
			fricForce = o.getAcc().magnitude();
		}

		double fricx = o.getVel().unit().getx() * fricForce;
		double fricy = o.getVel().unit().gety() * fricForce;

		double[] fricData = { -1 * fricx, -1 * fricy };
		Vector force = new Vector(fricData);
		o.applyForce(force);
		System.out.println(
				"fric " + force + this.applynormal(o, true) + " " + this.getFrictionCoeff() + " " + o.getClass());
	}

	@Override
	public void display() {
		// p5.line(0,100, 500,500);
		// p5.translate((float)rx,(float)ry);
		// p5.circle((float)rx,(float)ry,(float)radius);
		p5.push();
		p5.imageMode(p5.CENTER);
		p5.translate((float) pos.getx(), (float) pos.gety());

		angleinRad = (((angle) / 360) * (2 * p5.PI));
		p5.rotate((float) (-angleinRad));
		// p5.image(ball, 0, 0);
		p5.image(slope, 0, 0, 2500, 500);

		p5.pop();
	}

}
