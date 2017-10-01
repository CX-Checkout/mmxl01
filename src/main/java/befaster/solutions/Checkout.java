package befaster.solutions;

public class Checkout {

  public static Integer checkout(String skus) {
    int aItems = 0;
    int bItems = 0;
    int cItems = 0;
    int dItems = 0;
    int eItems = 0;
    int fItems = 0;
    for (char c : skus.toCharArray()) {
      switch (c) {
        case 'A':
          aItems++;
          break;
        case 'B':
          bItems++;
          break;
        case 'C':
          cItems++;
          break;
        case 'D':
          dItems++;
          break;
        case 'E':
          eItems++;
          break;
        case 'F':
          fItems++;
          break;
        default:
          return -1;
      }
    }

    return calculateA(aItems) + calculateB(bItems, eItems) + calculateC(cItems) + calculateD(dItems)
        + calculateE(eItems) + calculateF(fItems);
  }

  private static int calculateF(int fItems) {
    return fItems / 3 * 20 + fItems % 3 * 10;
  }

  private static int calculateE(int eItems) {
    return 40 * eItems;
  }

  private static int calculateD(int dItems) {
    return 15 * dItems;
  }

  private static int calculateC(int cItems) {
    return 20 * cItems;
  }

  private static int calculateB(int bItems, int eItems) {
    if (bItems > 0) {
      bItems -= eItems / 2;
    }
    return (bItems / 2) * 45 + (bItems % 2) * 30;
  }

  private static int calculateA(int aItems) {
    int price = aItems / 5 * 200;
    aItems = aItems % 5;
    price += aItems / 3 * 130 + (aItems % 3) * 50;
    return price;
  }
}
