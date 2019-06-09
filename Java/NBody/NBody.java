public class NBody {

    public static void main(String[] args) {
        double t = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet [] planets = readPlanets(filename);
        double r= readRadius(filename);
        double[] audio = StdAudio.read("audio/2001.mid");
        StdAudio.play(audio);

        StdDraw.setScale(-r, r);

        /* Clears the drawing window. */
        StdDraw.clear();


        drawBackGround(planets);
        enableDoubleBuffering(t,r, dt,planets);
    }

    public static void drawBackGround(Planet[] planets) {
        StdDraw.picture(0, 0,"images/starfield.jpg");

        for (int i =0; i<planets.length; i++) {
            planets[i].drawLine();
            planets[i].draw();
        }
    }


    public static void enableDoubleBuffering(double T, double radius, double dt,Planet[] p) {

        double tV = 0;
        int nPlanets = p.length;
        double netXForces [] = new double[nPlanets];
        double netYForces [] = new double[nPlanets];
        int planetIndex = 0;





        StdDraw.enableDoubleBuffering();

        while(tV <= T) {

            for (int i =0;i<nPlanets; i++) {
                netXForces[i] = p[i].calcNetForceExertedByX(p);
                netYForces[i] = p[i].calcNetForceExertedByY(p);
            }

            p[planetIndex].update(dt,netXForces[planetIndex], netYForces[planetIndex]);
            StdDraw.clear();
            drawBackGround(p);
            StdDraw.show(10);


            planetIndex = (planetIndex+1) % nPlanets;
            tV += dt;
        }

        printFinalState(p,radius);
    }

    public static void printFinalState(Planet[] planets, double radius) {
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static double readRadius(String filename) {
        In in = new In(filename);

        int nPlanets = in.readInt();
        double radius = in.readDouble();

        in.close();

        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);

        int nPlanets = in.readInt();
        double radius = in.readDouble();
        Planet [] p = new Planet[nPlanets];
        int index = 0;

        /* Keep looking until the file is empty. */
        while(index!= nPlanets) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            p[index] = new Planet(xPos, yPos, xVel, yVel, mass, img);
            index++;
        }
        in.close();

        return p;
    }
}
