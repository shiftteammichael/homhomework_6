/**
 * The GroceryDriver class acts as the main driver for the inventory management system
 * @author Michael Hom
 * @ID 112536073
 * @Recitation 09
 */

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class GroceryDriver {
    public static void main(String[] args){
        HashedGrocery bruh= new HashedGrocery();
       while(true){

           Scanner in=new Scanner(System.in);
           System.out.println("Menu :\n" +
                   " \n" +
                   "(L) Load item catalog    \n" +
                   "(A) Add items              \n" +
                   "(B) Process Sales      \n" +
                   "(C) Display all items\n" +
                   "(N) Move to next business day  \n" +
                   "(Q) Quit    \n" +
                   " ");
           System.out.println("Enter Option:");
           String input=in.nextLine();
           if(input.equalsIgnoreCase("L")){
               try {
                   System.out.println("Enter file to load:");
                   String file1 = in.nextLine();
                   bruh.addItemCatalog(file1);
               }
               catch(IOException ex){
                   System.out.println(ex.getMessage());
               }
               catch(ParseException ex){
                   System.out.println(ex.getMessage());
               }
           }
           if(input.equalsIgnoreCase("A")){
               try {
                   System.out.println("Enter item code:");
                   String itemCode = in.nextLine();
                   System.out.println("Enter item name:");
                   String itemName = in.nextLine();
                   System.out.println("Enter Quantity in store:");
                   int quantity = in.nextInt();
                   System.out.println("Enter Average sales per day:");
                   int avgSales = in.nextInt();
                   System.out.println("Enter price");
                   double price = in.nextDouble();
                   Item wham = new Item(itemCode, itemName, quantity, avgSales, price, 0, 0);
                   bruh.addItem(wham);
                   System.out.println(itemCode + ": " + itemName + " added to inventory.");
               }
               catch(IllegalArgumentException ex){
                   System.out.println(ex.getMessage());
               }
           }
           if(input.equalsIgnoreCase("B")){
               try{
                System.out.println("Enter filename:");
                String file=in.nextLine();
                bruh.processSales(file);
                }
               catch(IOException ex){
                   System.out.println(ex.getMessage());
               }
               catch(ParseException ex){
                   System.out.println(ex.getMessage());
               }
               catch(IllegalArgumentException ex){
                   System.out.println(ex.getMessage());
               }
           }
           if(input.equalsIgnoreCase("C")){
                System.out.println(bruh.toString());
           }
           if(input.equalsIgnoreCase("N")){
                bruh.nextBusinessDay();
           }
           if(input.equalsIgnoreCase("Q")){
                System.exit(0);
           }

       }

    }

}
