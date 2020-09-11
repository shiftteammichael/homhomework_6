/**
 * The item class contains all the information regarding an item
 * @author Michael Hom
 * @ID 112536073
 * @Recitation 09
 */


import java.io.Serializable;

public class Item implements Serializable {
    String itemCode;
    String name;
    int qtyInStore;
    int averageSalesPerDay;
    int onOrder;
    int arrivalDay;
    double price;

    /**
     * This is a setter method that sets the arrival day of an Item
     * @param arrivalDay
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * This is the default constructor of an Item
     */
    public Item() {

    }

    /**
     * This is another constructor of an Item that sets the information of an Item
     * @param itemCode
     * @param name
     * @param qtyInStore
     * @param averageSalesPerDay
     * @param price
     * @param onOrder
     * @param arrivalDay
     */
    public Item(String itemCode, String name, int qtyInStore, int averageSalesPerDay, double price,int onOrder,int arrivalDay
    ) {
        this.itemCode=itemCode;
        this.name=name;
        this.qtyInStore=qtyInStore;
        this.averageSalesPerDay=averageSalesPerDay;
        this.price=price;
        this.onOrder=onOrder;
        this.arrivalDay=arrivalDay;

    }

    /**
     * This returns a string representation of the Item
     * @return
     */
    public String toString(){
        return itemCode +" "+name+ " "+ qtyInStore+ " "+averageSalesPerDay+ " "+price+ " "+onOrder+" "+arrivalDay;
    }

    /**
     * This is a getter method that gets the price of an Item
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * This is a setter method that sets the price of an Item
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This is a getter method that gets the arrival day of an Item
     * @return
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * This is a getter method that gets how many have been ordered for restocking
     * @return onOrder
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * This is a setter method that sets how many have been ordered for restocking
     * @param onOrder
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * This is a getter method that gets the average sales per day of the item
     * @return
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * This is a setter method that sets the average sales per day of the item
     * @param averageSalesPerDay
     */
    public void setAverageSalesPerDay(int averageSalesPerDay) {
        this.averageSalesPerDay = averageSalesPerDay;
    }

    /**
     * This is a getter method that gets the quantity of the item
     * @return
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * This is a setter method that sets the quantity of the item
     * @param qtyInStore
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }

    /**
     * This is a getter method that gets the name of the item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This is a setter method that sets the name of the item
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a getter method that gets the item code of the item
     * @return
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This is a setter method that sets the item code of the item
     * @param itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
