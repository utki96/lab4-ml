package nyu.edu.algos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KNN extends MLAlgo {

    private class IndexValue {
        public int index;
        public double value;

        public IndexValue(int ind, double val) {
            this.index = ind;
            this.value = val;
        }
    }

    private int kValue;

    public KNN(int k, boolean isVerbose) {
        super();
        this.isVerbose = isVerbose;
        this.kValue = k;
    }

    @Override
    public void trainModel() {
        // No training required for kNN
    }

    @Override
    public void testModel() {
        if (testingData == null || testingData.isEmpty() || testLabels == null || testLabels.isEmpty()) {
            throw new RuntimeException("KNN: Testing data and labels not loaded");
        }
        for (int i = 0; i < testingData.size(); i++) {
            predictedLabels.add(i, getLabelForInput(i));
        }
    }

    private String getLabelForInput(int testIndex) {
        PriorityQueue<IndexValue> distancesPQ = new PriorityQueue<>(Comparator.comparingDouble(a -> a.value));
        int trainingIndex = 0, k = this.kValue;
        for (Double[] inputAttrs : trainingData) {
            double eucDistSq = 0.0;
            for (int i = 0; i < inputAttrs.length; i++) {
                eucDistSq += Math.pow((inputAttrs[i] - testingData.get(testIndex)[i]), 2);
            }
            distancesPQ.add(new IndexValue(trainingIndex, eucDistSq));
            trainingIndex++;
        }
        String label = "";
        double maxWeight = -1.0;
        Map<String, Double> labelCount = new HashMap<>();
        while (k-- > 0) {
            IndexValue indexValue = distancesPQ.poll();
            String popLabel = trainingLabels.get(indexValue.index);
            double voteWeight = indexValue.value != 0 ? (1.0 / indexValue.value) : 999;
            labelCount.put(popLabel, labelCount.getOrDefault(popLabel, 0.0) + voteWeight);
            if (labelCount.get(popLabel) >= maxWeight) {
                maxWeight = labelCount.get(popLabel);
                label = popLabel;
            }
        }
        return label;
    }
}
