package rpn.bus.messages;

import rpn.operator.Item;

import java.util.Stack;

public class ResultMessage implements Message {

    private Stack<Item> stack;
    private String tokenId;
    public ResultMessage(String tokenId, Stack<Item> stack) {
        this.stack = stack;
        this.tokenId = tokenId;
    }

    @Override
    public String messageType() {
        return MessageType.RESULT.name();
    }

    public Stack<Item> getStack() {
        return stack;
    }

    public String getTokenId() {
        return tokenId;
    }
}
