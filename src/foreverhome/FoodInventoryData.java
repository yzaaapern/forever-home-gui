/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Objects;

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
    // 1-Parameter Constructor
    public FoodInventoryData(String foodInventoryID)
    {
        this.foodInventoryID = foodInventoryID;
    }
    
    // 3-Parameter Constructor
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
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodInventoryData other = (FoodInventoryData) obj;
        return Objects.equals(getFoodInventoryID(), other.getFoodInventoryID()) &&
               Objects.equals(getFoodInventory(), other.getFoodInventory()) &&
               Objects.equals(getUserName(), other.getUserName());
    }
    
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(getFoodInventoryID(), getFoodInventory(), getUserName());
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
