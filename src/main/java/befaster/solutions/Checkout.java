package befaster.solutions;

import java.util.HashMap;

public class Checkout {

  public static Integer checkout(String skus) {
    HashMap<Character, Integer> itemCount = new HashMap<>();
    for (char c : skus.toCharArray()) {
      if (isInvalid(c)) {
        return -1;
      }
      Integer count = itemCount.get(c);
      if (count == null) {
        count = 0;
      }
      itemCount.put(c, ++count);
    }

    return calculatePrice(itemCount);
  }

  private static int calculatePrice(HashMap<Character, Integer> itemCount) {
    return calculateA(itemCount)
        + calculateB(itemCount)
        + getSimplePrice(itemCount, 'C', 20)
        + getSimplePrice(itemCount, 'D', 15)
        + getSimplePrice(itemCount, 'E', 40)
        + calculateF(itemCount)
        + getSimplePrice(itemCount, 'G', 20)
        + calculateH(itemCount)
        + getSimplePrice(itemCount, 'I', 35)
        + getSimplePrice(itemCount, 'J', 60)
        + calculateK(itemCount)
        + getSimplePrice(itemCount, 'L', 90)
        + calculateM(itemCount)
        + getSimplePrice(itemCount, 'O', 10)
        + calculateP(itemCount)

  }


  private static int calculateF(HashMap<Character, Integer> items) {
    Integer count = items.get('F');
    if (isNullOrZero(count)) {
      return 0;
    }
    return count / 3 * 20 + count % 3 * 10;
  }

  private static int getSimplePrice(HashMap<Character, Integer> items, char ch, int basePrice) {
    Integer count = items.get(ch);
    if (isNullOrZero(count)) {
      return 0;
    }
    return basePrice * count;
  }

  private static int calculateB(HashMap<Character, Integer> items) {
    Integer bItems = items.get('B');
    Integer eItems = items.get('E');
    if (isNullOrZero(bItems)) {
      return 0;
    }
    if (bItems > 0 && !isNullOrZero(eItems)) {
      bItems -= eItems / 2;
    }
    return (bItems / 2) * 45 + (bItems % 2) * 30;
  }

  private static int calculateA(HashMap<Character, Integer> items) {
    Integer count = items.get('A');
    if (isNullOrZero(count)) {
      return 0;
    }
    int price = count / 5 * 200;
    count = count % 5;
    price += count / 3 * 130 + (count % 3) * 50;
    return price;
  }

  private static boolean isNullOrZero(Integer value) {
    return value == null || value == 0;
  }

  private static boolean isInvalid(char c) {
    return c < 'A' || c > 'Z';
  }
}
