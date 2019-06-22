package rpn;

import rpn.bus.Bus;
import rpn.bus.Consumer;
import rpn.bus.messages.EndOfCalculusMessage;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.Message;

import java.util.Stack;

public class Expression implements Consumer {
    private Bus bus;
    private Stack<Double> result;

    public Expression(Bus bus) {
        this.bus = bus;
    }

    public void send(String expression) {
        bus.publish(new ExpressionMessage());
    }

    @Override
    public void receive(Message message) {
        EndOfCalculusMessage eMsg = (EndOfCalculusMessage) message;
        result = eMsg.getResult();
    }

    public Stack<Double> getResult() {
        return result;
    }
}
