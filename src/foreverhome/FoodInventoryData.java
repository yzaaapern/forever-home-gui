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
    public String foodInventoryID; // PK
    public FoodInventory foodInventory; // Attribute
    public String userName; // FK
    
    // Constructor
    public FoodInventoryData(String foodInventoryID, FoodInventory foodInventory, String userName)
    {
        this.foodInventoryID = foodInventoryID;
        this.foodInventory = foodInventory;
        this.userName = userName;
    }
    
    // METHODS
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodInventoryData other = (FoodInventoryData) obj;
        return Objects.equals(foodInventoryID, other.foodInventoryID) &&
               Objects.equals(foodInventory, other.foodInventory) &&
               Objects.equals(userName, other.userName);
    }
    
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(foodInventoryID, foodInventory, userName);
    }
    
    // toString method
    @Override
    public String toString() {
        return "FoodInventoryData{" +
               "foodInventoryID='" + foodInventoryID + '\'' +
               ", foodInventory=" + foodInventory +
               ", userName='" + userName + '\'' +
               '}';
    }
}
