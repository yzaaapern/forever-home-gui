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
    
    /*  buyFood method
    
        Parameters: Food object for chosen food
        Return: None
        Description: User may buy food for their pet
    */
    public void buyFood(Food food)
    {
        if(this.getDabloons() >= food.getFoodCost()) // if the player can afford the food
        {
            food.incFoodCount(); // increase the amount of that food in their food inventory
            this.decDabloons(food.getFoodCost()); // decrease the player's dabloons by the cost of that food
        }
        else // otherwise the player cannot afford the food
        {
            System.out.println("Insufficient Funds.\n"); // prints this message
        }
    }
    
    /*  feedPet method
    
        Parameters: Food object of chosen food
        Return: None
        Description: User may feed pet a chosen food
    */
    public void feedPet(Food food)
    {
        if(this.getFosterPet().getHunger() < Animal.DEFAULT_STAT) // if the pet is hungry
        {
            if(food.getFoodCount() <= 0) // if the player has none of that food
            {
                System.out.println("Insufficient supply.\n"); 
            }
            else // if the player has some of that food
            {
                this.getFosterPet().incHunger(food); // pet's hunger increases
                food.decFoodCount(); // decrease the food count by 1
                this.getFosterPet().incLevelXP(); // increase the foster pet's level xp 
                this.levelUpReward(); // potential level up reward

            }
        }
        else // if the pet already has max hunger stat, it will not eat
        {
            System.out.println(this.getFosterPet().getName() + " is already full!\n");
        }
    }
    
    /*  giveBath method
    
        Parameters: None
        Return: None
        Description: User may bathe their pet to increase pet's hygiene stat
    */
    
    public void giveBath()
    {
        if(this.getFosterPet().getHygiene() < Animal.DEFAULT_STAT) // if the pet is not fully clean
        {
            this.getFosterPet().incHygiene(); // increase their hygiene
            this.getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
        }
        else // pet is already clean, bathing has no effect on the pet or user
        {
            System.out.println(this.getFosterPet().getName() + " is already clean!\n");
        }
    }
    
    /*  interactWithPet method
    
        Parameters: Interaction object for chosen interaction   
        Return: None
        Description: User may interact with their pet as often as they like
    */
    
    public void interactWithPet(Interaction interaction)
    {
        if(!this.isInteractUnlocked(interaction)) // if the interaction is not yet unlocked by the pet 
        {
            System.out.println("You cannot do this trick yet! " + this.getFosterPet().getName() + " has yet to reach Level " + interaction.getLevelUnlocked() + ".");
        }
        else // otherwise the interaction is unlocked 
        {
            if(interaction instanceof Play) // if it is a play interaction
            {
                this.getFosterPet().incHappiness(); // increase the happiness
                this.getFosterPet().decHunger(); // decrease the hunger of the pet
                this.getFosterPet().decHygiene(); // derease the hygiene stat of the pet
            }
            else if(interaction instanceof Trick) // if it is a trick interaction
            {
                this.getFosterPet().incHappiness(); // increase the pet's happiness
            }
            else if(interaction instanceof Health) // if it is a health interaction
            {
                this.getFosterPet().incHappiness(); // increase the pet's happiness
                this.getFosterPet().incHygiene(); // increase the pet's hygiene
            }
            this.getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
        }
    }
    
    /* isInteractUnlocked method
    
        Parameters: Interaction object of chosen interaction
        Return: boolean, true if it is unlocked, false if it is locked
        Description: Checks the the foster pet has a high enough level for the interaction
    */
    
    public boolean isInteractUnlocked(Interaction interaction)
    {
        if(this.getFosterPet().getLevel() >= interaction.getLevelUnlocked()) // if the foster pet's level is greater or equal to the level of the interaction, it is unlocked
        {
            return true;
        }
        return false;
    }
    
    /*  levelUpReward
    
        Parameters: None
        Return: None
        Description: Checks if the pet is ready for a level up, increases level, gives a level up message, and gives player reward dabloons
    */
    public void levelUpReward()
    {
        if(this.getFosterPet().checkLevelForIncLevel()) // if pet is ready for level up
        {
            this.getFosterPet().incLevel(); // increase the pet's level
            this.getFosterPet().levelUpMessage(); // display level up message
            if(this.getFosterPet().getLevel() < Animal.MAX_LEVEL) // if the foster pet's leveled up level is not yet max
            {
                this.incDabloons(Player.INC_DABLOONS); // give them this amount of dabloons
            }
            else // if the foster pet's leveled up level is max
            {
                this.incDabloons(Player.REWARD_DABLOONS); // give them more this amount of dabloons
            }
            
        }
        
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
