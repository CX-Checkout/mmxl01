package befaster.solutions;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutTest {


  @Test
  public void checkout_computes_prices() {
    assertThat(Checkout.checkout("ABC"), equalTo());

  }

}