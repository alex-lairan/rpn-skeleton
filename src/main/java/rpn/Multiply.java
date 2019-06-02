package rpn;

import java.util.Stack;

public class Multiply implements Operator {
    private Item x;
    private Item y;

    Multiply(Stack<Item> stack) {
        this.x = stack.pop();
        this.y = stack.pop();
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
