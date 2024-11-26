package com.commerce;

import java.util.Scanner;

public class ECommerceApp {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            ProductDAO productDAO = new ProductDAO();
            CartService cartService = new CartService();

            System.out.println("Welcome to the E-Commerce App!");
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Browse Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        productDAO.getAllProducts().forEach(product ->
                                System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice())
                        );
                        break;
                    case 2:
                        System.out.print("Enter Product ID: ");
                        int productId = scanner.nextInt();
                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();
                        cartService.addToCart(1, productId, quantity); // Example userId=1
                        System.out.println("Product added to cart.");
                        break;
                    case 3:
                        cartService.viewCart(1).forEach(cartItem ->
                                System.out.println(cartItem.getName() + " - Qty: " + cartItem.getQuantity() + " - $" + cartItem.getPrice())
                        );
                        break;
                    case 4:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
    }
}
