package rpn;

import java.util.Stack;

public class Times implements Operator {
    private Item x;
    private Item y;

    Times(Stack<Item> stack) {
        this.x = stack.pop();
        this.y = stack.pop();
    }

    @Override
    public double process() {
        double acc = 0;
        double processor = y.process();
        int times = (int) x.process();
        for(int i = 0; i < times; ++i) {
            acc += processor * Math.pow(10, i);
        }

        return acc;
    }
}
