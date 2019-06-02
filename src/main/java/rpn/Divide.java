package rpn;

import java.util.Stack;

public class Divide implements Operator {
    private Item x;
    private Item y;

    Divide(Stack<Item> stack) {
        this.x = stack.pop();
        this.y = stack.pop();
    }

    @Override
    public double process() {
        return y.process() / x.process();
    }

    public String toString() {
        return "Divide(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
