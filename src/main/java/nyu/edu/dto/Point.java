package nyu.edu.dto;

import java.util.List;

public class Point {

    public Point(List<Double> positions) {
        this.position = positions;
    }

    private List<Double> position;

    public Double getPositionForDimension(int index) {
        return position.get(index);
    }

    @Override
    public String toString() {
        String res = "( ";
        for (Double val : position) {
            res += val + ", ";
        }
        return res.substring(0, res.lastIndexOf(", ")) + " )";
    }
}
