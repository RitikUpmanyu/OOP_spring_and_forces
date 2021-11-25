package spring_and_forces;

import java.util.ArrayList;

public class HelperFunctions {
    public static double convertToRealx(double x, double screenSizex, double screenSizey, double angle) {
        double k = (screenSizey / Math.tan(Math.toRadians(angle))) - 100;
        double y = (screenSizex - k) / 2;
        double z = (x / screenSizex) * (k);
        if (y > 0) {
            return y + z;
        } else
            return x;
    }

    public static double convertToOriginalx(double x, double screenSizex, double screenSizey, double angle) {
        double k = (screenSizey / Math.tan(Math.toRadians(angle))) - 100;
        double y = (screenSizex - k) / 2;
        double z = ((x - y) * screenSizex / (screenSizey - 100 * Math.tan(Math.toRadians(angle))))
                * Math.tan(Math.toRadians(angle));
        if (y > 0) {
            return z;
        } else
            return x;
    }

    public static Obj filterbyId(int id, Obj notFound, ArrayList<Ball> balls, ArrayList<Ring> rings,
            ArrayList<Cylinder> cylinders, ArrayList<Box> boxes) {

        for (Obj o : balls) {
            if (o.id == id)
                return o;
        }
        for (Obj o : rings) {
            if (o.id == id)
                return o;
        }
        for (Obj o : cylinders) {
            if (o.id == id)
                return o;
        }
        for (Obj o : boxes) {
            if (o.id == id)
                return o;
        }
        return notFound;
    }

    public static void helperApplyForce(Vector force, ArrayList<Ball> balls, ArrayList<Ring> rings,
            ArrayList<Cylinder> cylinders, ArrayList<Box> boxes) {
        for (Ball o : balls) {
            o.applyForce(force);
        }
        for (Ring o : rings) {
            o.applyForce(force);
        }
        for (Cylinder o : cylinders) {
            o.applyForce(force);
        }
        for (Box o : boxes) {
            o.applyForce(force);
        }
    }

    public static void helperHandleSlope(Slope slope, ArrayList<Ball> balls, ArrayList<Ring> rings,
            ArrayList<Cylinder> cylinders, ArrayList<Box> boxes, ArrayList<Spring> springs) {
        for (Ball o : balls) {
            o.handleSlope(slope);
        }
        for (Ring o : rings) {
            o.handleSlope(slope);
        }
        for (Cylinder o : cylinders) {
            o.handleSlope(slope);
        }
        for (Box o : boxes) {
            o.handleSlope(slope);
        }
        for (Spring o : springs) {
            o.handleSlope(slope);
        }

    }

    public static void helperDisplay(ArrayList<Ball> balls, ArrayList<Ring> rings, ArrayList<Cylinder> cylinders,
            ArrayList<Box> boxes, ArrayList<Spring> springs) {
        for (Ball o : balls) {
            o.display();
        }
        for (Ring o : rings) {
            o.display();
        }
        for (Cylinder o : cylinders) {
            o.display();
        }
        for (Box o : boxes) {
            o.display();
        }
        for (Spring o : springs) {
            o.display();
        }

    }

    public static void helperMove(ArrayList<Ball> balls, ArrayList<Ring> rings, ArrayList<Cylinder> cylinders,
            ArrayList<Box> boxes, ArrayList<Spring> springs) {
        for (Ball o : balls) {
            o.move();
        }
        for (Ring o : rings) {
            o.move();
        }
        for (Cylinder o : cylinders) {
            o.move();
        }
        for (Box o : boxes) {
            o.move();
        }
        for (Spring o : springs) {
            o.move();
        }

    }
}
