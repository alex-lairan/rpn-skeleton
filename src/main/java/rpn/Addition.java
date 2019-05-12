package rpn;

public class Addition extends Operator {
    Addition(Item x, Item y) {
        super(x, y);
    }

    @Override
    public double process() {
        return x.process() + y.process();
    }

    public String toString() {
        return "Addition(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}


