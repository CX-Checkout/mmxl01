package befaster.solutions;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SumTest {

    @Test
    public void compute_sum() {
        assertThat(Sum.sum(1, 1), equalTo(2));
    }

    @Test
    public void sum_returns_0_when_arguments_are_0() {
        assertThat(Sum.sum(0, 0), equalTo(0));
    }
}