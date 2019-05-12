package rpn;

public class Number implements Item {
    private double value;

    Number(double value) {
        this.value = value;
    }

    public double process() {
        return this.value;
    }

    public String toString() {
        return "Number(" + this.value + ")";
    }
}
