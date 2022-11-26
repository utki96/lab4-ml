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

    public KNN(int k) {
        super();
        this.kValue = k;
    }

    @Override
    public void trainModel() {
        // No training required for kNN
    }

    @Override
    public void testModel() {
        for (int i = 0; i < testingData.size(); i++) {
            predictedLabels.add(i, getLabelForInput(i));
        }
    }

    private String getLabelForInput(int testIndex) {
        PriorityQueue<IndexValue> distancesPQ = new PriorityQueue<>(Comparator.comparingDouble(a -> a.value));
        int trainingIndex = 0, k = this.kValue, maxCount = -1;
        for (Double[] inputAttrs : trainingData) {
            double eucDistSq = 0.0;
            for (int i = 0; i < inputAttrs.length; i++) {
                eucDistSq += Math.pow((inputAttrs[i] - testingData.get(testIndex)[i]), 2);
            }
            distancesPQ.add(new IndexValue(trainingIndex, eucDistSq));
            trainingIndex++;
        }
        String label = "";
        Map<String, Integer> labelCount = new HashMap<>();
        while (k-- > 0) {
            String popLabel = trainingLabels.get(distancesPQ.poll().index);
            labelCount.put(popLabel, labelCount.getOrDefault(popLabel, 0) + 1);
            if (labelCount.get(popLabel) >= maxCount) {
                maxCount = labelCount.get(popLabel);
                label = popLabel;
            }
        }
        return label;
    }
}
