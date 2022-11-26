package nyu.edu;

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
}
