package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CheckoutTest {


  @Test
  public void checkout_computes_individual_prices() {
    assertThat(Checkout.checkout("A"), equalTo(50));
    assertThat(Checkout.checkout("B"), equalTo(30));
    assertThat(Checkout.checkout("C"), equalTo(20));
    assertThat(Checkout.checkout("D"), equalTo(15));
    assertThat(Checkout.checkout("E"), equalTo(40));
  }

  @Test
  public void checkout_returns_price_with_E_items() {
    assertThat(Checkout.checkout("EE"), equalTo(80));
  }

  @Test
  public void checkout_computes_group_prices() {
    assertThat(Checkout.checkout("ABCD"), equalTo(115));
    assertThat(Checkout.checkout("DCBA"), equalTo(115));
    assertThat(Checkout.checkout("AABCD"), equalTo(165));
  }

  @Test
  public void checkout_applies_discounts() {
    assertThat(Checkout.checkout("ABCDABCDA"), equalTo(245));
    assertThat(Checkout.checkout("AAABBCCDD"), equalTo(245));
    assertThat(Checkout.checkout("AAAAA"), equalTo(200));
    assertThat(Checkout.checkout("AAAAAAAA"), equalTo(330));
  }

  @Test
  public void checkout_applies_discount_to_B_items_when_buying_E_items() {
    assertThat(Checkout.checkout("EEBB"), equalTo(110));
  }

  @Test
  public void checkout_returns_minus_one_for_illegal_output() {
    assertThat(Checkout.checkout("ABCDF"), equalTo(-1));
    assertThat(Checkout.checkout("z"), equalTo(-1));
  }
}