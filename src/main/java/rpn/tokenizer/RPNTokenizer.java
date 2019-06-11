package rpn.tokenizer;

class RPNTokenizer implements Tokenizer {
    @Override
    public String[] tokenize(String expression) {
        return expression.split(" ");
    }
}
