package spring_and_forces;

import processing.core.PApplet;
import processing.core.PImage;

public class Spring extends Obj {

	// instance variables

	private double k;
	private Vector force;
	private Obj anchor;
	private Obj bob;
	private double restLength;
	private Vector anchorMinusBob;
	private Vector gravity;
	PImage spring;
	PApplet p5;

	// identical use to setup in Processing IDE except for size()
	// public void setup() {

	// stroke(0);
	// strokeWeight(10);
	// frameRate(30);
	// // translate(250, 250);
	// double rx = 400;
	// double ry = 400;
	// double anchorx = 250;
	// double anchory = 100;
	// restLength = 100;
	// double vx = 0.1 * 500 + 250;
	// double vy = 0.0 * 500 + 250;
	// double[] anchorData = { anchorx, anchory };
	// double[] bobData = { rx, ry };
	// Vector anchorVec = new Vector(anchorData);
	// Vector bobVec = new Vector(bobData);
	// bob = new Obj(this, bobVec);
	// anchor = new Obj(this, anchorVec);
	// double[] bob_velData = { vx, vy };
	// Vector bob_vel = new Vector(bob_velData);

	// double radius = 0.005;

	// // anchor.setLocked(true);

	// double[] gravityData = { 0, -1 };
	// gravity = new Vector(gravityData);

	// k = 0.02;
	// spring = loadImage("spring.png");
	// // Spring s1 = new Spring(anchor, bob, bob_vel, gravity, restLength, radius,
	// // springCoeff);
	// }

	// constructor
	public Spring(PApplet _p5, int id, Vector pos, double radius, Obj bobIn, Obj anchorIn, Vector Vel, Vector Gravity,
			double restLength, double k) {
		super(_p5, id, pos, radius, Vel);
		anchor = anchorIn;
		bob = bobIn;
		// System.out.println("inside String cons bob " + bob.getPos().getx() + " " +
		// bob.getPos().gety());
		// System.out.println("inside String cons anc " + anchor.getPos().getx() + " " +
		// anchor.getPos().gety());

		this.restLength = restLength;
		double[] forceData = { 0, 0 };
		force = new Vector(forceData);

		double[] anchorBob = { 0, 0 };
		anchorMinusBob = new Vector(anchorBob);
		p5 = _p5;
		gravity = Gravity;
		spring = p5.loadImage("spring.png");
		this.k = k;

	}

	// // bounce of vertical wall by reflecting x-velocity
	// private void bounceOffVerticalWall() {
	// vx = -vx;
	// }
	//
	// // bounce of horizontal wall by reflecting y-velocity
	// private void bounceOffHorizontalWall() {
	// vy = -vy;
	// }

	// move the ball one step
	public void move() {
		// if (Math.abs(rx + vx) + radius > 1.0) bounceOffVerticalWall();
		// if (Math.abs(ry + vy) + radius > 1.0) bounceOffHorizontalWall();

		anchorMinusBob = anchor.getPos().minus(bob.getPos());
		anchorMinusBob = anchorMinusBob.unit().scale(restLength).minus(anchor.getPos()).scale(-1).minus(bob.getPos());
		force = anchorMinusBob.scale(k).scale(-1);

		if (anchor.getLocked()) {
			bob.applyForce(force.scale(-1));
			// bob.applyForce(gravity.scale(-1));
			System.out.println("f" + anchorMinusBob + " " + force);
		} else {
			bob.applyForce(force.scale(-1).scale(0.5));
			// bob.applyForce(gravity.scale(-1));
			anchor.applyForce(force.scale(0.5));
			// anchor.applyForce(gravity.scale(-1));
		}
		// damping

		// bob.move();
		// anchor.move();
	}

	// // draw the ball
	// public void draw() {
	// StdDraw.line(bob.getPos().getx(), bob.getPos().gety(),
	// anchor.getPos().getx(), anchor.getPos().gety());
	// System.out.println("inside String draw bob " + bob.getPos().getx() + " " +
	// bob.getPos().gety());
	// System.out.println("inside String draw anc " + anchor.getPos().getx() + " " +
	// anchor.getPos().gety());

	// bob.draw();

	// // System.out.println(bob.getx()+" "+bob.gety());
	// anchor.draw();

	// }

	// public Vector getPos() {
	// return bob;
	// }
	public Obj getBob() {
		return bob;

	}

	public Obj getAnchor() {
		return anchor;

	}

	// public void updateAnchor(Obj anc) {
	// this.anchor = anc;
	// System.out.println("inside String updateanc bob " + bob.getPos().getx() + " "
	// + bob.getPos().gety());
	// System.out.println("inside String updateanc anc " + anchor.getPos().getx() +
	// " " + anchor.getPos().gety());

	// }

	// public void updateBob(Obj bob) {
	// this.bob = bob;
	// System.out.println("inside String updatebob bob " + bob.getPos().getx() + " "
	// + bob.getPos().gety());
	// System.out.println("inside String updatebob anc " + anchor.getPos().getx() +
	// " " + anchor.getPos().gety());

	// }

	// // method used only for setting the size of the window
	// public void settings() {
	// size(500, 500);
	// }

	// identical use to draw in Prcessing IDE
	public void display() {

		// b1.display();

		// b1.move();

		// line((float) bob.getPos().getx(), (float) bob.getPos().gety(), (float)
		// anchor.getPos().getx(),
		// (float) anchor.getPos().gety());
		// anchor.display();
		p5.push();
		p5.translate((float) anchor.getPos().getx(), (float) anchor.getPos().gety());

		float angle = p5.atan2((float) (bob.getPos().getx() - anchor.getPos().getx()),
				(float) (bob.getPos().gety() - anchor.getPos().gety()));
		p5.rotate(-angle);
		// p5.image(ball, 0, 0);
		// image(ball,0,0, (float)radius*2, (float)radius*2 );
		p5.image(spring, 0 - 10, 0, (float) 10 * 2, (float) bob.getPos().distanceTo(anchor.getPos()));

		p5.pop();
		System.out.println("inside String draw bob " + bob.getPos().getx() + " " + bob.getPos().gety());
		System.out.println("inside String draw anc " + anchor.getPos().getx() + " " + anchor.getPos().gety());

		// bob.display();

		// System.out.println(bob.getx()+" "+bob.gety());

	}

	// test client
	public static void main(String[] args) {

		// create and initialize two balls
		// PApplet.main("spring_and_forces.Spring");
		// Ball b1 = new Ball();

		// double rx = 0.5;
		// double ry = -0.9;
		// double anchorx = 0.0;
		// double anchory = 1;
		// double restLength = 0.25;
		// double vx = 0.1;
		// double vy = 0.0;
		// double[] anchorData = { anchorx, anchory };
		// double[] bobData = { rx, ry };
		// Vector anchorVec = new Vector(anchorData);
		// Vector bobVec = new Vector(bobData);
		// Particle bob = new Particle(bobVec);
		// Particle anchor = new Particle(anchorVec);
		// double[] bob_velData = { vx, vy };
		// Vector bob_vel = new Vector(bob_velData);

		// double radius = 0.005;

		// anchor.setLocked(true);

		// double[] gravityData = { 0.0, 0.001 };
		// Vector gravity = new Vector(gravityData);

		// double springCoeff = 0.02;
		// Spring s1 = new Spring(anchor, bob, bob_vel, gravity, restLength, radius,
		// springCoeff);
		// animate them
		// StdDraw.setXscale(-1.0, +1.0);
		// StdDraw.setYscale(-1.0, +1.0);
		// StdDraw.enableDoubleBuffering();

		// while (true) {
		// StdDraw.clear(StdDraw.GRAY);
		// StdDraw.setPenColor(StdDraw.BLACK);
		// s1.move();

		// s1.draw();

		// StdDraw.show();
		// StdDraw.pause(10);
		// }
	}

}
