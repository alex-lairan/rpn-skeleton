package rpn;

import java.util.Stack;

public class Divide extends Operator {
    Divide(Stack<Item> stack){
        super(stack.pop(), stack.pop());
    }

    @Override
    public double process() {
        return y.process() / x.process();
    }

    public String toString() {
        return "Divide(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
