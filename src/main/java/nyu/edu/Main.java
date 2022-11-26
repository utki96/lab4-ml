package nyu.edu;

import nyu.edu.algos.MLAlgo;
import nyu.edu.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String trainFile = "/home/utkarshtyg/Documents/KMeans/km1.txt";
            String testFile = "/home/utkarshtyg/Documents/KMeans/km1.txt";
            MLAlgo algo = AlgoFactory.getAlgoInstance(0, 3, getPoints());
            algo.readTrainingAndTestingData(trainFile, testFile);
            algo.trainModel();
            algo.testModel();
            algo.evaluateModel();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static List<Point> getPoints() {
        List<Double> l1 = new ArrayList<>(), l2 = new ArrayList<>(), l3 = new ArrayList<>();
        l1.add(0.0);
        l1.add(0.0);
        l1.add(0.0);
        l2.add(200.0);
        l2.add(200.0);
        l2.add(200.0);
        l3.add(500.0);
        l3.add(500.0);
        l3.add(500.0);

        List<Point> points = new ArrayList<>();
        points.add(new Point(l1));
        points.add(new Point(l2));
        points.add(new Point(l3));
        return points;
    }
}