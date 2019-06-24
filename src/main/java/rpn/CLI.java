package rpn;

import rpn.bus.Bus;
import rpn.bus.InMemoryBus;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.MessageType;
import rpn.operator.OperatorConsumer;

public class CLI {

    public static void main2(String[] args) {
        String expression = String.join(" ", args);

        System.out.println("About to evaluate '" + expression + "'");

        double result = new Evaluator().evaluate(expression);
        System.out.println("> " + result);
    }

    public static void main(String[] args) {
        Bus bus = new InMemoryBus();
        Calculator calc = new Calculator(bus);
        EndCalculConsumer ecc = new EndCalculConsumer();
        bus.subscribe(MessageType.EXPRESSION.name(), new Tokenizer(bus));
        bus.subscribe(MessageType.TOKEN.name(), calc);
        bus.subscribe(MessageType.OPERATOR.name(), new OperatorConsumer(bus));
        bus.subscribe(MessageType.RESULT.name(), calc);
        bus.subscribe(MessageType.EOC.name(), ecc);
        bus.publish(new ExpressionMessage(String.join(" ", args)));
        System.out.println(ecc.getFinalResult());
    }

}
