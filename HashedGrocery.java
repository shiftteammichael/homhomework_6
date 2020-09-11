/**
 * The HashedGrocery class contains all the methods that allow employees to perform
 * different functions on the inventory of the grocery store
 * @author Michael Hom
 * @ID 112536073
 * @Recitation 09
 */
import java.io.*;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.DecimalFormat;

public class HashedGrocery implements Serializable {
    int businessday = 1;
    Hashtable yer;

    /**
     * This is the constructor of the HashedGrocery class
     */
    public HashedGrocery() {
        yer = new Hashtable<String, Item>();
    }

    /**
     * This method adds the Item to the hash table
     * @param item
     * @throws IllegalArgumentException
     */
    public void addItem(Item item) throws IllegalArgumentException {
        if (yer.get(item.getItemCode()) != null) {
            throw new IllegalArgumentException(item.getItemCode()+": Cannot add item as item code already exists ");
        }
        yer.put(item.getItemCode(), item);
    }

    /**
     * This method changes the qtyInstore of item by the adjustQty
     * @param item
     * @param adjustByQty
     */
    public void updateItem(Item item, int adjustByQty) {
        int g = item.getQtyInStore() - adjustByQty;
        item.setQtyInStore(g);

    }

    /**
     * This method increases the businessDay by 1 and checks to see if any orders
     * have arrived. It updates the quantities of items in the store for those
     * orders which have arrived
     */
    public void nextBusinessDay() {
        System.out.println("Advancing business day...");
        businessday++;
        int n = 0;
        System.out.println("Business day " + businessday);
        boolean tf = false;
        Object[] keys = yer.keySet().toArray();
        for (int i = 0; i < yer.size(); i++) {
            Item obj = (Item) yer.get(keys[i]);
            int arrivalDayy = obj.getArrivalDay();
            String itemCodee = obj.getItemCode();
            int orderArrived = obj.getOnOrder();
            String name = obj.getName();
            int qty = obj.getQtyInStore();
            if (arrivalDayy == businessday) {
                if (n == 0) {
                    System.out.println("Orders have arrived for: ");
                }
                System.out.println(itemCodee + ": " + orderArrived + " units of " + name + ".");
                obj.setQtyInStore(orderArrived + qty);
                obj.setOnOrder(0);
                obj.setArrivalDay(0);
                n++;
                tf = true;
            }
        }
        if (!tf) {
            System.out.println("No orders have arrived");
        }
    }

    /**
     * This method adds all the items present in the text file
     * @param filename
     * @throws IOException
     * @throws ParseException
     */
    public void addItemCatalog(String filename) throws IOException, ParseException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = (JSONArray) parser.parse(isr);
        for (int i = 0; i < objs.size(); i++) {
            JSONObject obj = (JSONObject) objs.get(i);
            String val = (String) obj.get("itemCode");
            String itemName1 = (String) obj.get("itemName");
            int avgSales1 = Integer.parseInt((String) obj.get("avgSales"));
            int qtyInStore1 = Integer.parseInt((String) obj.get("qtyInStore"));
            double price1 = Double.parseDouble((String) obj.get("price"));
            int amtOnOrder = Integer.parseInt((String) obj.get("amtOnOrder"));
            if (yer.containsKey(val)) {
                System.out.println(val + " Cannot add Item as item code alrady exists");
                continue;
            } else {
                Item bruh = new Item(val, itemName1, qtyInStore1, avgSales1, price1, amtOnOrder, 0);
                yer.put(val, bruh);
                System.out.println(bruh.getItemCode() + ":" + bruh.getName() + " is added to inventory.");

            }
        }

    }

    /**
     * This is a getter method that gets the businessDay of the Item
     * @return
     */
    public int getBusinessday() {
        return businessday;
    }

    /**
     * This is a setter method that sets the businessDay of the Item
     * @param businessday
     */
    public void setBusinessday(int businessday) {
        this.businessday = businessday;
    }

    /**
     * This is a method that processes filename to see which items have been sold that day
     * @param filename
     * @throws IOException
     * @throws ParseException
     */
    public void processSales(String filename) throws IOException, ParseException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = (JSONArray) parser.parse(isr);
        for (int i = 0; i < objs.size(); i++) {
            JSONObject obj = (JSONObject) objs.get(i);
            String val = (String) obj.get("itemCode");
            int qtySold1 = Integer.parseInt((String) obj.get("qtySold"));
            if (yer.get(val) == null) {
                System.out.println(val + ": " + "Cannot buy as it is not in the Store.");
                continue;
            }

            Object aye = yer.get(val);
            int qty = ((Item) aye).getQtyInStore();
            int averageSales = ((Item) aye).getAverageSalesPerDay();
            if (qtySold1 > qty) {
                System.out.println(val + ": " + "Not enough stock for sale. Not Updated");
                continue;
            }
            updateItem((Item) aye, qtySold1);
            int updatedQty=((Item) aye).getQtyInStore();
                String n = "";
                if (updatedQty < averageSales * 3) {
                    int restock = averageSales * 2;
                    ((Item) aye).setOnOrder(restock);
                    ((Item) aye).setArrivalDay(3 + businessday);
                    n = " Order has been placed for " + "" + restock + "" + " more units";

                }
                System.out.println(val + ": " + qtySold1 + " units of " + ((Item) aye).getName() + " are sold." + n);
    }

    }

    /**
     * This method prints all items in a neatly formatted table
     * @return
     */
    public String toString(){
        String header= String.format("%-26s %19s %18s %16s %20s %20s %20s", "Itemcode" , "Name" , "Qty", "AvgSales", "Price", "OnOrder", "ArrOnBusDay");
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------------------------------------";
        String table="";
        String names[]= new String[yer.size()-1];
        Object[] keys = yer.keySet().toArray();
        for(int i=0; i<yer.size();i++){
            Item obj= (Item) yer.get(keys[i]);
            String nam= obj.getName();
            String itemCodee=obj.getItemCode();
            int qtyy=obj.getQtyInStore();
            int avgSaless=obj.getAverageSalesPerDay();
            double price1=obj.getPrice();
            int amountOnOrderr=obj.getOnOrder();
            int arrivalDayy=obj.getArrivalDay();
            table+=String.format("\n %-26s %19s %19d %16d %20.2f %20d %20d",itemCodee,nam,qtyy, avgSaless,price1,amountOnOrderr,arrivalDayy);
        }
        return header+ yuh+ table;
    }
}
