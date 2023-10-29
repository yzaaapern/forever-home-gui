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
    public FoodInventoryData(FoodInventory foodInventory, Player player)
    {
        this.foodInventoryID = this.generateRandomFoodInventoryID();
        this.setFoodInventory(this.foodInventoryToDBString(player.getFoodInventory()));
        this.userName = player.getName();
    }
    
    // new FoodInventoryData in the database (doesn't have an ID yet)
    public FoodInventoryData(FoodInventory foodInventory, String username)
    {
        this.foodInventoryID = this.generateRandomFoodInventoryID();
        this.setFoodInventory(this.foodInventoryToDBString(foodInventory));
        this.userName = username;
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
        this.setFoodInventory(this.foodInventoryToDBString(foodInventory));
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
    public String getFoodInventoryID() {
        return foodInventoryID;
    }

    public String getFoodInventory() {
        return foodInventory;
    }

    public void setFoodInventory(String foodInventory) {
        this.foodInventory = foodInventory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // METHODS
    
    // Generate a random foodInventoryID using UUID
    private String generateRandomFoodInventoryID()
    {
        return UUID.randomUUID().toString();
    }
    
    // Converts FoodInventory to a readable string for the database
    public String foodInventoryToDBString(FoodInventory foodInventory)
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
    
    // Converts FoodInventoryData to foodInventory
    public FoodInventory toFoodInventory() {
        FoodInventory foodInventory = new FoodInventory();
        String[] foodInventoryData = this.getFoodInventory().split("\\^");

        if (foodInventoryData.length >= FoodInventory.NUM_OF_FOODS) {
            for (int i = 0; i < FoodInventory.NUM_OF_FOODS; i++) {
                String[] parts = foodInventoryData[i].split(":");
                if (parts.length >= 2) {
                    try {
                        int foodCount = Integer.parseInt(parts[1]);
                        foodInventory.getFoods()[i].setFoodCount(foodCount);
                    } catch (NumberFormatException e) {
                        // Handle the case where the string is not a valid integer
                        // You can log an error message or take appropriate action here.
                    }
                } else {
                    // Handle the case where the input data format is not as expected.
                }
            }
        } else {
            // Handle the case where the input data does not have enough elements.
        }

        return foodInventory;
    }

    
    // Override equals method
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodInventoryData other = (FoodInventoryData) obj;
        return Objects.equals(getUserName(), other.getUserName());
    }

    
    // Override hashCode method
    @Override
    public int hashCode() 
    {
        return Objects.hash(getUserName());
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
