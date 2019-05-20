package rpn;

import java.util.Stack;

public class Addition extends Operator {
    Addition(Stack<Item> stack) {
        super(stack.pop(), stack.pop());
    }

    @Override
    public double process() {
        return x.process() + y.process();
    }

    public String toString() {
        return "Addition(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}


