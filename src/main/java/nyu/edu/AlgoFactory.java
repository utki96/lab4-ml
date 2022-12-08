package nyu.edu;

import nyu.edu.algos.Bayes;
import nyu.edu.algos.KMeans;
import nyu.edu.algos.KNN;
import nyu.edu.algos.MLAlgo;
import nyu.edu.dto.Args;

public class AlgoFactory {

    public static MLAlgo getAlgoInstance(Args args) {
        if (args.getC() > 0 && args.getK() > 0) {
            throw new RuntimeException("Invalid parameters C: " + args.getC() + ", K: " + args.getK());
        }
        if (args.getInitialCentroids() != null && ! args.getInitialCentroids().isEmpty()) {
            return new KMeans(args.getInitialCentroids().size(), args.getInitialCentroids(), args.getDistanceFunc(), args.isVerbose());
        }
        if (args.getK() > 0) {
            return new KNN(args.getK(), args.isVerbose());
        }
        return new Bayes(args.getC(), args.isVerbose());
    }
}
