package rpn;

import rpn.bus.Bus;
import rpn.bus.InMemoryBus;

public class CLI {

    public static void main(String[] args) {
        String expression = String.join(" ", args);

        System.out.println("About to evaluate '" + expression + "'");

        double result = new Evaluator().evaluate(expression);
        System.out.println("> " + result);
    }

    public static void main2(String[] args) {
        Bus bus = new InMemoryBus();

        Expression expression = new Expression(bus);


//        String expression = String.join(" ", args);
        System.out.println("About to evaluate '" + expression + "'");

    }
}
