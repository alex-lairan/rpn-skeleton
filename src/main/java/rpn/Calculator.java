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

public class Calculator implements Consumer {

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
        String tokenId = null;
        if (MessageType.TOKEN.name().equals(message.messageType())) {
            receiveTokenMessage((TokenMessage) message);
            tokenId = ((TokenMessage) message).getExpressionId();
        }

        if (MessageType.RESULT.name().equals(message.messageType())){
            ResultMessage resultMessage = (ResultMessage) message;
            tokens.put(resultMessage.getTokenId(), resultMessage.getStack());
            tokenId = resultMessage.getTokenId();
        }

        if (!MessageType.EOT.name().equals(message.messageType()))
            receive(message);
        else
            bus.publish(new EndOfCalculMessage(tokens.get(tokenId).pop().process()));
    }

    private void receiveTokenMessage(TokenMessage message) {
        TokenMessage token = message;

        if (operators.containsKey(token.getToken())){
            Operator op = (Operator) operators.get(token.getToken()).apply(tokens.get(token.getExpressionId()));
            bus.publish(new OperatorMessage(token.getToken(), op, token.getExpressionId(),tokens.get(token.getExpressionId())));
        }

        if (tokens.containsKey(token.getExpressionId()))
            tokens.get(token.getExpressionId()).push(new Number(Double.valueOf(token.getToken())));
        else {
            tokens.put(token.getExpressionId(), new Stack<>());
        }
    }
}
