package rpn;


import rpn.bus.Bus;
import rpn.bus.Consumer;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.Message;
import rpn.bus.messages.MessageType;
import rpn.bus.messages.TokenMessage;

import java.util.UUID;
import java.util.stream.Stream;

public class Tokenizer implements Consumer {
    private final Bus bus;

    public Tokenizer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        String expression = ((ExpressionMessage) message).getExperession();
        String id = UUID.randomUUID().toString();
        Stream.of(expression.split("\\s+")).forEach(token -> bus.publish(new TokenMessage(id, token)));
        bus.publish(new TokenMessage(id, MessageType.TOKEN.name()));
    }
}
