package nyu.edu.algos;

import nyu.edu.InputReader;

import java.util.*;

public abstract class MLAlgo {

    public MLAlgo() {
        this.trainingData = new ArrayList<>();
        this.trainingLabels = new ArrayList<>();
        this.testingData = new ArrayList<>();
        this.testLabels = new ArrayList<>();
        this.predictedLabels = new ArrayList<>();
    }
    protected List<Double[]> trainingData;
    protected List<String> trainingLabels;
    protected List<Double[]> testingData;
    protected List<String> testLabels;
    protected List<String> predictedLabels;

    public void readTrainingAndTestingData(String trainingFile, String testingFile) {
        InputReader.readData(trainingFile, trainingData, trainingLabels);
        if (testingFile != null && ! testingFile.isEmpty()) {
            InputReader.readData(testingFile, testingData, testLabels);
        }
    }

    public abstract void trainModel();

    public abstract void testModel();

    public void evaluateModel() {
        Map<String, Integer> want = new HashMap<>(), got = new HashMap<>(), correctGuess = new HashMap<>();
        for (int i = 0; i < testLabels.size(); i++) {
            String expected = testLabels.get(i).trim(), computed = predictedLabels.get(i).trim();
            if (expected.equalsIgnoreCase(computed)) {
                correctGuess.put(expected, correctGuess.getOrDefault(expected, 0) + 1);
            }
            want.put(expected, want.getOrDefault(expected, 0) + 1);
            got.put(computed, got.getOrDefault(computed, 0) + 1);
            System.out.println("want=" + expected + "  got=" + computed);
        }
        List<String> labels = new ArrayList<>(new HashSet<>(trainingLabels));
        Collections.sort(labels);
        for (String label : labels) {
            int correctGuessLabel = correctGuess.getOrDefault(label, 0);
            System.out.printf("Label=%s  Precision=%d/%d  Recall=%d/%d%n", label, correctGuessLabel, got.getOrDefault(label, 0),
                    correctGuessLabel, want.getOrDefault(label, 0));
        }
    }
}
