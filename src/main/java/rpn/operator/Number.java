package rpn.operator;

public class Number implements Item {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    public double process() {
        return this.value;
    }

    public String toString() {
        return "Number(" + this.value + ")";
    }
}
