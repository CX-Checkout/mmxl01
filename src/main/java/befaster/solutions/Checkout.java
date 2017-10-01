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

        
    }
}
