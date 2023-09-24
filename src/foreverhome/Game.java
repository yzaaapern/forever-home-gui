/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Scanner;

/**
 *
 * @author yzape
 * Name: Yza Pernia 
 * Student ID: 21137984
 */
public class Game {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    private final Player player;
    public static volatile boolean startThreads = true;
    public DecrementStatsRunnable dsr;
    public Thread petDecrementStatsThread;
    InteractionList interactionList = new InteractionList();

    /*
        OBJECT CONSTRUCTORS
     */
    public Game(Player player) {
        this.player = player;
        this.player.isPlaying = true;

//        this.startGameMessage();

        this.displayFosterMenu();
    }

    public Game(Player player, Animal fosterPet) {
        this.player = player;
        this.player.fosterPet = fosterPet;
        this.player.isPlaying = true;
        this.player.hasFosterPet = true;

//        this.startGameMessage();
        this.decStatsThread();
        if(this.player.fosterPet != null && !player.fosterPet.getIsAdopted())
        {
            this.displayPetFosterMenu();
        }
    }

    /*
        METHODS
     */
    
    /*
        displayFosterMenu Method
    
        Parameters: None
        Return: None
        Description: If the player doesn't have a pet, the player is directed to the Foster Menu.
                     They are asked to pick a pet to foster from Dog(1) to Chicken (5).
                     If the pet is instantiated, the player is directed to Pet Foster Menu.
                     If they chose to quit the game, the player is record to have No Pet.
    */
    public void displayFosterMenu() {

        // Variables
        int inputNum = 0;
        String fosterName;
        Animal a;

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        
        // Foster Menu Title
        System.out.println("##############################");
        System.out.println("          FOSTER MENU         ");
        System.out.println("##############################");
        System.out.println("Welcome " + this.player.getName() + " to Forever Home!");

        // Display user stats
        System.out.println("Current Stats: ");
        System.out.println(this.player.toString());

        // Pick a foster animal prompt
        System.out.println("Pick an animal that you would like to foster: ");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Rat");
        System.out.println("4. Parrot");
        System.out.println("5. Chicken");
        System.out.println("x - Exit the game\n");

        // User inputs a number for which animal they wish to foster
        inputNum = this.userEnterInput(Animal.NUM_OF_ANIMAL_CLASS);

        // User names their foster pet
        do {
            System.out.print("Give them a name: ");
            fosterName = scanner.next();
        } while (fosterName == "" || fosterName == " "); // if the foster name is empty spaces, repeat the prompt. We do not want an empty name

        // Foster pet is instantiated
        switch (inputNum) {
            case 1:
                System.out.println("You chose a dog!");
                a = new Dog(fosterName);
                this.player.fosterPet = a;
                break;
            case 2:
                System.out.println("You chose a cat!");
                a = new Cat(fosterName);
                this.player.fosterPet = a;
                break;
            case 3:
                System.out.println("You chose a rat!");
                a = new Rat(fosterName);
                this.player.fosterPet = a;
                break;
            case 4:
                System.out.println("You chose a parrot!");
                a = new Parrot(fosterName);
                this.player.fosterPet = a;
                break;
            case 5:
                System.out.println("You chose a chicken!");
                a = new Chicken(fosterName);
                this.player.fosterPet = a;
                break;
            default:
                break;
        }
        
        this.player.hasFosterPet = true; // player has a foster pet
        
        // Calls the thread function that decrements the player's foster pet's stats if they don't take care of it for a specified amount of time.
        this.decStatsThread();

        // Display the Pet Foster Menu
        this.displayPetFosterMenu();
    }
    
    /*
        displayPetFosterMenu Method
    
        Parameters: None
        Return: None
        Description: If the player has a pet, the player is directed to the Pet Foster Menu.
                     They are presented with a menu that helps them take care of their foster pet.
    */
    
    public void displayPetFosterMenu() {
        // Variables
        int numOfOptions = 4;
        int inputNum = 0;

        // If the pet is max level, they are ready for adoption
        if (this.player.getFosterPet().getLevel() == Animal.MAX_LEVEL) {
            System.out.println(this.player.getFosterPet().getName() + " is ready for adoption! ");
            numOfOptions = 5;
        }

        // Pet Foster Menu Title
        System.out.println("##############################");
        System.out.println("        PET FOSTER MENU       ");
        System.out.println("##############################");

        // Display user stats
        System.out.println(this.player.toString());

        // Pet Foster Menu
        System.out.println("Choose one of the following:");
        System.out.println("1. Feed " + this.player.fosterPet.getName());
        System.out.println("2. Bathe " + this.player.fosterPet.getName());
        System.out.println("3. Play or do tricks with " + this.player.fosterPet.getName());
        System.out.println("4. Buy food for " + this.player.fosterPet.getName());

        // If the pet is max level/ready to adopt, an extra menu option is presented
        if (this.player.getFosterPet().getLevel() == Animal.MAX_LEVEL) {
            // Option 5. Adoption 
            System.out.println("5. Find " + this.player.fosterPet.getName() + "'s forever home :)");
            System.out.println("x - Exit the game\n");

            // User enters a number
            inputNum = this.userEnterInput(numOfOptions);
        } else {
            // User enters a number
            System.out.println("x - Exit the game");
            inputNum = this.userEnterInput(numOfOptions);
        }

        switch (inputNum) {
            case 1:
                // User wants to feed their pet
                this.feedPetFosterMenu();
                break;
            case 2:
                // User wants to bathe their pet
                this.player.giveBath();
                System.out.println(this.player.fosterPet.toString()); // print the pet's stats
                break;
            case 3:
                // User wants to interact with the pet
                this.displayInteractMenu();
                break;
            case 4:
                // user wants to buy food for their pet
                this.buyFoodMenu(); // open buy food meny
                break;
            case 5:
                // If the pet is max level
                if (this.player.getFosterPet().getLevel() == Animal.MAX_LEVEL) {
                    // display adoption menu
                    this.adoptionMenu();
                    break;
                } else {
                    // If the player enters 5 but their pet is not max level, break.
                    break;
                }
            default:
                break;
        }

        // If the player is playing, display the Pet Foster Men
        if (this.player.isPlaying) {
            this.displayPetFosterMenu();
        }

    }
    
    /*  displayInteractMenu method
    
        Parameters: None
        Return: None
        Description: If the player pics the option to play with or do tricks with their pet, this menu shows up with interaction options to do with their pet.
    */
    
    public void displayInteractMenu() {
        // Variables
        int inputNum = 0;

        // Interact menu title
        System.out.println("##############################");
        System.out.println("         INTERACT MENU        ");
        System.out.println("##############################");
        System.out.println("Play with " + this.player.fosterPet.getName() + " or teach them some new tricks!");
        
        // Prompt
        System.out.println("Choose one of the following: \n");
        
        // for the number of interactions, print the interaction and it's description
        for (int i = 0; i < InteractionList.NUM_OF_INTERACTIONS; i++) {
            // If the interaction is unlocked, display normally
            if (this.player.isInteractUnlocked(interactionList.getInteractions()[i])) 
            {
                System.out.println((i + 1) + ". " + this.player.getFosterPet().getName() + " " + interactionList.getInteractions()[i].getInteractionDesc());
            } 
            // Otherwise, display with (only available at _ level)
            else 
            {
                System.out.println((i + 1) + ". " + this.player.getFosterPet().getName() + " " + interactionList.getInteractions()[i].getInteractionDesc() + " (only available if your pet foster is level " + interactionList.getInteractions()[i].getLevelUnlocked() + "+)");
            }
        }
        
        // User has an option to go back to pet foster menu
        System.out.println((InteractionList.NUM_OF_INTERACTIONS + 1) + ". Go back to Pet Foster Menu");

        // User may exit game
        System.out.println("x - Exit the game\n");

        // valid input numbers are NUM_OF_INTERACTIONS + 1 (+1 due to the extra option to go back to pet foster menu
        inputNum = this.userEnterInput(InteractionList.NUM_OF_INTERACTIONS + 1);

        // The player interacts with their pet with their chosen interaction (if they are still playing)
        if (this.player.isPlaying) 
        {
            for (int i = 0; i <= InteractionList.NUM_OF_INTERACTIONS + 1; i++) 
            {
                if (inputNum == (i + 1)) 
                {
                    this.player.interactWithPet(interactionList.getInteractions()[i]);
                } 
                else if (inputNum == (InteractionList.NUM_OF_INTERACTIONS + 1)) 
                {
                    System.out.println("Returning back to Pet Foster Menu.");
                    this.displayPetFosterMenu();
                }
            }
        }

    }

    /*
        FOOD MENUS
     */
    
    /*  buyFoodMenu method
    
        Parameters: None
        Return: None
        Description: If the player wants to buy food for their pet, compatible food types will show. They may purchase if they have enough dabloons.
    */
    public void buyFoodMenu() {
        // Variables
        String input = "";
        Food chosenFood = null;
        int inputNum = 0;
        Scanner scanner = new Scanner(System.in);
        
        // Buy food menu title
        System.out.println("##############################");
        System.out.println("         BUY FOOD MENU        ");
        System.out.println("##############################");
        
        // Food store prompt
        System.out.println("Get more foods to feed your foster pet!");
        System.out.println("Purchasing one of these foods increases your chosen food's quantity by 5! Isn't that nice?");

        // Displaying store for valid food types of the foster pet
        System.out.println(this.displayValidFood(this.player.getFosterPet().getAnimalFoodType()));
        System.out.println((this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1) + ". Go back to Pet Foster Menu");
        System.out.println("x -  Exit the game\n");

        // User's input number
        inputNum = this.userEnterInput(this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1);

        // If the player is still playing/has not pressed x to exit the game
        if (this.player.isPlaying) 
        {
            // User may return back to pet foster menu
            if (inputNum == (this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1)) 
            {
                System.out.println("Returning back to Pet Foster Menu.");
                this.displayPetFosterMenu();
                
            } 
            else 
            {
                // Player is getting from list of viable foods
                chosenFood = this.getValidFood(this.player.getFosterPet().getAnimalFoodType(), inputNum);

                // Player buys pet food
                this.player.buyFood(chosenFood);
            }
        }
    }

    /*   feedPetFosterMenu method
    
        Parameters: None
        Return: None
        Description: If the player wants to feed their foster, they may do so with a list of compatible foods
    */
    
    public void feedPetFosterMenu() {
        // Variables
        String input = "";
        Food chosenFood = null;
        int inputNum = 0;
        Scanner scanner = new Scanner(System.in);

        // Feed pet foster title
        System.out.println("##############################");
        System.out.println("     FEED PET FOSTER MENU     ");
        System.out.println("##############################");
        System.out.println("Feed your foster pet something tasty! \n");

        // Display player's stock of valid food types of their foster pet
        System.out.println(this.displayValidFood(this.player.getFosterPet().getAnimalFoodType()));
        System.out.println((this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1) + ". Go back to Pet Foster Menu");
        System.out.println("x -  Exit the game\n");

        // User's input number
        inputNum = this.userEnterInput(this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1);

        // If the player is still playing/has not pressed x to exit the game
        if (this.player.isPlaying) {
            if (inputNum == (this.getValidFoodRange(this.player.getFosterPet().getAnimalFoodType()) + 1)) {
                System.out.println("Returning back to Pet Foster Menu.");
                this.displayPetFosterMenu();
            } else {
                // Player feeding pet from list of viable foods
                chosenFood = this.getValidFood(this.player.getFosterPet().getAnimalFoodType(), inputNum);

                // Player feeds pet
                this.player.feedPet(chosenFood);
            }
        }
    }

    /*
        FOOD VALIDITY METHODS
    */
    
    /* displayValidFood method
    
        Parameters: integer for the animal's prefered foodtype
        Return: String display
        Description: Must be invoked with a food type, displays a list of compatible foods.
     */
    
    public String displayValidFood(int foodType) {
        // Variables
        String output = "";
        int count = 1;

        for (int i = 0; i < this.player.getFoodInventory().getFoods().length; i++) // for loop for the length of the foods array
        {
            if (this.player.getFoodInventory().getFoods()[i].getFoodType() == 0 || this.player.getFoodInventory().getFoods()[i].getFoodType() == foodType) // if the food is compatible
            {
                output += count + ". " + this.player.getFoodInventory().getFoods()[i]; // output adds the count and the food description
                count++; // increments count for viable foods
            }
        }
        return output; // return output
    }

    /* getValidFood method
    
        Parameters: integer for food type, integer for the user's input number
        Return: A Food object 
        Description: Must be invoked with a food type and user's input number. Returns chosenFood
     */
    
    public Food getValidFood(int foodType, int inputNum) {
        int count = 1;
        Food chosenFood = null;

        for (int i = 0; i < this.player.getFoodInventory().getFoods().length; i++) {
            if (this.player.getFoodInventory().getFoods()[i].getFoodType() == 0 || this.player.getFoodInventory().getFoods()[i].getFoodType() == foodType) // if the food is a valid type
            {
                if (inputNum == count) // if the user's inputNum is equal to the count of viable foods
                {
                    chosenFood = this.player.getFoodInventory().getFoods()[i]; // the chosen food is that food
                }
                count++; // increments count for viable foods
            }
        }
        return chosenFood;
    }

    /*  getValidFoodRange method
    
        Parameters: int foodType
        Return: int of range of compatible foods
        Description: Invoked with a foodType integers, returns the length of the array of appropriate foods.
    */
    
    public int getValidFoodRange(int foodType) {
        int validFoodRange = 0;
        for (int i = 0; i < this.player.getFoodInventory().getFoods().length; i++) {
            if (this.player.getFoodInventory().getFoods()[i].getFoodType() == 0 || this.player.getFoodInventory().getFoods()[i].getFoodType() == foodType) {
                validFoodRange++;
            }
        }
        return validFoodRange;
    }

    /*  startGameMessage method
    
        Parameters: None
        Return: None
        Description: The game starts with this text
    */
    public void startGameMessage() {
        String[] startGameMessages = {
            "##############################\n      LAUNCHING THE GAME      \n##############################",
            "3...",
            "2...",
            "1...",
            "##############################\n         GAME STARTED         \n##############################\n",
            "---------------------\n"
        };

        for (String s : startGameMessages) {
            System.out.println(s);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    /*  endGameMessage method
    
        Parameters: None
        Return: None
        Description: The game ends with this text
    */
    public void endGameMessage() {
        String[] endGameMessages = {
            "---------------------",
            "Preparing the game for shutdown!",
            "Please do not close or forcefully terminate the program.",
            "This will take a while...",
            "Taking you to the End Game Portal...",
            "##############################\n       ENDING THE GAME        \n##############################",
            "Your game statistics will be displayed in the following: ",
            "(P.S. Take your time reading them! It's quite long...)",
            
            // Display user stats
            this.player.toString(),
            "Don't worry about losing any of your data! All of your progress is stored within a thorough file system.",
            "That means when you log onto the game next time, you can simply enter your username as it must be unique for every user. ",
            "##############################\n          GAME ENDED        \n##############################",
            "\nThank you, " + this.player.getName() + ", for playing Forever Home. We hope to see you again next time. :)"
        };

        for (String s : endGameMessages) {
            System.out.println(s);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*  decStatsThread method
    
        Parameters: None
        Return: None
        Description: Decrements the pet's stats overtime to simulate real life.
    */
    private void decStatsThread() {
        if (this.startThreads) {
            this.dsr = new DecrementStatsRunnable(this.player);
            this.petDecrementStatsThread = new Thread(this.dsr);
            this.petDecrementStatsThread.start();
        }
    }

    /*  adoptionMenu method
    
        Parameters: None
        Return: None
        Description: If the animal is max level and the player wants to put them up for adoption, this menu will show
    */

    public void adoptionMenu() {
        // Variables
        int inputNum = 0;
        int numOfOptions = 2;
        
        // Prompt
        System.out.println("Do you want to keep " + this.player.getFosterPet().getName() + " for a while longer? [1] or put "
                + this.player.getFosterPet().getName() + " up for adoption [2] or press x to exit game. ");
        
        // User input
        inputNum = this.userEnterInput(numOfOptions);
        
        // User wants to keep their pet around a bit longer
        if (inputNum == 1) 
        {
            System.out.println("That's all good");
        } 
        // User puts their foster pet up for adoption
        else if (inputNum == 2) 
        {
            System.out.println("Thank you for fostering " + this.player.getFosterPet().getName() + ",  they have found their forever home :)");
            this.startThreads = false;
            this.player.fosterPet.setIsAdopted(true);
            this.player.fosterPet = null;
            this.player.hasFosterPet = false;
            this.player.isPlaying = false;
            this.endGameMessage();
        }
    }

    /*
        USER INPUT & VALIDITY METHODS
     */
    
    /*  userEnterInput method
    
    Parameters: int for range of input
    Return: int of valid input
    Description: Forces user to enter a valid input.
    */
    public int userEnterInput(int inputRange) {
        // Variables
        String input = "";
        int inputNum = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            // Prompt
            System.out.print("Your choice: ");
            input = scanner.nextLine(); // input is the user's next input
            
            // User wants to exit game
            if (input.equalsIgnoreCase("x")) {
                System.out.println("You have chosen to quit the game!");
                this.player.isPlaying = false; // user is no longer playing
                this.startThreads = false; // stop decreasing animal's stats
                this.endGameMessage(); // end game message displays
                break;
            }
            
            // otherwise try to turn the input String into an integer
            try {
                inputNum = Integer.parseInt(input);
                if (this.isValidInputRange(inputNum, inputRange))  // if the integer is within range, break the loop as it is a valid input
                {
                    break;
                } 
                // otherwise the integer is not within range
                else 
                {
                    System.out.println("Input is out of range.\nPlease try again.");
                    continue; // loop continues until user puts valid input
                }
            } 
            catch (NumberFormatException ex) 
            {
                if (input.equalsIgnoreCase("x")) 
                {
                    this.player.isPlaying = false;
                    this.startThreads = false;
                    this.endGameMessage();
                    break;
                }
                System.out.println("Invalid input.\nPlease try again.");
            }
        } while (!this.isValidInput(input, inputRange)); // do this while the input is invalid

        return inputNum; // return valid input
    }

    /* isValidInput method
    
        Parameters: String for user input, int for valid input range
        Return: boolean, true for valid input, false for invalid input
        Description: Checks if the user's input is valid.
    */
    private boolean isValidInput(String input, int inputRange) 
    {
        // Variables
        int inputNum = 0;
        Scanner scanner = new Scanner(System.in);
        
        try {
            inputNum = Integer.parseInt(input);
            if (!this.isValidInputRange(inputNum, inputRange)) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException ex) {
            if (input.equalsIgnoreCase("x")) {
                this.player.isPlaying = false;
                this.startThreads = false;
                return true;
            }
            return false;
        }
    }
    
    /*  isValidInputRange method
    
        Parameters: int for input num, int for input range
        Return: boolean, true for is in range, false for out of range
        Description: Checks if the user's input number is within a valid range.
    */
    private boolean isValidInputRange(int inputNum, int inputRange) {
        if (inputNum > inputRange || inputNum <= 0) {
            return false;
        }
        return true;
    }

}
