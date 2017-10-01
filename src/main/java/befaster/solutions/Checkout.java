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

    return calculateA(itemCount) + calculateB(bItems, eItems) + calculateC(cItems) + calculateD(dItems)
        + calculateE(eItems) + calculateF(fItems);
  }

  private static boolean isInvalid(char c) {
    return c < 'A' || c > 'Z';
  }

  private static int calculateF(HashMap<Character, Integer> items) {
    Integer count = items.get('F');
    if (isNullOrZero(count)) {
      return 0;
    }
    return count / 3 * 20 + count % 3 * 10;
  }

  private static int calculateE(HashMap<Character, Integer> items) {
    return getSimplePrice(items, 'E', 40);
  }

  private static int calculateD(HashMap<Character, Integer> items) {
    return getSimplePrice(items, 'D', 15);
  }

  private static int calculateC(HashMap<Character, Integer> items) {
    return getSimplePrice(items, 'C', 20);
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
    Integer aItems = items.get('A');
    if (isNullOrZero(aItems)) {
      return 0;
    }
    int price = aItems / 5 * 200;
    aItems = aItems % 5;
    price += aItems / 3 * 130 + (aItems % 3) * 50;
    return price;
  }

  private static boolean isNullOrZero(Integer value) {
    return value == null || value == 0;
  }
}
