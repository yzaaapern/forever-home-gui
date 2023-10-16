/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author AnnGa
 */
public class FoodInventoryData 
{
    // INSTANCE VARIABLES
    private String foodInventoryID; // PK
    private String foodInventory; // Attribute
    private String userName; // FK
    
    // CONSTRUCTORS
    
    // new FoodInventoryData in the database (doesn't have an ID yet)
    public FoodInventoryData(FoodInventory foodInventory, String userName)
    {
        this.foodInventoryID = this.generateRandomFoodInventoryID();
        this.setFoodInventory(this.dbFoodInventoryToString(foodInventory));
        this.userName = userName;
    }
    
    // 2-Parameter Constructor: new FoodInventoryData in the database (doesn't have an ID yet)
    public FoodInventoryData(String foodInventory, String userName)
    {
        this.foodInventoryID = this.generateRandomFoodInventoryID();
        this.setFoodInventory(foodInventory);
        this.setUserName(userName);
    }
    
    // 3-Parameter Constructor: existing FoodInventoryData in the database
    public FoodInventoryData(String foodInventoryID, FoodInventory foodInventory, String userName)
    {
        this.foodInventoryID = foodInventoryID;
        this.setFoodInventory(this.dbFoodInventoryToString(foodInventory));
        this.setUserName(userName);
    }
    
    // 3-Parameter Constructor: existing FoodInventoryData in the database
    public FoodInventoryData(String foodInventoryID, String foodInventory, String userName)
    {
        this.foodInventoryID = foodInventoryID;
        this.setFoodInventory(foodInventory);
        this.setUserName(userName);
    }
    
    
    // GET & SET METHODS
    /**
     * @return the foodInventoryID
     */
    public String getFoodInventoryID() {
        return foodInventoryID;
    }

    /**
     * @return the foodInventory
     */
    public String getFoodInventory() {
        return foodInventory;
    }

    /**
     * @param foodInventory the foodInventory to set
     */
    public void setFoodInventory(String foodInventory) {
        this.foodInventory = foodInventory;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // METHODS
    
    // Generate a random foodInventoryID using UUID
    private String generateRandomFoodInventoryID()
    {
        return UUID.randomUUID().toString();
    }
    
    // Converts FoodInventory String to a readable string for the database
    public String dbFoodInventoryToString(FoodInventory foodInventory)
    {
        String output = "";
        
        for(Food f : foodInventory.getFoods())
        {
            output += f.getFoodName() + ":" + f.getFoodCount() + "^";
        }
        // Remove the trailing comma
        if (output.endsWith("^")) 
        {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
    
    // Converts dbFoodInventory to foodInventory
    public FoodInventory dbFoodInventoryToFoodInventory(String foodInventoryString)
    {
        FoodInventory foodInventory = new FoodInventory();
        for(int i = 0; i < FoodInventory.NUM_OF_FOODS; i++)
        {
            int foodCount = Integer.parseInt(foodInventoryString.split("^")[i].split(":")[0]);
            foodInventory.getFoods()[i].setFoodCount(foodCount);
        }
        return foodInventory;
    }
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodInventoryData other = (FoodInventoryData) obj;
        return Objects.equals(getFoodInventoryID(), other.getFoodInventoryID());
    }
    
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(getFoodInventoryID());
    }
    
    // toString method
    @Override
    public String toString() {
        return "FoodInventoryData{" +
               "foodInventoryID='" + getFoodInventoryID() + '\'' +
               ", foodInventory=" + getFoodInventory() +
               ", userName='" + getUserName() + '\'' +
               '}';
    }
}
