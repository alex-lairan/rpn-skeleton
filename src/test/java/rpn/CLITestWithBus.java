package rpn;

import org.junit.Test;
import rpn.bus.Bus;
import rpn.bus.InMemoryBus;
import rpn.bus.messages.ExpressionMessage;
import rpn.bus.messages.MessageType;
import rpn.operator.OperatorConsumer;
import rpn.tokenizer.Tokenizer;

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
//        bus.publish(new ExpressionMessage(String.join(" ", args)));
    }

    @Test
    public void should_evaluate_single_digit_constant() {
        bus.publish(new ExpressionMessage("5"));
        assertThat(ecc.getFinalResult()).isEqualTo(5);
    }

//    @Test
//    public void should_evaluate_single_negative_digit_constant() {
//        assertThat(evaluator.evaluate("-5")).isEqualTo(-5);
//    }
//
//    @Test
//    public void should_evaluate_multiple_digits_constant() {
//        assertThat(evaluator.evaluate("17")).isEqualTo(17);
//    }
//
//    @Test
//    public void should_evaluate_simple_addition() {
//        assertThat(evaluator.evaluate("17 5 +")).isEqualTo(22);
//    }
//
//    @Test
//    public void should_evaluate_more_complex_addition() {
//        assertThat(evaluator.evaluate("2 3 5 + +")).isEqualTo(10);
//    }
//
//    @Test
//    public void should_evaluate_simple_subtraction() { assertThat(evaluator.evaluate("17 5 -")).isEqualTo(12); }
//
//    @Test
//    public void should_evaluate_simple_multiply() { assertThat(evaluator.evaluate("5 5 *")).isEqualTo(25); }
//
//    @Test
//    public void should_evaluate_simple_divide() { assertThat(evaluator.evaluate("20 4 /")).isEqualTo(5); }
//
//    @Test
//    public void should_evaluate_complex_formula() { assertThat(evaluator.evaluate("1 1 + 5 * 2 /")).isEqualTo(5); }
//
//    @Test
//    public void should_evaluate_simple_formula_with_negative() { assertThat(evaluator.evaluate("-1 2 + 5 * 2 /")).isEqualTo(2.5); }
//
//    @Test
//    public void should_evaluate_formula_with_floating() { assertThat(evaluator.evaluate("3 5.5 8 * 7 + *")).isEqualTo(153.0); }
//
//    @Test(expected = NumberFormatException.class)
//    public void should_do_nothing_with_empty_string() {
//        evaluator.evaluate("");
//    }
//
//    @Test(expected = NumberFormatException.class)
//    public void should_do_nothing_with_a_non_numeric() { evaluator.evaluate(", !"); }
//
//    @Test
//    public void should_evaluate_simple_absolute() { assertThat(evaluator.evaluate("-3 ABS")).isEqualTo(3.0); }
//
//    @Test
//    public void should_evaluate_complexe_formala_with_absolute() { assertThat(evaluator.evaluate("5 5 + -3 ABS -")).isEqualTo(7.0); }
//
//    @Test
//    public void should_evaluate_complexe_formala_2_with_absolute() { assertThat(evaluator.evaluate("5 5 + 85 + 2 * 250 - ABS")).isEqualTo(60.0); }
//
//    @Test
//    public void should_evaluate_simple_times() { assertThat(evaluator.evaluate("2 3 TIMES")).isEqualTo(222.0); }
//
//    @Test
//    public void should_evaluate_complexe_formula_with_times() { assertThat(evaluator.evaluate("3 2 3 + TIMES")).isEqualTo(33333.0); }
//
//    @Test
//    public void should_evaluate_drop() { assertThat(evaluator.evaluate("5 2 2 2 DROP 1 +")).isEqualTo(6.0); }
//
//    @Test
//    public void should_evaluate_double_drop() { assertThat(evaluator.evaluate("1 5 2 2 2 DROP 1 + 1 DROP")).isEqualTo(1.0);}
//
//    @Test
//    public void should_evaluate_swap() { assertThat(evaluator.evaluate("5 6 SWAP")).isEqualTo(5); }
//
//    @Test
//    public void should_evaluate_complexe_swap() { assertThat(evaluator.evaluate("5 6 SWAP -")).isEqualTo(1); }


}
