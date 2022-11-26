package nyu.edu.algos;

import nyu.edu.dto.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans extends MLAlgo {

    private int k;
    private List<Point> centroids;
    private Map<Integer, List<Integer>> centroidMap;   // Map of (centroid index -> training data index)

    private String DISTANCE_FUN = "e2";

    public KMeans(int k, List<Point> centroids) {
        this.k = k;
        this.centroids = centroids;
        this.centroidMap = new HashMap<>();
    }

    public KMeans(int k, List<Point> centroids, String distanceFunc) {
        this.k = k;
        this.centroids = centroids;
        this.centroidMap = new HashMap<>();
        this.DISTANCE_FUN = distanceFunc;
    }

    @Override
    public void trainModel() {
        boolean isFirstIteration = true;
        while (isFirstIteration || centroidsChanged()) {
            centroidMap = new HashMap<>();
            assignPointsToCentroids();
            isFirstIteration = false;
        }
    }

    private boolean centroidsChanged() {
        boolean centroidChanged = false;
        int dimensions = trainingData.get(0).length;
        for (int centroidIndex = 0; centroidIndex < this.k; centroidIndex++) {
            List<Double> centroidPoint = new ArrayList<>();
            for (int dimension = 0; dimension < dimensions; dimension++) {
                centroidPoint.add(0.0);
            }
            for (Integer pointIndex : centroidMap.getOrDefault(centroidIndex, new ArrayList<>())) {
                for (int dimension = 0; dimension < dimensions; dimension++) {
                    centroidPoint.set(dimension, centroidPoint.get(dimension) + trainingData.get(pointIndex)[dimension]);
                }
            }
            for (int dimension = 0; dimension < dimensions; dimension++) {
                int pointCount = centroidMap.getOrDefault(centroidIndex, new ArrayList<>()).size();
                centroidPoint.set(dimension, centroidPoint.get(dimension) / (pointCount != 0 ? pointCount : 1));
                if (Double.compare(centroids.get(centroidIndex).getPositionForDimension(dimension), centroidPoint.get(dimension)) != 0) {
                    centroidChanged = true;
                }
            }
            centroids.set(centroidIndex, new Point(centroidPoint));
        }
        return centroidChanged;
    }

    private void assignPointsToCentroids() {
        for (int trainingIndex = 0; trainingIndex < trainingData.size(); trainingIndex++) {
            double minDist = Double.MAX_VALUE;
            int minIndex = -1;
            for (int centroidIndex = 0; centroidIndex < this.k; centroidIndex++) {
                double centroidDist = getPointDistFromCentroid(trainingIndex, centroidIndex);
                if (centroidDist < minDist) {
                    minDist = centroidDist;
                    minIndex = centroidIndex;
                }
            }
            List<Integer> centroidPoints = centroidMap.getOrDefault(minIndex, new ArrayList<>());
            centroidPoints.add(trainingIndex);
            centroidMap.put(minIndex, centroidPoints);
        }
    }

    private double getPointDistFromCentroid(int pointIndex, int centroidIndex) {
        double distance = 0.0;
        for (int dimension = 0; dimension < trainingData.get(0).length; dimension++) {
            if (DISTANCE_FUN.equalsIgnoreCase("manh")) {
                distance += Math.abs(trainingData.get(pointIndex)[dimension] - centroids.get(centroidIndex).getPositionForDimension(dimension));
            } else {
                distance += Math.pow(trainingData.get(pointIndex)[dimension] - centroids.get(centroidIndex).getPositionForDimension(dimension), 2);
            }
        }
        return distance;
    }

    @Override
    public void testModel() {
        // No testing required for K-Means
    }

    @Override
    public void evaluateModel() {
        for (Integer centroidIndex : centroidMap.keySet()) {
            System.out.printf("C%d = {", centroidIndex+1);
            for (int index = 0; index < centroidMap.getOrDefault(centroidIndex, new ArrayList<>()).size(); index++) {
                System.out.print(trainingLabels.get(centroidMap.get(centroidIndex).get(index)));
                if (index < centroidMap.get(centroidIndex).size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        for (Point centroid : centroids) {
            System.out.println(centroid.toString());
        }
    }
}
