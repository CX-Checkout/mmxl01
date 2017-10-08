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
    return getPriceWithMultiDiscount(itemCount, 'A', 50, 200, 5, 130, 3)
        + getPriceAffectedByOtherItemAndDiscount(itemCount, 'B', 30, 45, 2, 'E', 2)
        + getSimplePrice(itemCount, 'C', 20)
        + getSimplePrice(itemCount, 'D', 15)
        + getSimplePrice(itemCount, 'E', 40)
        + getPriceWithOneFreeOffer(itemCount, 'F', 10, 2)
        + getSimplePrice(itemCount, 'G', 20)
        + getPriceWithMultiDiscount(itemCount, 'H', 10, 80, 10, 45, 5)
        + getSimplePrice(itemCount, 'I', 35)
        + getSimplePrice(itemCount, 'J', 60)
        + getPriceWithSingleDiscount(itemCount, 'K', 70, 150, 2)
        + getSimplePrice(itemCount, 'L', 90)
        + getPriceAffectedByOtherItem(itemCount, 'M', 15, 'N', 3)
        + getSimplePrice(itemCount, 'N', 40)
        + getSimplePrice(itemCount, 'O', 10)
        + getPriceWithSingleDiscount(itemCount, 'P', 50, 200, 5)
        + getPriceAffectedByOtherItemAndDiscount(itemCount, 'Q', 30, 80, 3, 'R', 3)
        + getSimplePrice(itemCount, 'R', 50)
        + getPriceWithOneFreeOffer(itemCount, 'U', 40, 3)
        + getPriceWithMultiDiscount(itemCount, 'V', 50, 130, 3, 90, 2)
        + getSimplePrice(itemCount, 'W', 20)

        + getMultiGroupPrice(itemCount, new char[]{ 'Z', 'S', 'T', 'Y', 'X'}, new int[]{21, 20, 20, 20, 17}, 45, 3);
  }

  private static int getMultiGroupPrice(HashMap<Character, Integer> itemCount, char[] items,
      int[] basePrices, int discountGroupPrice, int unitsToQualify) {

    int[] counts = new int[items.length];
    int total = 0;
    for (int i = 0; i < items.length; i++) {
      counts[i] = safeGetCount(itemCount, items[i]);
      total += counts[i];
    }

    int itemsToDiscount = total >= unitsToQualify ? total - (total % unitsToQualify) : 0;
    int price = itemsToDiscount / unitsToQualify * discountGroupPrice;

    int currentItem = 0;
    while (itemsToDiscount > 0) {
      if (counts[currentItem] > 0) {
        int countAfterRemoval = itemsToDiscount < counts[currentItem] ? counts[currentItem] - itemsToDiscount : 0;
        itemsToDiscount -= (counts[currentItem] - countAfterRemoval);
        counts[currentItem] = countAfterRemoval;
      }
      currentItem++;
    }

    for (int i = 0; i < items.length; i++) {
      price += counts[i] * basePrices[i];
    }

    return price;
  }

  private static int getPriceWithOneFreeOffer(HashMap<Character, Integer> items, char item,
      int basePrice, int unitsToQuality) {
    Integer count = items.get(item);
    if (isNullOrZero(count)) {
      return 0;
    }
    return count / (unitsToQuality + 1) * basePrice * unitsToQuality
        + count % (unitsToQuality + 1) * basePrice;

  }

  private static int getSimplePrice(HashMap<Character, Integer> items, char item, int basePrice) {
    return basePrice * safeGetCount(items, item);
  }

  private static int getPriceAffectedByOtherItem(HashMap<Character, Integer> items,
      char itemToCalculate, int basePrice, char otherItem, int otherUnitsForDiscount) {
    return applyDiscountFromOtherUnit(items, itemToCalculate, otherItem, otherUnitsForDiscount)
        * basePrice;
  }

  private static int getPriceAffectedByOtherItemAndDiscount(HashMap<Character, Integer> items,
      char itemToCalculate, int basePrice, int discountGroupPrice, int unitsForDiscount,
      char otherItem, int otherUnitsForDiscount) {
    int count = applyDiscountFromOtherUnit(items, itemToCalculate, otherItem,
        otherUnitsForDiscount);
    return count / unitsForDiscount * discountGroupPrice + (count % unitsForDiscount) * basePrice;
  }

  private static int applyDiscountFromOtherUnit(HashMap<Character, Integer> items,
      char itemToCalculate, char otherItem, int otherUnitsForDiscount) {
    Integer itemCount = items.get(itemToCalculate);
    Integer itemWithDiscountCount = items.get(otherItem);
    if (isNullOrZero(itemCount)) {
      return 0;
    }
    if (itemCount > 0 && !isNullOrZero(itemWithDiscountCount)) {
      itemCount -= itemWithDiscountCount / otherUnitsForDiscount;
    }
    return itemCount;
  }

  private static int getPriceWithSingleDiscount(HashMap<Character, Integer> items, char item,
      int basePrice, int discountGroupPrice, int unitsForDiscount) {
    int count = safeGetCount(items, item);
    return count / unitsForDiscount * discountGroupPrice + (count % unitsForDiscount) * basePrice;
  }

  private static int getPriceWithMultiDiscount(HashMap<Character, Integer> items, char item,
      int basePrice, int bigDiscountGroupPrice, int unitsForBigDiscount,
      int smallDiscountGroupPrice, int unitsForSmallDiscount) {
    int count = safeGetCount(items, item);
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

  private static int safeGetCount(HashMap<Character, Integer> items, char item) {
    Integer count = items.get(item);
    if (isNullOrZero(count)) {
      return 0;
    }
    return count;
  }
}
