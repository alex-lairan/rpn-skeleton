package rpn.operator;

import rpn.bus.Bus;
import rpn.bus.Consumer;
import rpn.bus.messages.Message;
import rpn.bus.messages.OperatorMessage;
import rpn.bus.messages.ResultMessage;

import java.util.Stack;

public class OperatorConsumer implements Consumer {

    private Bus bus;

    public OperatorConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        OperatorMessage operatorMessage = (OperatorMessage) message;
        Stack<Item> stack = operatorMessage.getStack();
        Double result = operatorMessage.getOperator().process();
        stack.push(new Number(result));
        bus.publish(new ResultMessage(operatorMessage.getTokenId(), stack));
    }
}
