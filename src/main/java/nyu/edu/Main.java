package nyu.edu;

import nyu.edu.algos.MLAlgo;
import nyu.edu.dto.Args;

public class Main {
    public static void main(String[] args) {
        try {
            Args configArgs = new Args();
            for (String arg : args) {
                InputReader.updateArgs(configArgs, arg);
            }
            MLAlgo algo = AlgoFactory.getAlgoInstance(configArgs);
            algo.readTrainingAndTestingData(configArgs.getTrainFile(), configArgs.getTestFile());
            algo.trainModel();
            algo.testModel();
            algo.evaluateModel();
        } catch (Exception ex) {
            System.out.println("Error: " + (ex.getMessage() == null ? ex : ex.getMessage()));
        }
    }
}