//Greg Vilac
//T00239747
//InventoryManagementSystem.java

import java.util.Scanner;
import java.text.DecimalFormat;

public class InventoryManagementSystem {
    // static inventory item array with max 50 items
    public static InventoryItem[] itemArray = new InventoryItem[50];

    // static method to display menu
    public static void displayMenu() {
        System.out.println("Inventory Management System Menu");
        System.out.println("1. Add New Inventory Item");
        System.out.println("2. Display All Inventory Items");
        System.out.println("3. Search for an Inventory Item");
        System.out.println("4. Update Inventory Item Information");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // static method to check if item is valid and code is unique
    public static boolean isValidProductEntry(int quantity, String name, double price, String code) {
        boolean foundCode = false;
        for (int i = 0; i < itemArray.length && itemArray[i] != null; i++) {
            if (code.equals(itemArray[i].getItemCode())) {
                foundCode = true;
            }

        }
        if (foundCode || price < 0 || quantity < 0 || name.equals("")) {
            return false;
        }
        return true;
    }

    // main method
    public static void main(String[] args) {
        // array counter to populate itemArray according to index
        int arrayCounter = 0;
        // create object of scanner class
        Scanner scan = new Scanner(System.in);
        // display menu
        InventoryManagementSystem.displayMenu();
        int choice = scan.nextInt();
        // Line below needed to prevent skipping on user entries.
        scan.nextLine();
        // while loop to continue the program until exited
        while (choice != 5) {

            switch (choice) {
                // add new item
                case 1: {
                    System.out.print("Enter Item Code: ");
                    String code = scan.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scan.nextInt();
                    System.out.print("Enter Price: $");
                    double price = scan.nextDouble();

                    // Check if entry is valid and code is unique
                    if (InventoryManagementSystem.isValidProductEntry(quantity, name, price, code)) {
                        // populate array, increment counter, return to menu
                        itemArray[arrayCounter] = new InventoryItem(code, name, quantity, price);
                        arrayCounter++;
                        System.out.println("Item Added Successfully.");
                        InventoryManagementSystem.displayMenu();
                        choice = scan.nextInt();
                        scan.nextLine();
                        break;

                    } else {
                        // reject addition and return to menu
                        System.out.println("That product had a problem. Returning to menu.");
                        InventoryManagementSystem.displayMenu();
                        choice = scan.nextInt();
                        scan.nextLine();
                        break;
                    }

                }
                // List inventory items
                case 2: {
                    System.out.println("Inventory Items:");
                    // loop through item array, stop if index is null or index beyond limit
                    for (int i = 0; i < itemArray.length && itemArray[i] != null; i++) {

                        // print details
                        System.out.println(itemArray[i].toString());
                        System.out.println();

                    }
                    // return to menu
                    InventoryManagementSystem.displayMenu();
                    choice = scan.nextInt();
                    scan.nextLine();
                    break;

                }
                // Search for an item
                case 3: {

                    System.out.print("Enter Item Code to Search: ");
                    // get code input from user
                    String code = scan.nextLine();
                    // initialize found item as null, update if found
                    InventoryItem item = null;
                    // loop through item array
                    for (int i = 0; i < itemArray.length && itemArray[i] != null; i++) {
                        // find matching code and update item
                        if (code.equals(itemArray[i].getItemCode())) {
                            item = itemArray[i];
                        }
                    }
                    // code not found
                    if (item == null) {
                        // reject search
                        System.out.println("That code is invalid.");
                    } else {
                        // print details of found item
                        System.out.println(item.toString());
                    }
                    // return to menu
                    InventoryManagementSystem.displayMenu();
                    choice = scan.nextInt();
                    scan.nextLine();
                    break;

                }

                // Update item
                case 4: {
                    System.out.print("Enter Item Code to update: ");
                    // get code from user
                    String code = scan.nextLine();
                    // initialize item to update as null. I could probably consolidate some code for
                    // this and case 3 by using a method that searches through the item array
                    // instead of writing the same for loop twice
                    InventoryItem itemToUpdate = null;
                    for (int i = 0; i < itemArray.length && itemArray[i] != null; i++) {
                        if (code.equals(itemArray[i].getItemCode())) {

                            itemToUpdate = itemArray[i];

                        }
                    }
                    // item with that code exists
                    if (itemToUpdate != null) {

                        System.out.println("Enter new details:");

                        System.out.print("Enter Item name: ");
                        itemToUpdate.setItemName(scan.nextLine());
                        System.out.print("Enter Quantity: ");
                        itemToUpdate.setQuantity(scan.nextInt());
                        System.out.print("Enter price: $");
                        itemToUpdate.setPrice(scan.nextDouble());
                        System.out.println("Item details updated.");

                    } else {
                        // item not found
                        System.out.println("That code is not valid");
                    }
                    // return to menu
                    InventoryManagementSystem.displayMenu();
                    choice = scan.nextInt();
                    scan.nextLine();
                    break;
                }
                // case if choice is invalid
                default: {
                    System.out.println("That entry is not valid");
                    // return to menu
                    InventoryManagementSystem.displayMenu();
                    choice = scan.nextInt();
                    scan.nextLine();
                    break;
                }

            }
        }
        // Close menu
        System.out.println("Exiting Inventory Management System");
        scan.close();
    }

}

class InventoryItem {
    // instance variables
    private String itemCode;
    private String itemName;
    private int quantity;
    private double price;

    // constructor
    public InventoryItem(String itemCode, String itemName, int quantity, double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // set format for prices
    DecimalFormat format = new DecimalFormat("#.00");

    // Getters
    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {

        return format.format(price);
    }

    // Setters
    public void setItemCode(String code) {
        itemCode = code;
    }

    public void setItemName(String name) {
        itemName = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString
    // I don't like implementing it like this but toString must return a string. I'd
    // rather it return void and just use println statements in the method body. The
    // return statement is ugly but there's no way to fix it without changing the
    // method name
    public String toString() {
        return "Item Code: " + this.itemCode + "\n" +
                "Item Name: " + this.itemName + "\n" +
                "Quantity: " + this.quantity + "\n" +
                "Price: $" + format.format(this.price);
    }
}