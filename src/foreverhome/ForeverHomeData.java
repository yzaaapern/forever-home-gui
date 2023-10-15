/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author AnnGa
 */
public class ForeverHomeData 
{
    // Instance variables
    UserData userData;
    PetData petData;
    FoodInventoryData foodInventoryData;
    
    // Constructor
    public ForeverHomeData(UserData userData, PetData petData, FoodInventoryData foodInventoryData)
    {
        this.userData = userData;
        this.petData = petData;
        this.foodInventoryData = foodInventoryData;
    }
}
