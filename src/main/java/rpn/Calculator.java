package rpn;

import rpn.bus.Bus;
import rpn.bus.Consumer;
import rpn.bus.messages.*;
import rpn.operator.*;
import rpn.operator.Number;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.logging.Logger;

public class Calculator implements Consumer {

    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getName());

    private Map<String, Stack<Item>> tokens = new HashMap<>();
    private Map<String, Function<Stack<Item>, Operator>> operators = new HashMap<>();
    private Bus bus;

    public Calculator(Bus bus) {
        this.bus = bus;

        operators.put("+", Addition::new);
        operators.put("-", Subtraction::new);
        operators.put("*", Multiply::new);
        operators.put("/", Divide::new);
        operators.put("ABS", Absolute::new);
        operators.put("TIMES", Times::new);
        operators.put("DROP", Drop::new);
        operators.put("SWAP", Swap::new);
    }

    @Override
    public void receive(Message message) {
        if (MessageType.TOKEN.name().equals(message.messageType())) {
            TokenMessage tokenMessage = (TokenMessage)message;
            if (MessageType.TOKEN.name().equals(((TokenMessage)message).getToken())){
                bus.publish(new EndOfCalculMessage(tokens.get(tokenMessage.getExpressionId()).pop().process()));
                return;
            }
            receiveTokenMessage(tokenMessage);
        }

        if (MessageType.RESULT.name().equals(message.messageType())){
            ResultMessage resultMessage = (ResultMessage) message;
            LOGGER.info(resultMessage.getStack().toString());
            tokens.put(resultMessage.getTokenId(), resultMessage.getStack());
        }


    }

    private void receiveTokenMessage(TokenMessage message) {
        TokenMessage token = message;
        LOGGER.info(token.getToken());
        if (operators.containsKey(token.getToken())){
            Operator op = (Operator) operators.get(token.getToken()).apply(tokens.get(token.getExpressionId()));
            bus.publish(new OperatorMessage(op, token.getExpressionId(),tokens.get(token.getExpressionId())));
            return;
        }

        if (tokens.containsKey(token.getExpressionId())) {
            tokens.get(token.getExpressionId()).push(new Number(Double.valueOf(token.getToken())));
        } else {
            tokens.put(token.getExpressionId(), new Stack<>());
            tokens.get(token.getExpressionId()).push(new Number(Double.valueOf(token.getToken())));
        }
    }
}
