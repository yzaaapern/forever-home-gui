/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
public abstract class Animal implements Level {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    public static final int NUM_OF_ANIMAL_CLASS = 5;
    private final int INC_STAT = 25; // when a stat is increased it will increase by 25 points
    private final int DEC_STAT = 5; // when a stat is decreased it will decrease by 5 points
    public static final int DEFAULT_STAT = 100; // default stat cap for happiness, hygiene, hunger, and level xp 
    private final int RESET_STAT = 20; // when the animal levels up, it's stats are set to 20

    private String name;
    private int animalFoodType, happiness, hygiene, hunger, level, levelXP, statBar, levelXPBar;
    private boolean isAdopted = false;

    /*
        OBJECT CONSTRUCTORS
     */
    public Animal(String name) {
        this.setName(name);
        this.setAnimalFoodType(0);
        this.setHappiness(0);
        this.setHygiene(0);
        this.setHunger(0);
        this.setLevel(0);
        this.setLevelXP(0);
        this.setStatBar(DEFAULT_STAT);
        this.setLevelXPBar(Level.DEFAULT_LEVELXP_CAP);
        this.setIsAdopted(false);
    }

    public Animal(String name, int happiness, int hunger, int hygiene, int levelXP, int levelXPBar, int level) {
        this.setName(name);
        this.setAnimalFoodType(0);
        this.setHappiness(happiness);
        this.setHygiene(hygiene);
        this.setHunger(hunger);
        this.setLevel(level);
        this.setLevelXP(levelXP);
        this.setStatBar(DEFAULT_STAT);
        this.setLevelXPBar(levelXPBar);
        this.setIsAdopted(false);
    }

    /*
        NAME: SET AND GET METHOD
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /*
        ANIMAL FOOD TYPE: SET AND GET METHOD
     */
    public void setAnimalFoodType(int animalType) {
        this.animalFoodType = animalType;
    }

    public int getAnimalFoodType() {
        return this.animalFoodType;
    }

    /*
        HAPPINESS: SET AND GET METHOD
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getHappiness() {
        return this.happiness;
    }

    /*
        HUNGER: SET AND GET METHOD
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;

    }

    public int getHunger() {
        return this.hunger;
    }

    /*
        HYGIENE: SET AND GET METHOD
     */
    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public int getHygiene() {
        return this.hygiene;
    }

    /*
        LEVELXP: SET AND GET METHOD
     */
    public void setLevelXP(int levelXP) {
        this.levelXP = levelXP;
    }

    public int getLevelXP() {
        return this.levelXP;
    }

    /*
        LEVEL: SET AND GET METHOD
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    /*
        STATBAR: SET AND GET METHODS
     */
    public void setStatBar(int statBar) {
        this.statBar = statBar;
    }

    public int getStatBar() {
        return this.statBar;
    }

    /*
        LEVELXPBAR: SET AND GET METHODS
     */
    public void setLevelXPBar(int levelXPBar) {
        this.levelXPBar = levelXPBar;
    }

    public int getLevelXPBar() {
        return this.levelXPBar;
    }

    /*
    I   SADOPTED : SET AND GET METHODS
     */
    public void setIsAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public boolean getIsAdopted() {
        return this.isAdopted;
    }

    /*
        OVERRIDE TOSTRING METHOD
    
        Parameters: None
        Return: String
        Description: Returns useful description of the foster pet and its stats.
    
     */
    @Override
    public String toString() {
        return ("---------------------\n"
                + "FOSTER PET STATS"
                + "\n-Foster Pet Name: " + this.name
                + "\n-Level: " + this.level + " and Level XP: " + this.levelXP + "/" + this.levelXPBar
                + "\n-Hunger: " + this.hunger + "/" + this.statBar
                + "\n-Hygiene: " + this.hygiene + "/" + this.statBar
                + "\n-Happiness: " + this.happiness + "/" + this.statBar
                + "\n---------------------\n");
    }

    public String fileToString() {
        return this.name + ":" + this.checkFosterPet(this) + ":" + this.happiness + ":" + this.hunger + ":" + this.hygiene + ":" + this.levelXP + ":" + this.levelXPBar + ":" + this.level;
    }

    /*
        METHODS
     */

 /*  incHunger Method
    
        Parameters: Food object
        Return: None
        Description: If the pet eats their hunger stat will increase,
        If the increase in hunger is greater than the cap, it will just be set to the cap (you cannot have more hunger points than the max cap)
        Otherwise, the hunger is the hunger + the increase in hunger constant.
     */
    public void incHunger(Food food) {
        int inc_hunger = this.hunger + food.getFoodValue();
        if (inc_hunger > DEFAULT_STAT) {
            this.setHunger(DEFAULT_STAT);
        } else {
            this.setHunger(inc_hunger);
        }
    }

    /*  decHunger Method
    
        Parameters: None
        Return: None
        Description: The pet's hunger stat will decrease
        If the decrease in hunger is lower than 0, it will just be set to 0 (you cannot have negative hunger points)
        Otherwise, the hunger is the hunger - decrease in hunger constant.
     */
    public void decHunger() {
        int dec_hunger = this.hunger - DEC_STAT;
        if (dec_hunger < 0) {
            this.setHunger(0);
        } else {
            this.setHunger(dec_hunger);
        }

    }

    /*  incHunger Method
    
        Parameters: None
        Return: None
        Description: Pet's happiness stat increases
        If the increase in happiness is greater than the cap, it will just be set to the cap (you cannot have more happiness points than the max cap)
        Otherwise, the happiness is the happiness + the increase in happiness constant.
     */
    public void incHappiness() {
        int inc_happiness = this.happiness + INC_STAT;
        if (inc_happiness > DEFAULT_STAT) {
            this.setHappiness(DEFAULT_STAT);
        } else {
            this.setHappiness(inc_happiness);
        }
    }

    /*  decHappiness Method
    
        Parameters: None
        Return: None
        Description: The pet's happiness stat will decrease
        If the decrease in happiness is lower than 0, it will just be set to 0 (you cannot have negative happiness points)
        Otherwise, the happiness is the happiness - decrease in happiness constant.
     */
    public void decHappiness() {
        int dec_happiness = this.happiness - DEC_STAT;
        if (dec_happiness < 0) {
            this.setHappiness(0);
        } else {
            this.setHappiness(dec_happiness);
        }
    }

    /*  incHygiene Method
    
        Parameters: None
        Return: None
        Description: Pet's hygiene stat increases (this occurs when the pet is bathed)
        If the increase in hygiene is greater than the cap, it will just be set to the cap (you cannot have more hygiene points than the max cap)
        Otherwise, the hygiene is the hygiene + the increase in hygiene constant.
     */
    public void incHygiene() {
        int inc_hygiene = this.hygiene + INC_STAT;
        if (inc_hygiene > DEFAULT_STAT) {
            this.setHygiene(DEFAULT_STAT);
        } else {
            this.setHygiene(inc_hygiene);
        }
    }

    /*  decHygiene Method
    
        Parameters: None
        Return: None
        Description: The pet's hygiene stat will decrease (normally when playing)
        If the decrease in hygiene is lower than 0, it will just be set to 0 (you cannot have negative hygiene points)
        Otherwise, the hygiene stat is the hygiene - decrease in hygiene constant.
     */
    public void decHygiene() {
        int dec_hygiene = this.hygiene - DEC_STAT;
        if (dec_hygiene < 0) {
            this.setHygiene(0);
        } else {
            this.setHygiene(dec_hygiene);
        }
    }

    /*
        OVERRIDE LEVEL METHODS
     */
 /*
        checkLevelForInteract Method
    
        Parameters: int level
        Return: Boolean value of true or false, depending if the conditional statement was met
        Description: Returns a boolean value of true or false if the foster pet's level is equal to or greater than the parameter level.
     */
    @Override
    public boolean checkLevelForInteract(int level) {
        return this.level >= level;
    }

    /*
        checkLevelForIncLevel Method
    
        Parameters: None
        Return: Boolean value of true or false, depending if the conditional statement was met
        Description: Returns a boolean value of true or false if the 
                     foster pet's stats and levelXP is equal to the stat bar and levelXP bar respectively.
     */
    @Override
    public boolean checkLevelForIncLevel() {
        return (this.happiness == this.getStatBar()
                && this.hunger == this.getStatBar()
                && this.hygiene == this.getStatBar()
                && this.levelXP == this.getLevelXPBar());
    }

    /*
        incLevel Method
    
        Parameters: None
        Return: None
        Description: If the foster pet's stats and levelXP have reached the maximum value:
                     - the foster pet's levelXP resets to 0
                     - the levelXP bar increases by the value of Level class' INC_LEVELXP_CAP
                     - the foster pet's happiness, hunger, and hygiene resets to the value of RESET_STAT
                     - the foster pet's level increases, but if the foster pet's level is greater than the value of MAX_LEVEL, then the foster pet's level is set to MAX_LEVEL
     */
    @Override
    public void incLevel() {
        if (this.checkLevelForIncLevel()) {
            this.resetLevelXP();
            this.setLevelXPBar(this.getLevelXPBar() + Level.INC_LEVELXP_CAP);
            this.setHappiness(RESET_STAT);
            this.setHunger(RESET_STAT);
            this.setHygiene(RESET_STAT);

            int inc_level = this.getLevel() + 1;
            if (inc_level > Animal.MAX_LEVEL) {
                this.setLevel(Animal.MAX_LEVEL);
            } else {
                this.setLevel(inc_level);
            }
        }
    }

    /*
        incLevelXP Method
    
        Parameters: None
        Return: None
        Description: Increases the foster pet's levelXP by the value of INC_EXP. 
                     If the incremented levelXP is greater than the levelXP Bar, 
                     it sets the foster pet's levelXP to the value of the levelXP Bar
                     and calls the incLevel function.
     */
    @Override
    public void incLevelXP() {
        int inc_levelXP = this.levelXP + Level.INC_EXP;
        if (inc_levelXP > this.levelXPBar) {
            this.setLevelXP(this.levelXPBar);
        } else {
            this.setLevelXP(inc_levelXP);
        }
    }

    /*
        decLevelXP Method
    
        Parameters: None
        Return: None
        Description: Decreases the foster pet's levelXP by the value of DEC_EXP. 
                     If the decremented levelXP is less than the 0, it sets the levelXP to 0.
     */
    @Override
    public void decLevelXP() {
        int dec_levelXP = this.levelXP - Level.DEC_EXP;
        if (dec_levelXP < 0) {
            this.setLevelXP(0);
        } else {
            this.setLevelXP(dec_levelXP);
        }
    }

    /*
        resetLevelXP Method
    
        Parameters: None
        Return: None
        Description: Sets the levelXP to 0.
     */
    @Override
    public void resetLevelXP() {
        this.setLevelXP(0);
    }

    /*
        levelUpMessage Method
    
        Parameters: None
        Return: None
        Description: Prints a level up message if a player's foster pet successfully levels up.
     */
    public void levelUpMessage() {
        System.out.println("LEVEL UP! Congratulations your foster pet, " + this.getName() + " is now LEVEL " + this.getLevel());
    }

    /*
        checkFosterPet Method
    
        Parameters: Animal object
        Return: String animalType
        Description: Checks the animal sub-class instance of the parameter foster pet and returns a respective integer number.
     */
    public String checkFosterPet(Animal fosterPet) {
        String animalType = "";

        if (fosterPet instanceof Dog) {
            animalType = "dog";
        } else if (fosterPet instanceof Cat) {
            animalType = "cat";
        } else if (fosterPet instanceof Rat) {
            animalType = "rat";
        } else if (fosterPet instanceof Parrot) {
            animalType = "parrot";
        } else {
            animalType = "chicken";
        }
        return animalType;

    }
    
    public PetData toPetData(String username)
    {
        PetData p = new PetData(this, username);
        return p;
    }
}
