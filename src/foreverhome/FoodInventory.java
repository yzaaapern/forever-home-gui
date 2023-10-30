/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Scanner;

/**
 *
 * @author yzape
 */
public class FoodInventory {
    
    // Instance variables & Constants
    public static final int NUM_OF_FOODS = 7;
    
    public Food foodForAll;
    public Food kibble;
    public Food cannedFood;
    public Food veggieMix;
    public Food seeds;
    public Food rainbowTreat;
    public Food water;
    
    Food[] foods = new Food[NUM_OF_FOODS];
    
    /*
        OBJECT CONSTRUCTOR
     */  
    
    public FoodInventory(){
        this.foodForAll = new FoodType0("Food for All", 10, 6, 5);
        this.foods[0] = foodForAll;
        
        this.kibble = new FoodType1("Kibble", 20, 10, 0);
        this.foods[1] = kibble;
        
        this.cannedFood = new FoodType1("Canned Food", 30, 12, 0);
        this.foods[2] = cannedFood;
        
        this.veggieMix = new FoodType2("Veggie Mix", 20, 10, 0);
        this.foods[3] = veggieMix;
        
        this.seeds = new FoodType2("Seeds", 30, 12, 0);
        this.foods[4] = seeds;
        
        this.rainbowTreat = new FoodType0("Rainbow Treat", 50, 20, 0);
        this.foods[5] = rainbowTreat;
        
        this.water = new FoodType0("Water", 2, 0, 100);
        this.foods[6] = water;
    }
    
    // METHODS
    
    /*
        getFoods Method
        
        Parameter: None
        Return: Array of Food objects
        Description: Returns the current food inventory.
    */
    
    public Food[] getFoods()
    {
        return this.foods;
    }
    
    /*  OVERRIDE toString method
    
        Parameters: None
        Return: String
        Description: Returns useful description of the food inventory.
    
     */
    
    @Override
    public String toString(){
        String foodInventoryOutput = "Your current food inventory: \n";
        int count = 1;
        
        for(Food f : foods){
            foodInventoryOutput += count + ". " + f.toStringNoCost() + "\n---------------------\n";
            count++;
        }
        return foodInventoryOutput;
    }
    
    /*  fileFoodInventoryToString method
    
        Parameters: None
        Return: String
        Description: Returns a formatted food inventory string for file writing.
    */
    
    public String fileFoodInventoryToString()
    {
        String output = "";
        for(Food f : foods)
        {
            output += f.getFoodName() + ":" + f.getFoodCount() + ",";
        }
        // Remove the trailing comma
        if (output.endsWith(",")) 
        {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
    
    public FoodInventoryData toFoodInventoryData(Player player)
    {
        FoodInventoryData f = new FoodInventoryData(this, player);
        return f;
    }
            
    
}
