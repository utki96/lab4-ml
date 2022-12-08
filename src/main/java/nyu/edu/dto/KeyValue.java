package nyu.edu.dto;

public class KeyValue {

    public KeyValue(String key, String val) {
        this.key = key;
        this.value = val;
    }
    private String key;
    private String value;

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}
