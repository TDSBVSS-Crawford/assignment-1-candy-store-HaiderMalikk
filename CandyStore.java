// import scanner to use for inputs 
import java.util.Scanner;

// create the class 
public class CandyStore {
    // main method
    public static void main(String[] args) {
        // Main method. This method should contain most of your code.
        System.out.println("WELCOME TO THE CANDY SHOP");
        // create a empty string to be filled with the users input 
        String candyChoice = "";
        // create a empty int the users purchase quantity will be added to this as ints
        int candyQty = 0;
        // the candys price per its quantity is added to this
        double candyPrice = 0.0;
        // here the subtotal starts at 0 no tax is added to this until quit
        double subtotal = 0.0;

        // this code runs the candychoices until the user enters quit
        // with this while loop the code runs at least once to start and keeps promting user until quit is entered
        while (!candyChoice.equals("quit")) {
            candyChoice = candyChoices();
            // this is ran if the user enters a valid letter other than quit 
            // it is not checked if the option is valid here
            if (!candyChoice.equals("quit")) {
                // qty is = to the users input
                candyQty = candyQuantity();
                // price is sent to be calculated in method calculate cost vith 2 vars choice and qty
                candyPrice = calculateCost(candyChoice, candyQty);
                // the price of candy no tax is added to the subtotal
                subtotal += candyPrice;
                // the reson i did not put the purchase promt here is so that if you enter quit right away
                // it would not return "the total cost of "empty" is $0"
                System.out.printf(": $%.2f%n",candyPrice);
            }
        }
        
        // if the while loop is false and you do enter quit this runs
        // this is your recipt, if you enter quit the first time it will still give you a recipt 
        System.out.println("--------------------------");
        System.out.println("Thanks for visiting! here is your receipt:");
        System.out.printf("Subtotal: $%.2f%n", subtotal);
        // tax is 13%
        double tax = 0.13 * subtotal;
        System.out.printf("Tax: $%.2f%n", tax);
        // grand total is subtotal + 13% tax
        double grandTotal = subtotal + tax;
        System.out.printf("Grand Total: $%.2f%n", grandTotal);
        // recipt end
    } // end main

    // candy choice menu
    public static String candyChoices() {
        // this method should print out all the candy choices and prompt the user to make a choice
        System.out.println("--------------------------");
        System.out.println("Please choose from the follwing options, or type \"quit\" to exit: ");
        System.out.println("a) Reese's Pieces: $2.50/0.5 kg");
        System.out.println("b) Skittles: $1.75/1 kg");
        System.out.println("c) Jubjubes: $0.50/1 kg");
        System.out.println("d) Lollipops: 1 for $0.50 or 5 for $2.00");
        System.out.println("e) Smarties: $1.50/1 kg");
        System.out.print("Select the candy you wish to purchase: ");
        // takes choice input as a string
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        return choice; //change this return statement to return the user's choice
        // after some diggin i found, 
        // since the Scanner object is created and used within the method,
        // it is not necessary to close it explicitly in this case.
        // so realisticly there are no resource leaks, same goes for the scanner in candyQuantity()
    }
    // prompt the user to select the amount of candy they want
    public static int candyQuantity() {
        System.out.print("Enter the quantity you wish to purchase: ");
        Scanner input = new Scanner(System.in);
        int qty = input.nextInt();
        return qty; // returns users quantity
        
    }

    // this method calculates the cost
    public static double calculateCost(String choice, int qty) {
        // this method should calculate the total cost of purchasing a type of candy
        // price starts at 0 and purchases are added to it 
        double price = 0.0;

        // this methods calculates cost by taking price and multiplying by quantity 
        if (choice.equals("a")) {
            price = 2.50 * (qty / 0.5);
            System.out.printf("The cost of %dKG of Resse's Pieces is", qty);
        } 
        else if (choice.equals("b")) {
            price = 1.75 * qty;
            System.out.printf("The cost of %dKG of Skittles is", qty);
        } 
        else if (choice.equals("c")) {
            price = 0.50 * qty;
            System.out.printf("The cost of %dKG of Jubjubes is", qty);
        } 
        // this block is intresting
        // 5 lollipops is 2 dollars and 1 is 0.5 
        // for every pack of 5 its 2 dollars than 0.5 for every single lollipop
        // if you have multiple packs of 5 dicount applyes for every pack of 5 than 0.5 for each after
        // EX if you have 11 lollipops is 4 dollars for the first 10 $2 per pack of 5 than $0.5 for the 1 remaining lollipop
        else if (choice.equals("d")) {
            int remainder = qty % 5; //finds remainder after qty is divided by 5
            int packsOfFive = qty / 5; //qty is divided by 5
            price = packsOfFive * 2; // price for a pack of 5 is $2. multiply so there can be more than 1 pack of 5 discount
            System.out.printf("The cost of %d Lollipops is", qty);
            // if the remaining lollipops is less than 5 greater that 0 or if you bought less that 5 lollipops
            // remember if you got less than 5 the remainder is = to the lollipops you got 
            if (remainder > 0 && remainder < 5) {
                price += remainder * 0.5; // $0.5 for every lollipop
            } 
            // if the remainder is greater than or equal to 5 the discount applies for pack of five
            else if (remainder >= 5) {
                // price += 2 charges 2 per pack of five
                // + remainder - 5 takes the remaining lollipops after packs of five and charges 0.5 each 
                price += 2 + (remainder - 5) * 0.5; 
            }
        } // Disclaimer i used yt to learn most of this part for lollipops but i promise i understand what is going on here:)
        else if (choice.equals("e")) {
            price = 1.50 * qty;
            System.out.printf("The cost of %dKG of Smarties is", qty);
        } 
        // this is sort of the checker its only for the input there is not checker for input
        else {
            System.out.print("Invalid candy selection. Price");
        }
        
        return price; // returns price 
    }
} // end class 
