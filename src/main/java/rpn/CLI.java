package rpn;


import static rpn.Evaluator.evaluate;

public class CLI {
    public static final void main(String[] args) {
        String expression = String.join(" ", args);

        System.out.println("About to evaluate '" + expression + "'");

        double result = evaluate(expression);
        System.out.println("> " + result);
    }
}
