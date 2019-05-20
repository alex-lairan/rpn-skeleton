package rpn;

import java.util.Stack;

public class Multiply extends Operator {
    Multiply(Stack<Item> stack){
        super(stack.pop(), stack.pop());
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
