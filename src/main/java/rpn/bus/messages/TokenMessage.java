package rpn.bus.messages;

public class TokenMessage implements Message {
    private final String token;
    private final String expressionId;

    public TokenMessage(String expressionId, String token) {
        this.token = token;
        this.expressionId = expressionId;
    }

    @Override
    public String messageType() {
        return null;
    }

    public String getToken() {
        return this.token;
    }

    public String getExpressionId() {
        return this.expressionId;
    }
}
