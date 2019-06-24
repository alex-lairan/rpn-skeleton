package rpn;

import org.junit.Test;
import rpn.bus.Bus;
import rpn.bus.InMemoryBus;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.MessageType;
import rpn.operator.OperatorConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class CLITestWithBus {

    private Bus bus = new InMemoryBus();
    private EndCalculConsumer ecc = new EndCalculConsumer();
    public CLITestWithBus() {
        Calculator calc = new Calculator(bus);
        bus.subscribe(MessageType.EXPRESSION.name(), new Tokenizer(bus));
        bus.subscribe(MessageType.TOKEN.name(), calc);
        bus.subscribe(MessageType.OPERATOR.name(), new OperatorConsumer(bus));
        bus.subscribe(MessageType.RESULT.name(), calc);
        bus.subscribe(MessageType.EOC.name(), ecc);
    }

    @Test
    public void should_evaluate_single_digit_constant() {
        bus.publish(new ExpressionMessage("5"));
        assertThat(ecc.getFinalResult()).isEqualTo(5);
    }

    @Test
    public void should_evaluate_single_negative_digit_constant() {
        bus.publish(new ExpressionMessage("-5"));
        assertThat(ecc.getFinalResult()).isEqualTo(-5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        bus.publish(new ExpressionMessage("17"));
        assertThat(ecc.getFinalResult()).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        bus.publish(new ExpressionMessage("17 5 +"));
        assertThat(ecc.getFinalResult()).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        bus.publish(new ExpressionMessage("2 3 5 + +"));
        assertThat(ecc.getFinalResult()).isEqualTo(10);
    }

    @Test
    public void should_evaluate_simple_subtraction() {
        bus.publish(new ExpressionMessage("17 5 -"));
        assertThat(ecc.getFinalResult()).isEqualTo(12);
    }

    @Test
    public void should_evaluate_simple_multiply() {
        bus.publish(new ExpressionMessage("5 5 *"));
        assertThat(ecc.getFinalResult()).isEqualTo(25);
    }

    @Test
    public void should_evaluate_simple_divide() {
        bus.publish(new ExpressionMessage("20 4 /"));
        assertThat(ecc.getFinalResult()).isEqualTo(5);
    }

    @Test
    public void should_evaluate_complex_formula() {
        bus.publish(new ExpressionMessage("1 1 + 5 * 2 /"));
        assertThat(ecc.getFinalResult()).isEqualTo(5);
    }

    @Test
    public void should_evaluate_simple_formula_with_negative() {
        bus.publish(new ExpressionMessage("-1 2 + 5 * 2 /"));
        assertThat(ecc.getFinalResult()).isEqualTo(2.5);
    }

    @Test
    public void should_evaluate_formula_with_floating() {
        bus.publish(new ExpressionMessage("3 5.5 8 * 7 + *"));
        assertThat(ecc.getFinalResult()).isEqualTo(153.0);
    }

    @Test(expected = NumberFormatException.class)
    public void should_do_nothing_with_empty_string() {
        bus.publish(new ExpressionMessage(""));
        ecc.getFinalResult();
    }

    @Test(expected = NumberFormatException.class)
    public void should_do_nothing_with_a_non_numeric() {
        bus.publish(new ExpressionMessage(", !"));
        ecc.getFinalResult();
    }

    @Test
    public void should_evaluate_simple_absolute() {
        bus.publish(new ExpressionMessage("-3 ABS"));
        assertThat(ecc.getFinalResult()).isEqualTo(3.0);
    }

    @Test
    public void should_evaluate_complexe_formala_with_absolute() {
        bus.publish(new ExpressionMessage("5 5 + -3 ABS -"));
        assertThat(ecc.getFinalResult()).isEqualTo(7.0);
    }

    @Test
    public void should_evaluate_complexe_formala_2_with_absolute() {
        bus.publish(new ExpressionMessage("5 5 + 85 + 2 * 250 - ABS"));
        assertThat(ecc.getFinalResult()).isEqualTo(60.0);
    }

    @Test
    public void should_evaluate_simple_times() {
        bus.publish(new ExpressionMessage("2 3 TIMES"));
        assertThat(ecc.getFinalResult()).isEqualTo(222.0);
    }

    @Test
    public void should_evaluate_complexe_formula_with_times() {
        bus.publish(new ExpressionMessage("3 2 3 + TIMES"));
        assertThat(ecc.getFinalResult()).isEqualTo(33333.0);
    }

    @Test
    public void should_evaluate_drop() {
        bus.publish(new ExpressionMessage("5 2 2 2 DROP 1 +"));
        assertThat(ecc.getFinalResult()).isEqualTo(6.0);
    }

    @Test
    public void should_evaluate_double_drop() {
        bus.publish(new ExpressionMessage("1 5 2 2 2 DROP 1 + 1 DROP"));
        assertThat(ecc.getFinalResult()).isEqualTo(1.0);
    }

    @Test
    public void should_evaluate_swap() {
        bus.publish(new ExpressionMessage("5 6 SWAP"));
        assertThat(ecc.getFinalResult()).isEqualTo(5);
    }

    @Test
    public void should_evaluate_complexe_swap() {
        bus.publish(new ExpressionMessage("5 6 SWAP -"));
        assertThat(ecc.getFinalResult()).isEqualTo(1);
    }


}
