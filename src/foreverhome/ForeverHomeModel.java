/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
import java.util.Observable;

public class ForeverHomeModel extends Observable {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    public ForeverHomeDB db;
    public ForeverHomeData data;
    public Player player;
    public String username;
    public String chosenAnimalType;

    /*
        OBJECT CONSTRUCTOR
     */
    public ForeverHomeModel() {
        this.db = new ForeverHomeDB();
//        this.db.dbSetUp();
    }

    /*
        METHODS
     */
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Animal getPet() {
        return this.player.fosterPet;
    }
    
    public ForeverHomeDB getDB()
    {
        return this.db;
    }
    
    public void setPlayer(String username) {
        UserData user = this.getDB().getDBQueries().getUserByUserName(username);
        Player player = user.toPlayer();
        this.username = username;
        this.player = player;
        this.setFoodInventory();
        this.notifyObserversWithData();
    }

    public void newUser(String username, String password) {
        Player player = new Player(username);
        player.setPassword(password);
        UserData userData = new UserData(player);
        FoodInventoryData foodInventoryData = new FoodInventoryData(player.getFoodInventory(), player.getName());

        this.getDB().getDBOperations().insertData(ForeverHomeDB.USER_DATA_TABLE, userData);
        this.getDB().getDBOperations().insertData(ForeverHomeDB.FOOD_INVENTORY_TABLE, foodInventoryData);
        this.setPlayer(username);
        this.setFoodInventory();
        this.notifyObserversWithData();
    }

    public void setPet() {
        PetData petData = this.getDB().getDBQueries().getPetByIsAdoptedAndUserName(player.getName(), false);
        Animal pet = petData.toAnimal();
        this.getPlayer().setFosterPet(pet);
        this.notifyObserversWithData();
    }
    
    

    public void setFoodInventory() {
        FoodInventoryData fData = this.getDB().getDBQueries().getFoodInventoryByUserName(player.getName());

        FoodInventory f = fData.toFoodInventory();
        player.setFoodInventory(f);

        this.notifyObserversWithData();
    }

    public boolean isExistingUser(String username) {
        UserData user = this.getDB().getDBQueries().getUserByUserName(username);
        if (user != null) {
            this.notifyObserversWithData();
            return true;
        }
        this.notifyObserversWithData();
        return false;
    }

    public boolean authenticateUser(String username, String passwordAttempt) {
        UserData user = this.getDB().getDBQueries().getUserByUserName(username);
        if (user.getUserPassword().equals(passwordAttempt)) {
            this.notifyObserversWithData();
            return true;
        }
        this.notifyObserversWithData();
        return false;
    }

    // FOSTER
    public void newPetFoster(String petName) {
        Animal pet;

        if (chosenAnimalType == null) {

        } else {
            // Determine the type of pet based on the chosen animal type
            if (this.chosenAnimalType.equalsIgnoreCase("dog")) {
                pet = new Dog(petName);
            } else if (this.chosenAnimalType.equalsIgnoreCase("cat")) {
                pet = new Cat(petName);
            } else if (this.chosenAnimalType.equalsIgnoreCase("rat")) {
                pet = new Rat(petName);
            } else if (this.chosenAnimalType.equalsIgnoreCase("parrot")) {
                pet = new Parrot(petName);
            } else if (this.chosenAnimalType.equalsIgnoreCase("chicken")) {
                pet = new Chicken(petName);
            } else {
                System.out.println("null");
                return;
            }

            // Set the player's fostered pet
            this.getPlayer().setFosterPet(pet);
            this.getDB().getDBOperations().insertData(ForeverHomeDB.PET_TABLE, pet.toPetData(player.getName()));
            this.getPlayer().hasFosterPet = true;
        }
        this.notifyObserversWithData();
    }
    /*  interactWithPet method
    
        Parameters: Interaction object for chosen interaction   
        Return: None
        Description: User may interact with their pet as often as they like
     */
    public void interactWithPet(Interaction interaction) {
        if (this.isInteractUnlocked(interaction)) // if the interaction is unlocked by the pet 
        {
            if (interaction instanceof Play) // if it is a play interaction
            {
                this.getPlayer().getFosterPet().incHappiness(); // increase the happiness
                this.getPlayer().getFosterPet().decHunger(); // decrease the hunger of the pet
                this.getPlayer().getFosterPet().decHygiene(); // derease the hygiene stat of the pet
            } else if (interaction instanceof Trick) // if it is a trick interaction
            {
                this.getPlayer().getFosterPet().incHappiness(); // increase the pet's happiness
            } else if (interaction instanceof Health) // if it is a health interaction
            {
                this.getPlayer().getFosterPet().incHappiness(); // increase the pet's happiness
                this.getPlayer().getFosterPet().incHygiene(); // increase the pet's hygiene
            }
            this.getPlayer().getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
            this.notifyObserversWithData();
        } else // otherwise the interaction is locked 
        {
//            System.out.println("You cannot do this trick yet! " + this.player.getFosterPet().getName() + " has yet to reach Level " + interaction.getLevelUnlocked() + ".");
            this.notifyObserversWithData();
        }
    }

    /* isInteractUnlocked method
    
        Parameters: Interaction object of chosen interaction
        Return: boolean, true if it is unlocked, false if it is locked
        Description: Checks the the foster pet has a high enough level for the interaction
     */
    public boolean isInteractUnlocked(Interaction interaction) {
        if (this.getPlayer().getFosterPet().getLevel() >= interaction.getLevelUnlocked()) // if the foster pet's level is greater or equal to the level of the interaction, it is unlocked
        {
            this.notifyObserversWithData();
            return true;
        }
        this.notifyObserversWithData();
        return false;
    }

    /*  buyFood method
    
        Parameters: Food object for chosen food
        Return: None
        Description: User may buy food for their pet
    */
    public void buyFood(Food food)
    {
        if(this.canAfford(food)) // if the player can afford the food
        {
            food.incFoodCount(); // increase the amount of that food in their food inventory
            this.getPlayer().decDabloons(food.getFoodCost()); // decrease the player's dabloons by the cost of that food
        } else // otherwise the player cannot afford the food
        {
//            System.out.println("Insufficient Funds.\n"); // prints this message
        }
        this.notifyObserversWithData();
    }
    
    public boolean canAfford(Food food)
    {
        boolean canAfford = (this.getPlayer().getDabloons() >= food.getFoodCost());
        this.notifyObserversWithData();
        return canAfford;
    }
    
    /*  levelUpReward
    
        Parameters: None
        Return: None
        Description: Checks if the pet is ready for a level up, increases level, gives a level up message, and gives player reward dabloons
     */
    public void levelUpReward() {
        if (this.getPlayer().getFosterPet().checkLevelForIncLevel()) // if pet is ready for level up
        {
            this.getPlayer().getFosterPet().incLevel(); // increase the pet's level
            if (this.getPlayer().getFosterPet().getLevel() < Animal.MAX_LEVEL) // if the foster pet's leveled up level is not yet max
            {
                this.getPlayer().incDabloons(Player.INC_DABLOONS); // give them this amount of dabloons
            } else // if the foster pet's leveled up level is max
            {
                this.getPlayer().incDabloons(Player.REWARD_DABLOONS); // give them more this amount of dabloons
            }

        }
        this.notifyObserversWithData();
    }

    /*  feedPet method
    
        Parameters: Food object of chosen food
        Return: None
        Description: User may feed pet a chosen food
     */
    public void feedPet(Food food) {
        if (!this.petFull()) // if the pet is not full 
        {
            if (food.getFoodCount() <= 0) // if the player has none of that food
            {
            } else // if the player has some of that food
            {
                this.getPlayer().getFosterPet().incHunger(food); // pet's hunger increases
                food.decFoodCount(); // decrease the food count by 1
                this.getPlayer().getFosterPet().incLevelXP(); // increase the foster pet's level xp 
                this.levelUpReward(); // potential level up reward

            }
        } else // if the pet already has max hunger stat, it will not eat
        {
        }
        this.notifyObserversWithData();
    }
    
    public boolean petFull()
    {
        boolean petFull = this.getPlayer().getFosterPet().getHunger() >= this.getPlayer().getFosterPet().getLevelXPBar();
        this.notifyObserversWithData();
        
        return petFull;
    }
    
    public boolean compatibleFood(Food food)
    {
        if(food.getFoodType() == 0 || this.getPlayer().getFosterPet().getAnimalFoodType() == food.getFoodType())
        {
            this.notifyObserversWithData();
            return true;
        }
        
        this.notifyObserversWithData();
        return false;
    }
    
    public boolean sufficientSupply(Food food)
    {
        boolean sufficientSupply = (food.getFoodCount() > 0) ? true : false;
        this.notifyObserversWithData();
        
        return sufficientSupply;
    }
    
    /*  bathePet method
    
        Parameters: None
        Return: None
        Description: User may bathe their pet to increase pet's hygiene stat
     */
    public void bathePet() {
        if (this.canBathe()) // if the pet is not fully clean
        {
            this.getPlayer().getFosterPet().incHygiene(); // increase their hygiene
            this.getPlayer().getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
        } else // pet is already clean, bathing has no effect on the pet or user
        {
//            System.out.println(this.player.getFosterPet().getName() + " is already clean!");
        }
        this.notifyObserversWithData();
    }
    
    public boolean canBathe()
    {
        boolean canBathe = this.getPlayer().getFosterPet().getHygiene() < this.getPlayer().getFosterPet().getLevelXPBar();
        this.notifyObserversWithData();
        return canBathe;
    }
    
    public void saveGame()
    {
        Player player;
        Animal pet;
        FoodInventory foodInventory;
        UserData userData = null;
        PetData petData = null;
        FoodInventoryData foodInventoryData = null;
        
        
        if(this.getPlayer() != null)
        {
            player = this.getPlayer();
            userData = player.toUserData();
            if(this.getPlayer().getFosterPet() != null)
            {
                pet = this.getPlayer().getFosterPet();
                petData = pet.toPetData(player.getName());
            }
            if(this.getPlayer().getFoodInventory() != null)
            {
                foodInventory = this.getPlayer().getFoodInventory();
                foodInventoryData = foodInventory.toFoodInventoryData(player.getName());
            }
        }
        
        this.db.getDBOperations().saveData(userData, petData, foodInventoryData);
        
        this.notifyObserversWithData();
    }

    public void quitGame() {

        this.saveGame();
        this.db.getDBManager().closeConnection();
        this.notifyObserversWithData();
    }
    
    public void resetGame()
    {
        this.player = null;
        this.username = null;
        this.chosenAnimalType = null;
    }
    
    public boolean isValid(String input)
    {
        if(input.isEmpty() || input == null)
        {
            return false;
        }
        return true;
    }
    
    private void notifyObserversWithData() {
        this.setChanged();
        this.notifyObservers(this.data);
    }
}
