package rpn;

public class Subtraction extends Operator {
    Subtraction(Item x, Item y) {
        super(x, y);
    }

    @Override
    public double process() {
        return y.process() - x.process();
    }

    public String toString() {
        return "Subtraction(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
