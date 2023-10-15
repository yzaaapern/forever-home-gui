/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */
public class UserService 
{
    // Method to connect a pet with a user, and the food inventory with a user
    public void connectUserWithPetAndFoodInventory(UserData user, PetData pet, FoodInventoryData foodInventory) {
        // Associate the user, pet, and food inventory.
        foodInventory.setUserName(user.getUserName());
        pet.setUserName(user.getUserName());
    }
}
