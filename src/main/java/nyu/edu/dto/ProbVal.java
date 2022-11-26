package nyu.edu.dto;

public class ProbVal {

    public ProbVal(KeyValue prob, KeyValue condition, double val) {
        this.prob = prob;
        this.condition = condition;
        this.value = val;
    }
    private KeyValue prob;
    private KeyValue condition;
    private double value;

    public KeyValue getProb() {
        return prob;
    }

    public KeyValue getCondition() {
        return condition;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String val = "";
        if (this.prob != null) {
            val = this.prob.toString();
        }
        if (this.condition != null) {
            val += " | " + this.condition.toString();
        }
        return val;
    }
}
