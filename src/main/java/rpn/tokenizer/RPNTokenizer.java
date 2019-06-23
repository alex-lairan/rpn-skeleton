package rpn.tokenizer;

class RPNTokenizer /*implements Tokenizer */{

    public String[] tokenize(String expression) {
        return expression.split("\\s+");
    }
}
