import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PurchaseService {

    private static final int[] DENOMINATIONS = {10000, 20000, 50000, 100000, 200000};
    private static final Product[] PRODUCTS = {
            new Product("Coke", 10000),
            new Product("Pepsi", 10000),
            new Product("Soda", 20000)
    };
    private static final int BUDGET_LIMIT_PER_DAY = 50000;

    private int budgetSpentToday;
    private Map<Product, Integer> productCountMap;
    private double winRateNextDay;

    public PurchaseService() {
        this.budgetSpentToday = 0;
        this.productCountMap = new HashMap<>();
        Arrays.stream(PRODUCTS).forEach(product -> productCountMap.put(product, 0));
        this.winRateNextDay = 0.1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========SODA MACHINE==========");

        while (true) {
            int denominationInput = readDenomination(scanner);
            printMenu();
            int productChoice = chooseProduct(scanner, denominationInput);
            if (productChoice == -1) {
                continue;
            }

            Product selectedProduct = PRODUCTS[productChoice];
            if (denominationInput < selectedProduct.getPrice()) {
                System.out.println("Not enough money. Please insert more money.");
                continue;
            }

            boolean purchaseSuccessful = processPurchase(selectedProduct, denominationInput);
            if (purchaseSuccessful) {
                updateProductCount(selectedProduct);

                checkPromotion(selectedProduct);

                budgetSpentToday += selectedProduct.getPrice();
                if (budgetSpentToday >= BUDGET_LIMIT_PER_DAY) {
                    System.out.println("Budget limit reached for today.");
                    break; // Exit loop to simulate end of the day
                }
            }
        }

        scanner.close();

        if (budgetSpentToday < BUDGET_LIMIT_PER_DAY) {
            winRateNextDay += 0.5 * winRateNextDay;
            System.out.println("Next day's win rate increased to: " + winRateNextDay);
        }

        simulateNextDay();
    }

    private boolean processPurchase(Product selectedProduct, int denominationInput) {
        if (denominationInput < selectedProduct.getPrice()) {
            System.out.println("Not enough money. Please insert more money.");
            return false;
        } else {
            int change = denominationInput - selectedProduct.getPrice();
            System.out.println("You have purchased: " + selectedProduct.getName());
            if (change > 0) {
                System.out.println("Your change: " + change + " VND");
            }
            return true;
        }
    }

    private int readDenomination(Scanner scanner) {
        int denominationInput;
        while (true) {
            System.out.println("Please enter the denomination (10,000 | 20,000 | 50,000 | 100,000 | 200,000): ");
            denominationInput = scanner.nextInt();
            boolean validDenomination = false;

            // Check if the input denomination is valid
            for (int denomination : DENOMINATIONS) {
                if (denominationInput == denomination) {
                    validDenomination = true;
                    break;
                }
            }

            if (validDenomination) {
                break;
            } else {
                System.out.println("Invalid denomination. Please enter a valid denomination.");
            }
        }
        return denominationInput;
    }

    private void printMenu() {
        System.out.println("""
                -----------Menu-----------
                Press number to choose products
                1. Coke (10,000 VND)
                2. Pepsi (10,000 VND)
                3. Soda (20,000 VND)
                4. Cancel""");
    }

    private int chooseProduct(Scanner scanner, int denominationInput) {
        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice >= 1 && choice <= PRODUCTS.length) {
                break;
            } else if (choice == 4) {
                System.out.println("Cancel request received. Refunding " + denominationInput + " VND.");
                return -1;
            } else {
                System.out.println("Invalid choice. Please select a valid product.");
            }
        }
        return choice - 1;
    }

    private void updateProductCount(Product product) {
        int count = productCountMap.get(product);
        productCountMap.put(product, count + 1);
    }

    private void checkPromotion(Product purchasedProduct) {
        int count = productCountMap.get(purchasedProduct);
        if (count >= 3) {
            double rand = Math.random();
            if (rand <= winRateNextDay) {
                System.out.println("Congratulations! You've won a free " + purchasedProduct.getName() + "!");
            }
        }
    }

    private void simulateNextDay() {
        budgetSpentToday = 0;
        productCountMap.replaceAll((product, count) -> 0);
    }

}
