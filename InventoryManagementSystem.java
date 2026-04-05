import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryManagementSystem {
    static class Product {
        String id;
        String name;
        int stock;
        
        Product(String id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }
    }
    
    private static HashMap<String, Product> products = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Inventory Management System");
            System.out.println("1. List all products");
            System.out.println("2. Search by ID or Name");
            System.out.println("3. Purchase (Add Stock)");
            System.out.println("4. Shipping (Record Sale)");
            System.out.println("5. Balance Stock Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    searchProduct(scanner);
                    break;
                case 3:
                    purchaseProduct(scanner);
                    break;
                case 4:
                    shipProduct(scanner);
                    break;
                case 5:
                    balanceStockReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Product List:");
            for (Product product : products.values()) {
                System.out.println("ID: " + product.id + ", Name: " + product.name + ", Stock: " + product.stock);
            }
        }
    }
    
    private static void searchProduct(Scanner scanner) {
        System.out.print("Enter product ID or name to search: ");
        String searchTerm = scanner.nextLine();
        for (Product product : products.values()) {
            if (product.id.equalsIgnoreCase(searchTerm) || product.name.equalsIgnoreCase(searchTerm)) {
                System.out.println("Found: ID: " + product.id + ", Name: " + product.name + ", Stock: " + product.stock);
                return;
            }
        }
        System.out.println("Product not found.");
    }
    
    private static void purchaseProduct(Scanner scanner) {
        System.out.print("Enter product ID to purchase: ");
        String id = scanner.nextLine();
        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        Product product = products.get(id);
        if (product != null) {
            product.stock += quantity;
            System.out.println("Stock updated. New stock for " + product.name + ": " + product.stock);
        } else {
            System.out.println("Product not found. Adding new product.");
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            products.put(id, new Product(id, name, quantity));
            System.out.println("New product added: ID: " + id + ", Name: " + name + ", Stock: " + quantity);
        }
    }
    
    private static void shipProduct(Scanner scanner) {
        System.out.print("Enter product ID to ship: ");
        String id = scanner.nextLine();
        System.out.print("Enter quantity to ship: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        Product product = products.get(id);
        if (product != null) {
            if (product.stock >= quantity) {
                product.stock -= quantity;
                System.out.println("Shipped " + quantity + " of " + product.name + ". Remaining stock: " + product.stock);
            } else {
                System.out.println("Not enough stock for " + product.name + ". Current stock: " + product.stock);
            }
        } else {
            System.out.println("Product not found.");
        }
    }
    
    private static void balanceStockReport() {
        System.out.println("Balance Stock Report:");
        for (Product product : products.values()) {
            System.out.println("ID: " + product.id + ", Name: " + product.name + ", Stock: " + product.stock);
        }
    }
}