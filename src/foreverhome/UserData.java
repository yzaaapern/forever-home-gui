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
public class UserData 
{
    // INSTANCE VARIABLES
    
    private String userName; // PK
    // Attributes
    private String userPassword; 
    private int userDabloons;
    private boolean userHasPet;
    
    // CONSTRUCTORS
    
    // New user in the database
    public UserData(Player player)
    {
        this.userName = player.getName();
        this.userPassword = player.getPassword();
        this.userDabloons = player.getDabloons();
        this.userHasPet = player.hasFosterPet;
        
        FoodInventoryData foodInventoryData = new FoodInventoryData(player.getFoodInventory(), player);
    }
    
    // 3-Paramter constructor
    public UserData(String userName, String userPassword, int userDabloons, boolean userHasPet)
    {
        this.userName = userName;
        this.setUserPassword(userPassword);
        this.setUserDabloons(userDabloons);
        this.setUserHasPet(userHasPet);
    }
    
    // GET & SET METHODS
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the userDabloons
     */
    public int getUserDabloons() {
        return userDabloons;
    }

    /**
     * @param userDabloons the userDabloons to set
     */
    public void setUserDabloons(int userDabloons) {
        this.userDabloons = userDabloons;
    }

    /**
     * @return the userHasPet
     */
    public boolean isUserHasPet() {
        return userHasPet;
    }

    /**
     * @param userHasPet the userHasPet to set
     */
    public void setUserHasPet(boolean userHasPet) {
        this.userHasPet = userHasPet;
    }
    
    
    // METHODS
    
    public Player toPlayer()
    {
        Player player = new Player(userName);
        player.setPassword(userPassword);
        player.setDabloons(userDabloons);
        player.hasFosterPet = userHasPet;

        return player;
    }
    
    // Method to connect a pet with a user, and the food inventory with a user
    public void connectUserWithPetAndFoodInventory(UserData user, PetData pet, FoodInventoryData foodInventory) 
    {
        // Associate the user, pet, and food inventory.
        foodInventory.setUserName(user.getUserName());
        pet.setUserName(user.getUserName());
    }
    
    // Override equals method
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(getUserName(), userData.getUserName());
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
    return "UserData{" +
            "userName='" + getUserName() + '\'' +
            ", userPassword='" + getUserPassword() + '\'' +
            ", userDabloons=" + getUserDabloons() +
            ", userHasPet=" + isUserHasPet() +
            '}';
    }
}
