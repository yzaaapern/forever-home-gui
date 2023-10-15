/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */
public class UserController 
{
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public void connectUserWithPet(UserData user, PetData pet, FoodInventoryData foodInventory) 
    {
        userService.connectUserWithPetAndFoodInventory(user, pet, foodInventory);
    }
}

