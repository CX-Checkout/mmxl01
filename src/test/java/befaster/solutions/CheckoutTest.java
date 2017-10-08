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
    assertThat(Checkout.checkout("F"), equalTo(10));
    assertThat(Checkout.checkout("G"), equalTo(20));
    assertThat(Checkout.checkout("H"), equalTo(10));
    assertThat(Checkout.checkout("I"), equalTo(35));
    assertThat(Checkout.checkout("J"), equalTo(60));
    assertThat(Checkout.checkout("K"), equalTo(70));
    assertThat(Checkout.checkout("L"), equalTo(90));
    assertThat(Checkout.checkout("M"), equalTo(15));
    assertThat(Checkout.checkout("N"), equalTo(40));
    assertThat(Checkout.checkout("O"), equalTo(10));
    assertThat(Checkout.checkout("P"), equalTo(50));
    assertThat(Checkout.checkout("Q"), equalTo(30));
    assertThat(Checkout.checkout("R"), equalTo(50));
    assertThat(Checkout.checkout("U"), equalTo(40));
    assertThat(Checkout.checkout("V"), equalTo(50));
    assertThat(Checkout.checkout("W"), equalTo(20));
    assertThat(Checkout.checkout("S"), equalTo(20));
    assertThat(Checkout.checkout("T"), equalTo(20));
    assertThat(Checkout.checkout("X"), equalTo(17));
    assertThat(Checkout.checkout("Y"), equalTo(20));
    assertThat(Checkout.checkout("Z"), equalTo(21));
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
  public void checkout_applies_simple_discounts() {
    assertThat(Checkout.checkout("ABCDABCDA"), equalTo(245));
    assertThat(Checkout.checkout("AAABBCCDD"), equalTo(245));
    assertThat(Checkout.checkout("AAAAA"), equalTo(200));
    assertThat(Checkout.checkout("AAAAAAAA"), equalTo(330));
    assertThat(Checkout.checkout("HHHHH"), equalTo(45));
    assertThat(Checkout.checkout("HHHHHHHHHH"), equalTo(80));
    assertThat(Checkout.checkout("KK"), equalTo(150));
    assertThat(Checkout.checkout("PPPPP"), equalTo(200));
    assertThat(Checkout.checkout("QQQ"), equalTo(80));
    assertThat(Checkout.checkout("VVV"), equalTo(130));
  }

  @Test
  public void checkout_applies_discount_to_B_items_when_buying_E_items() {
    assertThat(Checkout.checkout("EEBB"), equalTo(110));
  }

  @Test
  public void checkout_applies_discount_to_Q_items_when_buying_R_items() {
    assertThat(Checkout.checkout("RRRQ"), equalTo(150));
  }

  @Test
  public void checkout_applies_discount_to_M_items_when_buying_N_items() {
    assertThat(Checkout.checkout("NNNM"), equalTo(120));
    assertThat(Checkout.checkout("NNNNNNMM"), equalTo(240));
  }

  @Test
  public void checkout_returns_minus_one_for_illegal_output() {
    assertThat(Checkout.checkout("ABCDa"), equalTo(-1));
    assertThat(Checkout.checkout("z"), equalTo(-1));
  }

  @Test
  public void checkout_buying_2_F_gives_1_F_for_Free() {
    assertThat(Checkout.checkout("FFF"), equalTo(20));
    assertThat(Checkout.checkout("FF"), equalTo(20));
    assertThat(Checkout.checkout("FFFF"), equalTo(30));
    assertThat(Checkout.checkout("FFFFFF"), equalTo(40));
  }

  @Test
  public void checkout_buying_3_U_gives_1_U_for_Free() {
    assertThat(Checkout.checkout("UUU"), equalTo(120));
    assertThat(Checkout.checkout("UUUU"), equalTo(120));
    assertThat(Checkout.checkout("UUUUUUU"), equalTo(240));
    assertThat(Checkout.checkout("UUUUUUUU"), equalTo(240));
  }

  @Test
  public void checkount_applies_multi_item_group_discount() {
    assertThat(Checkout.checkout("STX"), equalTo(45));
    assertThat(Checkout.checkout("TXY"), equalTo(45));
    assertThat(Checkout.checkout("XYZ"), equalTo(45));
    assertThat(Checkout.checkout("SYZ"), equalTo(45));
    assertThat(Checkout.checkout("STXYZ"), equalTo(82));
    assertThat(Checkout.checkout("STXYZS"), equalTo(90));
    assertThat(Checkout.checkout("STXZ"), equalTo(62));

  }
}
