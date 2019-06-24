package rpn;

import rpn.bus.Consumer;
import rpn.bus.messages.EndOfCalculMessage;
import rpn.bus.messages.Message;

public class EndCalculConsumer implements Consumer {

    private Double finalResult;

    @Override
    public void receive(Message message) {
        EndOfCalculMessage endOfCalculMessage = (EndOfCalculMessage) message;
        finalResult = endOfCalculMessage.getFinalResult();
    }

    public Double getFinalResult() {
        return finalResult;
    }
}
