package rpn;

public class Multiply extends Operator {
    Multiply(Item x, Item y) {
        super(x, y);
    }

    @Override
    public double process() {
        System.out.println(x.process());
        System.out.println(y.process());
        return x.process() * y.process();
    }

    public String toString() {
        return "Multiply(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
