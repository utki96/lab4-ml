package nyu.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class Args {

    private String trainFile;
    private String testFile;
    private int k;
    private int c;
    private String distanceFunc;
    private boolean isVerbose;
    private List<Point> initialCentroids;

    public Args() {
        this.k = 0;
        this.c = 0;
        this.isVerbose = false;
        this.initialCentroids = new ArrayList<>();
    }

    public String getTrainFile() {
        return trainFile;
    }

    public void setTrainFile(String trainFile) {
        this.trainFile = trainFile;
    }

    public String getTestFile() {
        return testFile;
    }

    public void setTestFile(String testFile) {
        this.testFile = testFile;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getDistanceFunc() {
        return distanceFunc;
    }

    public void setDistanceFunc(String distanceFunc) {
        this.distanceFunc = distanceFunc;
    }

    public boolean isVerbose() {
        return isVerbose;
    }

    public void setVerbose(boolean verbose) {
        isVerbose = verbose;
    }

    public List<Point> getInitialCentroids() {
        return initialCentroids;
    }

    public void addInitialCentroid(Point centroid) {
        this.initialCentroids.add(centroid);
    }
}
