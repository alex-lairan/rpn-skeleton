package rpn.operator;

import java.util.Stack;

public class Drop implements Operator {
    private Item number;
    private Item remaining;

    public Drop(Stack<Item> stack) {
        this.number = stack.pop();

        for(int i = 0; i < this.number.process(); ++i) {
            stack.pop();
        }
        this.remaining = stack.pop();
    }

    @Override
    public double process() {
        return remaining.process();
    }
}
