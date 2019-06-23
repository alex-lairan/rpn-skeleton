package rpn.bus.messages;

public class ExpressionMessage implements Message {

    private String experession;

    public ExpressionMessage(String experession) {
        this.experession = experession;
    }

    @Override
    public String messageType() {
        return MessageType.EXPRESSION.name();
    }

    public String getExperession() {
        return experession;
    }
}
