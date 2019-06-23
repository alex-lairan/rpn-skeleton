package rpn.operator;

import rpn.bus.Bus;

import java.util.Stack;

public class Addition implements Operator {
    private Bus bus;
    private Item x;
    private Item y;

    public Addition(Stack<Item> stack) {
        this.x = stack.pop();
        this.y = stack.pop();
    }

    public Addition(Bus bus) {
        this.bus = bus;
    }

    @Override
    public double process() {
        return x.process() + y.process();
    }

    public String toString() {
        return "Addition(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}


