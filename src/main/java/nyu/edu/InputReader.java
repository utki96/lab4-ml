package nyu.edu;

import nyu.edu.dto.Args;
import nyu.edu.dto.Constants;
import nyu.edu.dto.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static void readData(String trainingFile, List<Double[]> trainingData, List<String> labels) throws RuntimeException {
        List<String> instructions = readInputFile(trainingFile);
        int varCount = -1;
        for (String instruction : instructions) {
            String[] vars = instruction.trim().split(",");
            if (varCount == -1) {
                varCount = vars.length;
            } else if (varCount != vars.length) {
                throw new RuntimeException("Invalid Input: Different count of attributes on different lines of inputs.");
            }
            Double[] attr = new Double[varCount - 1];

            for (int i = 0; i < varCount - 1; i++) {
                double val;
                try {
                    val = Double.parseDouble(vars[i].trim());
                } catch (Exception ex) {
                    throw new RuntimeException("Invalid Input: Failed to parse predictive attribute: " + vars[i].trim() + "Error: " + ex.getMessage());
                }
                attr[i] = val;
            }

            trainingData.add(attr);
            labels.add(vars[varCount-1].trim());
        }
    }

    private static List<String> readInputFile(String filePath) throws RuntimeException {
        List<String> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                if (! line.isEmpty()) {
                    instructions.add(line);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            throw new RuntimeException("Invalid path for file: " + filePath + ". Error msg: " + fnfEx.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to read Input File. Error msg: " + ex.getMessage());
        }
        return instructions;
    }

    public static void updateArgs(Args args, String inputArg) {
        if (inputArg.toLowerCase().startsWith(Constants.INPUT_TRAIN)) {
            args.setTrainFile(getInputValue(inputArg));
        } else if (inputArg.toLowerCase().startsWith(Constants.INPUT_TEST)) {
            args.setTestFile(getInputValue(inputArg));
        } else if (inputArg.equalsIgnoreCase(Constants.INPUT_VERBOSE)) {
            args.setVerbose(true);
        } else if (inputArg.toLowerCase().startsWith(Constants.INPUT_K)) {
            args.setK(getIntInputValue(inputArg));
        } else if (inputArg.toLowerCase().startsWith(Constants.INPUT_C)) {
            args.setC(getIntInputValue(inputArg));
        } else if (inputArg.toLowerCase().startsWith(Constants.INPUT_D)) {
            args.setDistanceFunc(getInputValue(inputArg));
        } else if (! inputArg.contains("=") && inputArg.contains(",")) {
            args.addInitialCentroid(getPointFromInput(inputArg));
        }
    }

    private static Point getPointFromInput(String inputArg) {
        String[] positions = inputArg.split(",");
        List<Double> pos = new ArrayList<>();
        for (String position : positions) {
            try {
                pos.add(Double.parseDouble(position));
            } catch (Exception ex) {
                throw new RuntimeException("Unable to parse centroid: " + inputArg);
            }
        }
        return new Point(pos);
    }

    private static int getIntInputValue(String inputArg) throws RuntimeException {
        try {
            return Integer.parseInt(getInputValue(inputArg));
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Failed to parse input param: %s, Error: %s", inputArg, ex.getMessage()));
        }
    }

    private static String getInputValue(String inputArg) throws RuntimeException {
        try {
            return inputArg.substring(inputArg.indexOf("=") + 1);
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Failed to parse input param: %s, Error: %s", inputArg, ex.getMessage()));
        }
    }
}
