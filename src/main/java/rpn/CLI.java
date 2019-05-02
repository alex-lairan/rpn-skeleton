package rpn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.BiFunction;

public class CLI {
    public static final void main(String[] args) {
        String expression = String.join(" ", args);

        System.out.println("About to evaluate '" + expression + "'");
        double result = evaluate(expression);
        System.out.println("> " + result);
    }

    static double evaluate(String expression) {
        // Split | Stack | Evaluate
        String[] rawTokens = expression.split(" ");

        HashMap<String, BiFunction<Item, Item, Operator>> operators = new HashMap<>();
        operators.put("+", Addition::new);
        operators.put("-", Subtraction::new);
        operators.put("*", Multiply::new);
        operators.put("/", Divide::new);

        Stack<Item> stack = new Stack<>();
        Arrays.stream(rawTokens).forEach(token -> {
            if(operators.containsKey(token)) {
                Item item1 = stack.pop();
                Item item2 = stack.pop();

                stack.push(operators.get(token).apply(item1, item2));
            } else {
                stack.push(new Number(Double.valueOf(token)));
            }
        });

        return stack.pop().process();
    }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
