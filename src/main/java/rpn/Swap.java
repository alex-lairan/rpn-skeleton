package rpn;

import java.util.Stack;

public class Swap implements Operator {
    private Item second;

    Swap(Stack<Item> stack) {
        Item first = stack.pop();
        second = stack.pop();

        stack.push(first);
    }

    @Override
    public double process() {
        return second.process();
    }
}
