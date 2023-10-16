/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */
public class ForeverHomeData 
{
    // Instance variables
    private UserData userData;
    private PetData petData;
    private FoodInventoryData foodInventoryData;
    
    // Constructor
    public ForeverHomeData(UserData userData, PetData petData, FoodInventoryData foodInventoryData)
    {
        this.setUserData(userData);
        this.setPetData(petData);
        this.setFoodInventoryData(foodInventoryData);
    }

    // METHODS
    
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userdata) {
        this.userData = userdata;
    }

    public PetData getPetData() {
        return petData;
    }

    public void setPetData(PetData petData) {
        this.petData = petData;
    }

    public FoodInventoryData getFoodInventoryData() {
        return foodInventoryData;
    }

    public void setFoodInventoryData(FoodInventoryData foodInventoryData) {
        this.foodInventoryData = foodInventoryData;
    }
}
