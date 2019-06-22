package rpn.tokenizer;

import rpn.bus.Bus;
import rpn.bus.Consumer;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.Message;
import rpn.bus.messages.TokenMessage;

import java.util.stream.Stream;

public class Tokenizer implements Consumer {
    private final Bus bus;
    private final String splitRegex;

    public Tokenizer(Bus bus, String splitRegex) {
        this.bus = bus;
        this.splitRegex = splitRegex;
    }

    @Override
    public void receive(Message message) {
        ExpressionMessage eMsg = (ExpressionMessage) message;

        String expression = eMsg.expression();
        Stream.of(expression.split("\\s+"))
                .forEach(token -> bus.publish(
                        new TokenMessage(token, eMsg.id())));

        bus.publish(new EndOfToken(eMsg.id()));
    }

    @Override
    public void receive(Message message) {

    }
}
