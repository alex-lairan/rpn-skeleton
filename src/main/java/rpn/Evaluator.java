package rpn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.BiFunction;

public class Evaluator {
    static double evaluate(String expression) {
        // Split | Stack | Evaluate
        String[] rawTokens = expression.split(" ");

        HashMap<String, BiFunction<Item, Item, Operator>> operators = new HashMap<>();
        operators.put("+", Addition::new);
        operators.put("-", Subtraction::new);
        operators.put("*", Multiply::new);
        operators.put("/", Divide::new);

        Evaluator evaluator = new Evaluator(operators);
        return evaluator.evaluate(rawTokens);
    }

    private HashMap<String, BiFunction<Item, Item, Operator>> operators;

    public Evaluator(HashMap<String, BiFunction<Item, Item, Operator>> operators) {
        this.operators = operators;
    }

    public double evaluate(String[] rawTokens) {
        Stack<Item> stack = new Stack<>();
        Arrays.stream(rawTokens).forEach(token -> {
            if (operators.containsKey(token)) {
                Item item1 = stack.pop();
                Item item2 = stack.pop();

                stack.push(operators.get(token).apply(item1, item2));
            } else {
                if (isNumeric(token)) {
                    stack.push(new Number(Double.valueOf(token)));
                } else {
                    throw new NumberFormatException("Entry must be a number or an operator");
                }
            }
        });

        return stack.pop().process();
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
