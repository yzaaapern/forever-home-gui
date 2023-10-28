/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author AnnGa
 * Name: Ann Del Rosario
 * Student ID: 21143100
 */
public class ForeverHomeData 
{
    // Instance variables
    boolean loginFlag = false;
    boolean quitFlag = false;
    private UserData userData;
    private PetData petData;
    private FoodInventoryData foodInventoryData;
    
    // Default Constructor
    public ForeverHomeData()
    {
        this.setUserData(null);
        this.setPetData(null);
        this.setFoodInventoryData(null);
    }
    
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
