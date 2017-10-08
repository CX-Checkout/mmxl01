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
    return calculateMultiDiscount(itemCount, 'A', 50, 200, 5, 130, 3)
        + calculateB(itemCount)
        + getSimplePrice(itemCount, 'C', 20)
        + getSimplePrice(itemCount, 'D', 15)
        + getSimplePrice(itemCount, 'E', 40)
        + calculatePriceWithOneFreeOffer(itemCount, 'F', 10, 2)
        + getSimplePrice(itemCount, 'G', 20)
        + calculateMultiDiscount(itemCount, 'H', 10, 80, 10, 45, 5)
        + getSimplePrice(itemCount, 'I', 35)
        + getSimplePrice(itemCount, 'J', 60)
        + calculateSingleDiscount(itemCount, 'K', 80, 150, 2)
        + getSimplePrice(itemCount, 'L', 90)
        + calculateM(itemCount)
        + getSimplePrice(itemCount, 'O', 10)
        + calculateSingleDiscount(itemCount, 'P', 50, 200, 5)
        + calculateSingleDiscount(itemCount, 'Q', 30, 80, 3);
        + calculateR(itemCount)
        + getSimplePrice(itemCount, 'S', 30)
        + getSimplePrice(itemCount, 'T', 20)
        + calculatePriceWithOneFreeOffer(itemCount, 'U', 40, 3)
        + calculateMultiDiscount(itemCount, 'V', 50, 130, 3, 90, 2)
        + getSimplePrice(itemCount, 'W', 20)
        + getSimplePrice(itemCount, 'X', 90)
        + getSimplePrice(itemCount, 'Y', 10)
        + getSimplePrice(itemCount, 'Z', 50);


  }


  private static int calculatePriceWithOneFreeOffer(HashMap<Character, Integer> items, char item,
      int basePrice, int unitsToQuality) {
    Integer count = items.get(item);
    if (isNullOrZero(count)) {
      return 0;
    }
    return count / (unitsToQuality + 1) * basePrice * unitsToQuality
        + count % (unitsToQuality + 1) * basePrice;

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

  private static int calculatePriceAffectedByOtherItem(HashMap<Character, Integer> items, char itemToCalculate, int basePrice, char itemWithDiscount, int unitsForDiscount) {
    
  }

  private static int calculateSingleDiscount(HashMap<Character, Integer> items, char item,
      int basePrice, int discountGroupPrice, int unitsForDiscount) {
    Integer count = items.get(item);
    if (isNullOrZero(count)) {
      return 0;
    }
    return count / unitsForDiscount * 45 + (count % unitsForDiscount) * basePrice;
  }

  private static int calculateMultiDiscount(HashMap<Character, Integer> items, char item,
      int basePrice, int bigDiscountGroupPrice, int unitsForBigDiscount,
      int smallDiscountGroupPrice, int unitsForSmallDiscount) {
    Integer count = items.get(item);
    if (isNullOrZero(count)) {
      return 0;
    }
    int price = count / unitsForBigDiscount * bigDiscountGroupPrice;
    count = count % unitsForBigDiscount;
    price += count / unitsForSmallDiscount * smallDiscountGroupPrice
        + (count % unitsForSmallDiscount) * basePrice;
    return price;
  }

  private static boolean isNullOrZero(Integer value) {
    return value == null || value == 0;
  }

  private static boolean isInvalid(char c) {
    return c < 'A' || c > 'Z';
  }
}
