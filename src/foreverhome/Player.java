/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Objects;

/**
 *
 * @author yzape
 */
public class Player 
{
    // Instance variables & Constants 
    public static final int INC_DABLOONS = 10; // when the player levels their pet they get 10 dabloons
    public static final int REWARD_DABLOONS = 100; // when the player's pet reaches max level they get 100 dabloons
    private String name;
    private String password;
    public Animal fosterPet;
    public boolean isPlaying;
    public boolean hasFosterPet;
    private int dabloons;
    private FoodInventory foodInventory; // each player has their own food inventory
    private InteractionList interactionList; // each player has their own interaction lists
    
    // Constructor
    public Player(String name){
        this.setName(name);
        this.setFosterPet(null);
        this.hasFosterPet = false;
        this.dabloons = 20;
        this.foodInventory = new FoodInventory();
        this.interactionList = new InteractionList();
    }
    
    // Player with a foster pet
    public Player(String name, Animal fosterPet) {
        this(name); // Call the default constructor
        this.setFosterPet(fosterPet);
        this.hasFosterPet = true; // Set hasFosterPet to true since the player has a foster pet
    }
    
    // GET & SET METHODS
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFosterPet(Animal fosterPet){
        this.fosterPet = fosterPet;
    }
    
    public Animal getFosterPet(){
        return this.fosterPet;
    }
    
    public int getDabloons() {
        return dabloons;
    }

    public void setDabloons(int dabloons) {
        this.dabloons = dabloons;
    }
    
    public FoodInventory getFoodInventory(){
        return this.foodInventory;
    }
    
    public InteractionList getInteractionList()
    {
        return this.interactionList;
    }

    // INC & DEC DABLOONS METHODS
    
    /*  incDabloons method
    
        Parameters: int for the amount to increase dabloons by
        Return: None
        Description: increments the amount of dabloons by the parameter amount
    */
    
    public void incDabloons(int amount)
    {
        int inc_dabloons = this.getDabloons() + amount;
        this.setDabloons(inc_dabloons);
    }
    
    /* decDabloons method
    
        Parameters: int for the amount to decrease dabloons buy
        Return: None
        Description: decrements the amount of dabloons by the parameter amount
    */
    
    public void decDabloons(int amount)
    {
        int dec_dabloons = this.getDabloons() - amount;
        // if the calculated dec_dabloons >= 0, set dabloons to the dec_dabloons
        if(dec_dabloons >= 0)
        {
            this.setDabloons(dec_dabloons);
        }
        // the user's dabloons should never be negative
    }
    
    public UserData toUserData()
    {
        UserData u = new UserData(this);
        return u;
    }
    
    /* Override toString method
    
        Parameters: None
        Return: String
        Description: Returns useful description of the player's stats and foster's stats
    */
    
    @Override
    public String toString(){
        String displayPet = (this.hasFosterPet == true) ? this.fosterPet.toString() : "No Pet";
        return "---------------------\n" +
               "PLAYER STATS\n" + 
               "Username: " + this.name + 
               "\nDabloons: $" + this.getDabloons() + 
               "\n" + displayPet +
               "\n" + this.foodInventory.toString();
    }
    
    
    /*  fileUserAndPetToString method
    
        Parameters: None
        Return: String
        Description: Returns user and pet string for file writing
    */
    
    public String fileUserAndPetToString(){
        String displayPet = (this.hasFosterPet ==  true) ? this.fosterPet.fileToString() : "NoPet";
        return this.name + ":" + this.dabloons 
             + "|" + displayPet;
    }
    
    /*  fileUserAndFoodInventoryToString
    
        Parameters: None
        Return: String
        Description: Returns user and their food inventory string for file writing 
    */
    public String fileUserAndFoodInventoryToString()
    {
        return this.getName() + "|" + this.getFoodInventory().fileFoodInventoryToString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
    
    /*  equals method
    
        Parameters: Object o
        Return: boolean, returns true of the objects are the same, returns false otherwise
        Description: Checks if the player is the same as the object
    */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        
        Player otherPlayer = (Player) o;
        return Objects.equals(this.name, otherPlayer.name);
    }

}
