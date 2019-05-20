package rpn;

import java.util.Stack;

public class Absolute extends Operator {

    Absolute(Stack<Item> stack) {
        super(stack.pop(),null);
    }

    @Override
    public double process() {
        return Math.abs(x.process());
    }
}
