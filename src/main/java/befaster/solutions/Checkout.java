package befaster.solutions;

public class Checkout {

  public static Integer checkout(String skus) {
    int aItems = 0;
    int bItems = 0;
    int cItems = 0;
    int dItems = 0;
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
        default:
          return -1;
      }
    }

    return calculateA(aItems) + calculateB(bItems) + calculateC(cItems) + calculateD(dItems);
  }

  private static int calculateD(int dItems) {
    return 15 * dItems;
  }

  private static int calculateC(int cItems) {
    return 20 * cItems;
  }

  private static int calculateB(int bItems) {
    return (bItems / 2) * 45 + (bItems % 2) * 30;
  }

  private static int calculateA(int aItems) {
    return (aItems / 3) * 130 + (aItems % 3) * 50;
  }
}
