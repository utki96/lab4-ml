package nyu.edu.algos;

import nyu.edu.dto.KeyValue;
import nyu.edu.dto.ProbVal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bayes extends MLAlgo {

    private double corr;
    private final String LABEL_COLUMN = "C";
    private Map<String, Double> probValues;

    public Bayes(double c, boolean isVerbose) {
        super();
        this.isVerbose = isVerbose;
        this.corr = c;
        this.probValues = new HashMap<>();
    }
    @Override
    public void trainModel() {
        if (testingData == null || testingData.isEmpty() || testLabels == null || testLabels.isEmpty()) {
            throw new RuntimeException("Bayes: Testing data and labels not loaded");
        }
        Map<Integer, Set<Double>> valueSetIndex = new HashMap<>();
        Set<String> labelSet = new HashSet<>(trainingLabels);
        getValuesForColumn(valueSetIndex);
        storeClassProb();
        storeConditionalProb(valueSetIndex, labelSet);
    }

    private void getValuesForColumn(Map<Integer, Set<Double>> valueSetIndex) {
        for (Double[] trainingData : this.trainingData) {
            for (int index = 0; index < trainingData.length; index++) {
                Set<Double> values = valueSetIndex.getOrDefault(index, new HashSet<>());
                values.add(trainingData[index]);
                valueSetIndex.put(index, values);
            }
        }
    }

    private void storeClassProb() {
        Map<String, Integer> labelCount = new HashMap<>();
        for (String label : trainingLabels) {
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
        }
        for (String label : labelCount.keySet()) {
            String probTerm = new ProbVal(new KeyValue(LABEL_COLUMN, label), null, 0.0).toString();
            probValues.put(probTerm, (double) labelCount.get(label) / (double) trainingLabels.size());
            if (isVerbose) {
                System.out.printf("P( %s ) = [ %d / %d ]\n", probTerm, labelCount.get(label), trainingLabels.size());
            }
        }
    }

    private void storeConditionalProb(Map<Integer, Set<Double>> valueSetIndex, Set<String> labelSet) {
        int trainingIndexes = trainingData.get(0).length;
        for (String label : labelSet) {
            KeyValue labelKeyValue = new KeyValue(LABEL_COLUMN, label);
            for (int columnIndex = 0; columnIndex < trainingIndexes; columnIndex++) {
                Map<Double, Integer> indexValCount = new HashMap<>();
                int valueCount = 0;
                for (int trainingDataIndex = 0; trainingDataIndex < trainingData.size(); trainingDataIndex++) {
                    if (! label.equalsIgnoreCase(trainingLabels.get(trainingDataIndex))) {
                        continue;
                    }
                    valueCount++;
                    indexValCount.put(trainingData.get(trainingDataIndex)[columnIndex],
                            indexValCount.getOrDefault(trainingData.get(trainingDataIndex)[columnIndex], 0) + 1);
                }
                for (Double indexVal : valueSetIndex.getOrDefault(columnIndex, new HashSet<>())) {
                    String probTerm = new ProbVal(new KeyValue(getTestingColumnLabel(columnIndex), Double.toString(indexVal)), labelKeyValue, 0.0).toString();
                    probValues.put(probTerm, getProbValue(indexValCount.getOrDefault(indexVal, 0), valueCount, valueSetIndex.getOrDefault(columnIndex, new HashSet<>()).size()));
                    if (isVerbose) {
                        System.out.printf("P( %s ) = [ %d / %d ]\n", probTerm, (int) (indexValCount.getOrDefault(indexVal, 0) + corr),
                                (int) (valueCount + valueSetIndex.getOrDefault(columnIndex, new HashSet<>()).size() * corr));
                    }
                }
            }
        }
    }

    private String getTestingColumnLabel(int index) {
        return "A" + index;
    }

    private double getProbValue(int trueCount, int totalCount, int domainCount) {
        return (trueCount + this.corr) / (totalCount + domainCount * this.corr);
    }

    @Override
    public void testModel() {
        Set<String> labelSet = new HashSet<>(trainingLabels);
        for (int index = 0; index < testingData.size(); index++) {
            predictedLabels.add(index, predictLabelForInput(index, labelSet));
        }
    }

    private String predictLabelForInput(int testIndex, Set<String> labelSet) {
        String predictedLabel = "";
        double maxValue = 0.0;
        for (String label : labelSet) {
            KeyValue labelKeyValue = new KeyValue(LABEL_COLUMN, label);
            double labelValue = probValues.getOrDefault(new ProbVal(labelKeyValue, null, 0.0).toString(), 0.0);
            for (int columnIndex = 0; columnIndex < testingData.get(0).length; columnIndex++) {
                labelValue = labelValue * probValues.getOrDefault(new ProbVal(new KeyValue(getTestingColumnLabel(columnIndex),
                        Double.toString(testingData.get(testIndex)[columnIndex])), labelKeyValue, 0.0).toString(), 0.0);
            }
            if (labelValue > maxValue) {
                maxValue = labelValue;
                predictedLabel = label;
            }
        }
        return predictedLabel;
    }
}
