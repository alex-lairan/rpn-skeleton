package rpn.bus.messages;

import rpn.operator.Item;
import rpn.operator.Operator;

import java.util.Stack;

public class OperatorMessage implements Message {

    private String message;
    private Operator operator;
    private String tokenId;
    private Stack<Item> stack;

    public OperatorMessage(String message, Operator operator, String id, Stack<Item> stack) {
        this.message = message;
        this.operator = operator;
        this.stack = stack;
        this.tokenId = id;
    }

    @Override
    public String messageType() {
        return message;
    }

    public Stack<Item> getStack() {
        return stack;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getTokenId() {
        return tokenId;
    }
}
