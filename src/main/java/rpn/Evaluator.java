package rpn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Function;

public class Evaluator {

    private HashMap<String, Function<Stack<Item>, Operator>> operators;

    public Evaluator() {
        this.operators = new HashMap<>();

        operators.put("+", Addition::new);
        operators.put("-", Subtraction::new);
        operators.put("*", Multiply::new);
        operators.put("/", Divide::new);
        operators.put("ABS", Absolute::new);
        operators.put("TIMES", Times::new);
        operators.put("DROP", Drop::new);
        operators.put("SWAP", Swap::new);
    }

    public double evaluate(String expression) {
        String[] rawTokens = expression.split(" ");
        Stack<Item> stack = new Stack<>();
        Arrays.stream(rawTokens).forEach(token -> {
            if (operators.containsKey(token)) {
                stack.push(operators.get(token).apply(stack));
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

    @org.jetbrains.annotations.Contract(pure = true)
    private boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
