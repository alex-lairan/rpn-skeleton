package rpn.bus.messages;

public class EndOfCalculMessage implements Message {

    private Double finalResult;

    public EndOfCalculMessage(Double finalResult) {
        this.finalResult = finalResult;
    }

    @Override
    public String messageType() {
        return MessageType.EOC.name();
    }

    public Double getFinalResult() {
        return finalResult;
    }
}
