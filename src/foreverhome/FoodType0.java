/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */
public class FoodType0 extends Food
{    
    // Constructor
    public FoodType0(String foodName, int foodValue, int foodCost, int foodCount)
    {
        super(foodName, foodValue, foodCost, foodCount);
        super.setFoodType(0);
    }
}
