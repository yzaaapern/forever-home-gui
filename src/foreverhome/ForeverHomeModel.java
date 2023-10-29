/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 * Name: Yza Pernia 
 * Student ID: 21137984
 */
import java.util.Observable;

public class ForeverHomeModel extends Observable {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    public static volatile boolean startThreads = true;
//    public Database db;
    public ForeverHomeDB db;
    public ForeverHomeData data;
    public DecrementStatsRunnable dsr;
    public Thread petDecrementStatsThread;
    public InteractionList interactionList;
    public Player player;
    public String username;
    public String chosenAnimalType;

    /*
        OBJECT CONSTRUCTOR
     */
    public ForeverHomeModel() 
    {
        this.db = new ForeverHomeDB();
        this.db.dbSetUp();
    }
    
    /*
        METHODS
    */
    
    public void setPlayer(String username)
    {
        UserData user = this.db.getDBQueries().getUserByUserName(username);
        Player player = user.toPlayer();
        this.player = player;
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    /*
        checkName method:
        Parameters: String username, String password
        Description: Checks the user's name to see if they exist in the database or not
                     Regardless of whether the player exists in the database or not,
                     a login flag is set to true when the new player is added into the database or if they previously exist
                     
                     When this flag is set to true, petFoster() is called.
    */
    public void checkName(String username, String password){
        this.username = username;
        this.data.setUserData(this.db.getDBQueries().getUserByUserName(username));
        
        if(data.loginFlag)
        {
            this.newGame();
        }
        this.setChanged();
        
        
        // change later!!
        /*
            idea is to have a data login flag and 
            if that login flag is true then it calls the newGame function
        */
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public boolean isExistingUser(String username)
    {
        UserData user = this.db.getDBQueries().getUserByUserName(username);
        if(user != null)
        {
            this.setChanged();
            this.notifyObservers(this.data);
            return true;
            
        } else {
            this.setChanged();
            this.notifyObservers(this.data);
            return false;
        }
        
    }
    
    public boolean authenticateUser(String username, String passwordAttempt)
    {
        UserData user = this.db.getDBQueries().getUserByUserName(username);
         if(user.getUserPassword().equals(passwordAttempt))
        {
            this.setChanged();
            this.notifyObservers(this.data);
            return true;
        }
        this.setChanged();
        this.notifyObservers(this.data);
        return false;
    }
    
    public void newUser(String username, String password)
    {
        this.username = username;
        this.player = new Player(username);
        this.player.setPassword(password);
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void newGame()
    {
        
    }
    
    // FOSTER
    
    public void newPetFoster(String petName) 
    {
        Animal pet;

        if(chosenAnimalType == null)
        {
            
        }
        else
        {
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
            player.setFosterPet(pet);
            this.player.hasFosterPet = true;
        }
            this.setChanged();
            this.notifyObservers(this.data);
    }

    
    
    public void petFoster(){
        /*
            grab the user, pet, and food inventory data from the data class
            and instantiate objects accordingly
        
            fosterMenu is called when player has no then and then returns back to petFosterMenu
        */
        this.player.fosterPet.setName("");
        this.notifyObservers();
    }
    /*  interactWithPet method
    
        Parameters: Interaction object for chosen interaction   
        Return: None
        Description: User may interact with their pet as often as they like
    */
    public void interactWithPet(Interaction interaction)
    {
        if(this.isInteractUnlocked(interaction)) // if the interaction is unlocked by the pet 
        {
            if(interaction instanceof Play) // if it is a play interaction
            {
                this.player.getFosterPet().incHappiness(); // increase the happiness
                this.player.getFosterPet().decHunger(); // decrease the hunger of the pet
                this.player.getFosterPet().decHygiene(); // derease the hygiene stat of the pet
            }
            else if(interaction instanceof Trick) // if it is a trick interaction
            {
                this.player.getFosterPet().incHappiness(); // increase the pet's happiness
            }
            else if(interaction instanceof Health) // if it is a health interaction
            {
                this.player.getFosterPet().incHappiness(); // increase the pet's happiness
                this.player.getFosterPet().incHygiene(); // increase the pet's hygiene
            }
            this.player.getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
            
        }
        else // otherwise the interaction is locked 
        {
            System.out.println("You cannot do this trick yet! " + this.player.getFosterPet().getName() + " has yet to reach Level " + interaction.getLevelUnlocked() + ".");
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    /* isInteractUnlocked method
    
        Parameters: Interaction object of chosen interaction
        Return: boolean, true if it is unlocked, false if it is locked
        Description: Checks the the foster pet has a high enough level for the interaction
    */
    private boolean isInteractUnlocked(Interaction interaction)
    {
        if(this.player.getFosterPet().getLevel() >= interaction.getLevelUnlocked()) // if the foster pet's level is greater or equal to the level of the interaction, it is unlocked
        {
            this.setChanged();
            this.notifyObservers(this.data);
            return true;
        }
        this.setChanged();
        this.notifyObservers(this.data);
        return false;
    }
    
    /*  buyFood method
    
        Parameters: Food object for chosen food
        Return: None
        Description: User may buy food for their pet
    */
    public void buyFood(Food food)
    {
        if(this.player.getDabloons() >= food.getFoodCost()) // if the player can afford the food
        {
            food.incFoodCount(); // increase the amount of that food in their food inventory
            this.player.decDabloons(food.getFoodCost()); // decrease the player's dabloons by the cost of that food
        }
        else // otherwise the player cannot afford the food
        {
            System.out.println("Insufficient Funds.\n"); // prints this message
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    /*  levelUpReward
    
        Parameters: None
        Return: None
        Description: Checks if the pet is ready for a level up, increases level, gives a level up message, and gives player reward dabloons
    */
    public void levelUpReward()
    {
        if(this.player.getFosterPet().checkLevelForIncLevel()) // if pet is ready for level up
        {
            this.player.getFosterPet().incLevel(); // increase the pet's level
            if(this.player.getFosterPet().getLevel() < Animal.MAX_LEVEL) // if the foster pet's leveled up level is not yet max
            {
                this.player.incDabloons(Player.INC_DABLOONS); // give them this amount of dabloons
            }
            else // if the foster pet's leveled up level is max
            {
                this.player.incDabloons(Player.REWARD_DABLOONS); // give them more this amount of dabloons
            }
            
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }

    // PET FOSTER 
    public void openBackPack()
    {
        
    }
    
    /*  feedPet method
    
        Parameters: Food object of chosen food
        Return: None
        Description: User may feed pet a chosen food
    */
    public void feedPet(Food food)
    {
        if(this.player.getFosterPet().getHunger() < Animal.DEFAULT_STAT) // if the pet is hungry
        {
            if(food.getFoodCount() <= 0) // if the player has none of that food
            {
                System.out.println("Insufficient supply.\n"); 
            }
            else // if the player has some of that food
            {
                this.player.getFosterPet().incHunger(food); // pet's hunger increases
                food.decFoodCount(); // decrease the food count by 1
                this.player.getFosterPet().incLevelXP(); // increase the foster pet's level xp 
                this.levelUpReward(); // potential level up reward

            }
        }
        else // if the pet already has max hunger stat, it will not eat
        {
            System.out.println(this.player.getFosterPet().getName() + " is already full!\n");
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    /*  bathePet method
    
        Parameters: None
        Return: None
        Description: User may bathe their pet to increase pet's hygiene stat
    */
    public void bathePet()
    {
        if(this.player.getFosterPet().getHygiene() < Animal.DEFAULT_STAT) // if the pet is not fully clean
        {
            this.player.getFosterPet().incHygiene(); // increase their hygiene
            this.player.getFosterPet().incLevelXP(); // increase the pet's xp
            this.levelUpReward(); // potential level up reward
        }
        else // pet is already clean, bathing has no effect on the pet or user
        {
            System.out.println(this.player.getFosterPet().getName() + " is already clean!\n");
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void quitGame()
    {
//        this.db.getDBOperations().quitGame(player, player.getFosterPet(), player.getFoodInventory());
        this.db.getDBManager().closeConnection();
        this.data.quitFlag = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public boolean isValid(String input)
    {
        if(input.isEmpty() || input == null)
        {
            return false;
        }
        return true;
    }
    
}
