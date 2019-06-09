import java.awt.*;

public class Planet {

//    instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public  String imgFileName;
    private static final double G = 6.67E-11;


    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    };

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }

    public void drawLine() {

        StdDraw.setPenColor(StdRandom.uniform(0,255), StdRandom.uniform(0,255), StdRandom.uniform(0,255));
        StdDraw.line(0,0,this.xxPos,this.yyPos);
    }



    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r = Math.sqrt(dx*dx+dy*dy);
        return r;
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double f = (G * this.mass * p.mass) / Math.pow(r,2);
        return f;
    }

    public double calcForceExertedByX(Planet p) {

        double dx = p.xxPos - this.xxPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return (f*dx) / r;
    }

    public double calcForceExertedByY(Planet p) {

        double dy = p.yyPos - this.yyPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return (f*dy) / r;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double forceX = 0;

        for (int i =0; i<p.length;i++) {
            if (this == p[i]) {
                continue;
            }

            double Fx = calcForceExertedByX(p[i]);
            forceX += Fx;
        }

        return  forceX;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double forceY = 0;

        for (int i =0; i<p.length;i++) {
            if (this == p[i]) {
                continue;
            }

            double Fy = calcForceExertedByY(p[i]);
            forceY += Fy;
        }

        return forceY;
    }

    public void update(double sec, double xForce, double yForce) {
        double ax = xForce / this.mass;
        double ay = yForce / this.mass;

        this.xxVel += ax * sec;
        this.yyVel += ay * sec;

        this.xxPos += this.xxVel * sec;
        this.yyPos += this.yyVel * sec;
    }



}
