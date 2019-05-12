package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.Evaluator.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_single_negative_digit_constant() {
        assertThat(evaluate("-5")).isEqualTo(-5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
    }

    @Test
    public void should_evaluate_simple_subtraction() { assertThat(evaluate("17 5 -")).isEqualTo(12); }

    @Test
    public void should_evaluate_simple_multiply() { assertThat(evaluate("5 5 *")).isEqualTo(25); }

    @Test
    public void should_evaluate_simple_divide() { assertThat(evaluate("20 4 /")).isEqualTo(5); }

    @Test
    public void should_evaluate_complex_formula() { assertThat(evaluate("1 1 + 5 * 2 /")).isEqualTo(5); }

    @Test
    public void should_evaluate_simple_formula_with_negative() { assertThat(evaluate("-1 2 + 5 * 2 /")).isEqualTo(2.5); }

    @Test
    public void should_evaluate_formula_with_floating() { assertThat(evaluate("3 5.5 8 * 7 + *")).isEqualTo(153.0); }

    @Test(expected = NumberFormatException.class)
    public void should_do_nothing_with_empty_string() {
        evaluate("");
    }

    @Test(expected = NumberFormatException.class)
    public void should_do_nothing_with_a_non_numeric() { evaluate(", !"); }

}
