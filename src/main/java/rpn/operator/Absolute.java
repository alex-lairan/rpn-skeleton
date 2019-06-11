package rpn.operator;

import java.util.Stack;

public class Absolute implements Operator {
    private Item x;

    public Absolute(Stack<Item> stack) {
        this.x = stack.pop();
    }

    @Override
    public double process() {
        return Math.abs(x.process());
    }
}
