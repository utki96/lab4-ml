package nyu.edu.dto;

public class KeyValue {

    public KeyValue(String key, String val) {
        this.key = key;
        this.value = val;
    }
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}
