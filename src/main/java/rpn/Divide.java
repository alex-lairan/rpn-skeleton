package rpn;

public class Divide extends Operator {
    Divide(Item x, Item y) {
        super(x, y);
    }

    @Override
    public double process() {
        return y.process() / x.process();
    }

    public String toString() {
        return "Divide(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
