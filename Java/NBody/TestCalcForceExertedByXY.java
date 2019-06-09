import java.math.*;

/**
 *  Tests calcForceExertedByXY
 */
public class TestCalcForceExertedByXY {

    /**
     *  Tests calcForceExertedByXY.
     */
    public static void main(String[] args) {
        checkCalcForceExertedByXY();
    }


    /** Checks whether two doubles are approximately equal.
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  eps         Tolerance for the double comparison.
     */
    private static boolean approxEqual(double actual, double expected, double eps) {
        return Math.abs(expected - actual) <= eps * Math.max(expected, actual);
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (approxEqual(actual, expected, eps)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
            if (approxEqual(actual, expected, eps)) {
                System.out.println("      Hint: Your answer is exactly opposite of the correct answer.");
            }
        }
    }

    /**
     *  Checks the Planet class to make sure calcForceExertedByXY works.
     */
    private static void checkCalcForceExertedByXY() {
        System.out.println("Checking calcForceExertedByX and calcForceExertedByY...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");


        Planet sun = new Planet(1.0e-12,2.0e-11,3.0,4.0,2.0e-30,"jupiter.gif");
        Planet saturn = new Planet(2.3e-12,9.5e-11,3.0,4.0,6.0e-26,"jupiter.gif");

        checkEquals(saturn.calcDistance(sun),1.5e-12,"calcDistance",0.01);
        checkEquals(sun.calcDistance(saturn),1.5e-12,"calcDistance",0.01);
        checkEquals(saturn.calcForceExertedBy(sun),3.6e-22,"calcForceExerted()", 0.01);
        checkEquals(saturn.calcForceExertedByX(sun),3.1e-22,"calcForceExertedByX()", 0.01);
        checkEquals(saturn.calcForceExertedByY(sun),1.8e-22,"calcForceExertedByY()", 0.01);

        checkEquals(sun.calcForceExertedByX(saturn),-3.1e-22,"calcForceExertedByX()", 0.01);
        checkEquals(sun.calcForceExertedByY(saturn),-1.8e-22,"calcForceExertedByY()", 0.01);



        checkEquals(p1.calcForceExertedByX(p2), 133.4, "calcForceExertedByX()", 0.01);
        checkEquals(p1.calcForceExertedByX(p3), 4.002e-11, "calcForceExertedByX()", 0.01);
        checkEquals(p1.calcForceExertedByY(p2), 0.0, "calcForceExertedByY()", 0.01);
        checkEquals(p1.calcForceExertedByY(p3), 5.336e-11, "calcForceExertedByY()", 0.01);
    }
}
