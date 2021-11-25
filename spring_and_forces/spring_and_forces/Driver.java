package spring_and_forces;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.opencsv.CSVWriter;

import processing.core.PApplet;
import processing.core.PImage;

public class Driver extends PApplet {
	public Slope slope;
	PImage bg;
	public static int angle_of_slope;
	public static double g, f, k, wind;
	private Vector gforce;
	public static int N;
	private static final String INPUTPATH = "input.txt";
	private static final String OUTPUTPATH = "output.csv";
	private static final int NOOFSEC = 50;
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
	public static ArrayList<Ring> rings = new ArrayList<Ring>();
	public static ArrayList<Cylinder> cylinders = new ArrayList<Cylinder>();
	public static ArrayList<Box> boxes = new ArrayList<Box>();
	public static ArrayList<Spring> springs = new ArrayList<Spring>();
	private int count = 0;
	double p[] = { 0, 0 };
	Vector pos = new Vector(p);
	Obj notFound = new Obj(this, -1, pos, 0, pos);

	private Obj filterbyId(int id) {

		return HelperFunctions.filterbyId(id, notFound, balls, rings, cylinders, boxes);
	}

	private void helperApplyForce(Vector force) {
		HelperFunctions.helperApplyForce(force, balls, rings, cylinders, boxes);
	}

	private void helperHandleSlope(Slope slope) {
		HelperFunctions.helperHandleSlope(slope, balls, rings, cylinders, boxes, springs);

	}

	private void helperDisplay() {
		HelperFunctions.helperDisplay(balls, rings, cylinders, boxes, springs);

	}

	private void helperMove() {
		HelperFunctions.helperMove(balls, rings, cylinders, boxes, springs);

	}

	public void settings() {
		size(1000, 700);
	}

	public CSVWriter writer;

	// identical use to setup in Processing IDE except for size()
	public void setup() {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(INPUTPATH));
			System.setIn(in);
		} catch (Error e) {
			System.out.println(e);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Define Global Vars: in order \nacc due to gravity\ncoeff of friction\nspring constant(if not applicable put 0)\n wind");

		g = sc.nextDouble();
		f = sc.nextDouble();
		k = sc.nextDouble();
		wind = sc.nextDouble();
		System.out.println("Enter angle of slope: (put 0 in case of ground)");
		angle_of_slope = sc.nextInt();
		System.out.println("Enter number of objects:");
		N = sc.nextInt();
		System.out.println("Please enter N lines each containing information for an object in following format: ");
		System.out.println(
				"( Object Id, Object Type Id, intial coordinate x,  …arguments)\nArguments of each object should be given in specified format as mentioned below.\nNote that arguments marked * are required!\nFollowing are the valid Object Ids and formats of arguments for the available objects:\nObject: Ball; ID:00  TypeID: 1; Arguments (x_coordinate, x_velocity, y_velocity, radius, mass )");
		System.out.println(
				"Object: Box; Id:01 TypeID: 2; Arguments (x_coordinate,  x_velocity, y_velocity, length, width, mass)");
		System.out.println(
				"Object: Ring; Id:02 TypeID: 3; Arguments (x_coordinate,  x_velocity, y_velocity, radius, mass )");
		System.out.println(
				"Object: Cylinder; Id:03 TypeID: 4;  Arguments (x_coordinate,  x_velocity, y_velocity, radius, mass )");
		System.out.println(
				"Object: Spring; Id:04 TypeID: 5;  Arguments (Obj id of anchor, Obj id of bob,  restlength, radius, 0/1(make anchor locked?))");
		int id = 0;
		int typeid = 00;
		slope = new Slope(this, 999, f, angle_of_slope);
		for (int i = 0; i < N; i++) {
			id = sc.nextInt();
			typeid = sc.nextInt();
			switch (typeid) {
			case 1:
				double bx = HelperFunctions.convertToRealx(sc.nextDouble(), 1000, 700, slope.getAngle());
				// double by = sc.nextDouble();
				double bvx = sc.nextDouble();
				double bvy = sc.nextDouble();
				double br = sc.nextDouble();
				double bm = sc.nextDouble();
				double[] bpos = { bx, 0 };
				Vector b = new Vector(bpos);
				double[] bvel = { bvx, bvy };
				Vector bv = new Vector(bvel);
				Ball b1 = new Ball(this, id, b, bv, br);
				balls.add(b1);
				System.out.println("Ball" + typeid + " " + id + " " + bx + " " + " " + bvx + " " + bvy + " " + br);
				break;
			case 2:
				double boxx = HelperFunctions.convertToRealx(sc.nextDouble(), 1000, 700, slope.getAngle());
				// double boxy = sc.nextDouble();
				double boxvx = sc.nextDouble();
				double boxvy = sc.nextDouble();
				double boxl = sc.nextDouble();
				double boxw = sc.nextDouble();
				double boxr = boxl / 2;
				double boxm = sc.nextDouble();

				double[] boxpos = { boxx, 0 };
				Vector box = new Vector(boxpos);
				double[] boxvel = { boxvx, boxvy };
				Vector boxv = new Vector(boxvel);
				Box box1 = new Box(this, id, box, boxv, boxl, boxw, boxr, angle_of_slope);
				boxes.add(box1);
				System.out.println("Box");
				break;
			case 3:
				double rx = HelperFunctions.convertToRealx(sc.nextDouble(), 1000, 700, slope.getAngle());
				// double ry = sc.nextDouble();
				double rvx = sc.nextDouble();
				double rvy = sc.nextDouble();
				double rr = sc.nextDouble();
				double rm = sc.nextDouble();

				double[] rpos = { rx, 0 };
				Vector r = new Vector(rpos);
				double[] rvel = { rvx, rvy };
				Vector rv = new Vector(rvel);
				Ring r1 = new Ring(this, id, r, rv, rr);
				rings.add(r1);
				System.out.println("ring" + typeid + " " + id + " " + rx + " " + " " + rvx + " " + rvy + " " + rr);

				break;
			case 4:
				double cx = HelperFunctions.convertToRealx(sc.nextDouble(), 1000, 700, slope.getAngle());
				// double cy = sc.nextDouble();
				double cvx = sc.nextDouble();
				double cvy = sc.nextDouble();
				double cr = sc.nextDouble();
				double cm = sc.nextDouble();

				double[] cpos = { cx, 0 };
				Vector c = new Vector(cpos);
				double[] cvel = { cvx, cvy };
				Vector cv = new Vector(cvel);
				Cylinder c1 = new Cylinder(this, id, c, cv, cr);
				cylinders.add(c1);
				System.out.println("cylinder" + typeid + " " + id + " " + cx + " " + " " + cvx + " " + cvy + " " + cr);

				break;
			case 5:

				int id1 = sc.nextInt();
				int id2 = sc.nextInt();

				double[] sv = { 0, 0 };
				Vector svel = new Vector(sv);
				double gravityData[] = { 0.0000000001, -g };
				Vector gravity = new Vector(gravityData);
				double[] spos = { 400, 0 };
				Vector sp = new Vector(spos);
				double rest = sc.nextDouble();
				double radius = sc.nextDouble();
				while (this.filterbyId(id1).id == -1 || this.filterbyId(id2).id == -1) {
					System.out.println("enter valid objects id which exists");
					id1 = sc.nextInt();
					id2 = sc.nextInt();
				}
				int anchorLocked = sc.nextInt();
				if (anchorLocked == 1) {
					double xcoord = this.filterbyId(id2).getPos().getx();
					double[] aposData = { xcoord, slope.getycord(xcoord) + this.filterbyId(id2).radius };
					Vector pos = new Vector(aposData);
					this.filterbyId(id2).setPos(pos);
					this.filterbyId(id2).setLocked(true);
				}
				Spring spring = new Spring(this, id, sp, radius, this.filterbyId(id1), this.filterbyId(id2), svel,
						gravity, rest, k);
				springs.add(spring);
				System.out.println("spring" + typeid + " " + id + " " + id1 + " " + id2 + " " + rest + " " + radius
						+ " " + anchorLocked);

				break;

			}

		}

		stroke(255);
		strokeWeight(10);
		frameRate(10);
		bg = loadImage("sky.jpg");
		bg.resize(1000, 700);

		if (wind == 0)
			wind = 0.00000000001;
		double forceData[] = { wind, g };
		gforce = new Vector(forceData);

		try {
			writer = new CSVWriter(new FileWriter(OUTPUTPATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// identical use to draw in Prcessing IDE
	public void draw() {
		background(bg);

		slope.display();

		this.helperApplyForce(gforce);
		this.helperHandleSlope(slope);
		this.helperDisplay();
		this.helperMove();
		count++;
		if (count <= 50) {

			try {
				this.writeTocsv(writer, count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void writeTocsv(CSVWriter writer, int i) throws Exception {

		// Writing data to a csv file
		String line1[] = { "id", "objtype", "y-position", "x-position", "angle in deg" };
		// String line2[] = {"1", "Krishna", "2548", "2012-01-01", "IT"};
		// String line3[] = {"2", "Vishnu", "4522", "2013-02-26", "Operations"};
		// String line4[] = {"3", "Raja", "3021", "2016-10-10", "HR"};
		// String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
		// Writing data to the csv file
		writer.writeNext(line1);
		// for (int i = 0; i < NOOFSEC; i++) {
		String line6[] = { "sec", Integer.toString(i), "", "", "" };
		writer.writeNext(line6);
		for (Ball o : balls) {
			String line2[] = { Integer.toString(o.id), "Ball", Double.toString(o.getPos().gety()),
					Double.toString(HelperFunctions.convertToRealx(o.getPos().getx(), 1000, 700, slope.getAngle())),
					Double.toString(o.getAngle()) };
			writer.writeNext(line2);
		}
		for (Ring o : rings) {
			String line3[] = { Integer.toString(o.id), "Ring", Double.toString(o.getPos().gety()),
					Double.toString(HelperFunctions.convertToRealx(o.getPos().getx(), 1000, 700, slope.getAngle())),
					Double.toString(o.getAngle()) };
			writer.writeNext(line3);
		}
		for (Cylinder o : cylinders) {
			String line4[] = { Integer.toString(o.id), "Cylinder", Double.toString(o.getPos().gety()),
					Double.toString(HelperFunctions.convertToRealx(o.getPos().getx(), 1000, 700, slope.getAngle())),
					Double.toString(o.getAngle()) };
			writer.writeNext(line4);
		}
		for (Box o : boxes) {
			String line5[] = { Integer.toString(o.id), "Box", Double.toString(o.getPos().gety() - o.getLength() / 2),
					Double.toString(HelperFunctions.convertToRealx(o.getPos().getx(), 1000, 700, slope.getAngle())),
					"-" };
			writer.writeNext(line5);
		}

		// }
		// this.setup();
		// writer.writeNext(line3);
		// writer.writeNext(line4);
		// Flushing data from writer to file
		writer.flush();
		System.out.println("Data entered");
	}

	public static void main(String[] args) {

		PApplet.main("spring_and_forces.Driver");
	}

}
