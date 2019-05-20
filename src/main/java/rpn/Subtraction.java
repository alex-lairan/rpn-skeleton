package rpn;

import java.util.Stack;

public class Subtraction extends Operator {
    Subtraction(Stack<Item> stack){
        super(stack.pop(), stack.pop());
    }

    @Override
    public double process() {
        return y.process() - x.process();
    }

    public String toString() {
        return "Subtraction(" + this.x.toString() + ", " + this.y.toString() + ")";
    }
}
