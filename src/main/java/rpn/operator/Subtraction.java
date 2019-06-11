package rpn.operator;

import java.util.Stack;

public class Subtraction implements Operator {
    private Item x;
    private Item y;

    public Subtraction(Stack<Item> stack) {
        this.x = stack.pop();
        this.y = stack.pop();
    }

    @Override
    public double process() {
        return y.process() - x.process();
    }

    public String toString() {
        return "Subtraction(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
