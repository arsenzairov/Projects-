package hw2;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;
import java.util.List;


public class PercolationStats {

    private static final int DELAY = 20;
    int nExperiments;
    int gridSize;
    PercolationFactory pf;
    StdRandom random;
    List<Double> percResults;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        validate(N,T);
        this.nExperiments = T;
        this.gridSize = N;
        this.pf = pf;
        initialize();
    }

    public void validate(int n, int t) {
        if (t <= 0 || n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public void initialize() {
        List<Double> percThreshold = new ArrayList<Double>();

        double counter = 0;
        double size = gridSize * gridSize;
        Percolation perc = pf.make(gridSize);
        PercolationVisualizer.draw(perc, gridSize);

        while(percThreshold.size() != nExperiments) {
            int row = random.uniform(0, gridSize);
            int col = random.uniform(0,gridSize);

            if (!perc.isOpen(row,col)) {
                counter++;
            }

            draw(row,col,gridSize,perc);

            if (perc.percolates()) {
               percThreshold.add(counter/size);
               perc = pf.make(gridSize);
               counter = 0;
            }
        }
        percResults = percThreshold;
    }

    /* sample mean of percolation threshold */
    public double mean() {
        double sum = 0;
        for (Double i : percResults) {
            sum+=i;
        }
        return sum/nExperiments;
    }

    /* sample standard deviation of percolation threshold */
    public double stddev() {
        double mean = mean();
        double numerator = 0;
        for (Double i : percResults) {
            double val = i-mean;
            numerator+=Math.pow(val,2);
        }
        return numerator/nExperiments-1;
    }

    /* low endpoint of 95% confidence interval */
    public double confidenceLow() {
        double mean = mean();
        double std = stddev();
        double numerator = 1.96 * std;
        return mean - (numerator/Math.sqrt(nExperiments));
    }

    /* high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        double mean = mean();
        double std = stddev();
        double numerator = 1.96 * std;
        return mean + (numerator/Math.sqrt(nExperiments));
    }



    public void draw(int row, int col, int N, Percolation perc) {
                if (!perc.isOpen(row, col)) {
                    StdOut.println(row + " " + col);
                }
                perc.open(row, col);

                // draw N-by-N percolation system
                PercolationVisualizer.draw(perc, N);
                StdDraw.show(DELAY);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats stats = new PercolationStats(4,20,pf);
        System.out.println(stats.confidenceLow());
        System.out.println(stats.confidenceHigh());
        System.out.println(stats.mean());
        System.out.println(stats.stddev());

    }

}
