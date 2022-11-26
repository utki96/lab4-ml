package nyu.edu;

import nyu.edu.algos.Bayes;
import nyu.edu.algos.KMeans;
import nyu.edu.algos.KNN;
import nyu.edu.algos.MLAlgo;
import nyu.edu.dto.Point;

import java.util.List;

public class AlgoFactory {

    public static MLAlgo getAlgoInstance(int c, int k, List<Point> centroids) {
        if (k > 0) {
            if (centroids != null && ! centroids.isEmpty()) {
                return new KMeans(centroids.size(), centroids);
            }
            return new KNN(k);
        }
        return new Bayes(c);
    }
}
