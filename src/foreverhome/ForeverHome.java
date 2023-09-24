/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ForeverHome {

    // Instance variables & Constants
    private static final String usersAndPets_FileName = "./resources/userAndPet_data.txt";
    private static final String usersAndFoodInventory_FileName = "./resources/userAndFoodInventory_data.txt";
    
    public HashSet<Player> users; // stores all the users documented in each text file
    private static String startInput;
    private static String username;

    // Constructor
    public ForeverHome() {
        this.users = new HashSet<>();
        this.getUsersAndPets(usersAndPets_FileName);
        this.getUsersAndFoodInventory(usersAndFoodInventory_FileName);
    }

    // METHODS
    
    /*
        start Method
    
        Parameters: None
        Return: None
        Description: Assigns the global tatic variables of username and startInput from the value returned by the getUsernameAndStartInput function.
                     The returned username is used to create a Player object, which is checked in the checkUser function and 
                     used as a parameter in checkUserAndStartInput and updateUserAndPetInfo functions.
    */
    
    public void start() {
        username = getUsernameAndStartInput();
        Player player = checkUser(username);
        checkUserAndStartInput(player);
        updateUserAndPetInfo(player);
        this.updateUserAndFoodInventoryInfo(player);
    }

    /*
        getUsernameAndStartInput Method
    
        Parameters: None
        Return: String userName
        Description: Lets the user decide if they want to login or signup or exit before launching the Forever Home game.
                     - If the user decides to login(1) or sign up(2), they are asked to enter their username but they are not allowed to enter an empty string
                     - If the user decides to enter exit(3), the program terminates and does not launch the Forever Home game.
    */
    
    private String getUsernameAndStartInput() {
        String userName = "";
        int inputNum = 0;
        int numOfOptions = 3;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to:\n"
                + "1. LOG IN\n2. SIGN UP\n3. EXIT?");
        do { // Loop continues until the input is valid
            System.out.print("Your choice: ");
            startInput = scanner.nextLine();

            try {
                inputNum = Integer.parseInt(startInput); // Converts the String startInput variable into an int and stores it in inputNum
            } catch (NumberFormatException ex) {
                if (startInput == "" || startInput == " ") { // If the startInput variable is an empty string, the user is forced to input again.
                    startInput = scanner.nextLine();
                }
                System.out.println("Invalid input. Please try again."); // Prints default error message if the input doesn't meet the required data type
            }
        } while (!isValidInput(startInput, numOfOptions)); // isValidInput function checks if the value of startInput is a valid input
        
            // User chooses to login            
            if (inputNum == 1) {
                System.out.println("You chose to log in!");
                
                do { // Loop continues until the user doesn't input an empty string for userName
                    System.out.print("Enter your username: ");
                    userName = scanner.next();
                } while (userName == "" || userName == " ");

            }  
            
            // User chooses to sign up
            else if (inputNum == 2) {
                System.out.println("You chose to sign up!");
                do { // Loop continues until the user doesn't input an empty string for userName
                    System.out.print("Enter your username: ");
                    userName = scanner.next();
                } while (userName == "" || userName == " ");

            }
            
            // User chooses to exit
            else if (inputNum == 3) {
                System.out.println("You chose to exit! We will not be launching Forever Home.");

            }

//        } while (!isValidInput(startInput, numOfOptions)); // isValidInput function checks if the value of startInput is a valid input

        return userName; // Returns username if the loop ends

    }

    /*
        getUsernameAndPets Method
    
        Parameters: String fileName
        Return: None
        Description: Uses a BufferedReader to read a file with the given parameter file name. 
                     If the file exists and isn't empty, the file gets the user and pet data and stores it in the users HashSet.
    */
    private void getUsersAndPets(String fileName) {
        BufferedReader fileReader;
        try { // try-catch block is placed in case of error or exceptions
            fileReader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = fileReader.readLine()) != null) { // While the fileReader reads lines that aren't empty
            String[] userAndPet = line.split("\\|"); // It splits every line using the | delimiter and stores it in userPet array

                if (userAndPet.length == 2) { // If userPet array has a length of 2
                    String playerInfo = userAndPet[0]; // Value of 0th index is stored in playerInfo
                    String petFosterInfo = userAndPet[1]; // Value of 1st index is stored in petFosterInfo
                    String[] playerData = playerInfo.split(":"); // Splits playerInfo using the : delimiter and stores it in playerData array

                    if (playerData.length == 2) { // If the playerData array has a length of 2
                        String playerName = playerData[0]; // Value of 0th index is stored into playerName
                        int dabloons = Integer.parseInt(playerData[1]); // Value of 1st index is stored into dabloons
                        Player player = new Player(playerName); // Creates a new player with playerName
                        player.setDabloons(dabloons); // Sets player's dabloons with the value stored in local dabloons variable

                        if ("NoPet".equals(petFosterInfo)) { // If the petFosterInfo is equal to NoPet, then the player doesn't have a pet foster and is added into the users HashSet
                            player.setFosterPet(null);
                            player.hasFosterPet = false;
                            users.add(player);
                        } else { // If the petFosterInfo has pet data
                            String[] petData = petFosterInfo.split(":");  // Pet data is split using the : delimiter and stores it in petData array
                            if (petData.length == 8) { // If petData's length is 8

                                String petName = petData[0]; // Value of 0th index is stored in petName
                                int petInstance = Integer.parseInt(petData[1]); // Value of 1st index is stored in petInstance
                                int petHappiness = Integer.parseInt(petData[2]); // Value of 2nd index is stored in petHappiness
                                int petHunger = Integer.parseInt(petData[3]); // Value of 3rd index is stored in petHunger
                                int petHygiene = Integer.parseInt(petData[4]); // Value of 4th index is stored in petHygiene
                                int petLevelXP = Integer.parseInt(petData[5]); // Value of 5th index is stored in petLevelXP
                                int petLevelXPBar = Integer.parseInt(petData[6]); // Value of 6th index is stored in petLevelXPBar
                                int petLevel = Integer.parseInt(petData[7]); // Value of 7th index is stored in petLevel

                                // fosterPet object is instantiated using checkFosterInstance object, using the retrieved pet data values as parameters
                                Animal fosterPet = checkFosterInstance(petInstance, petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                                player.setFosterPet(fosterPet); // Instantiated pet foster is assigned to the current player
                                player.hasFosterPet = true; // Sets the player's hasFosterPet status to true
                                users.add(player); // Adds to users HashSet
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
        getUserAndFoodInventory Method
    
        Parameters: String fileName
        Return: None
        Description: Uses a BufferedReader to read a file with the given parameter file name.
                     If the file exists and isn't empty, the file gets user's name and food inventory
                     and assigns it to a respective user that is currently playing the game and exists in the users HashSet.
    */
    private void getUsersAndFoodInventory(String fileName) 
    {
        // Variables
        Player player = null;
        String[] userAndFoodInventory;
        String[] foodInventory;
        String[] foodData;
        String foodName = "";
        int foodCount = 0;
        
        final int PLAYER_NAME_INDEX = 0;
        final int FOOD_INVENTORY_INDEX = 1;
        final int FOOD_NAME_INDEX = 0;
        final int FOOD_COUNT_INDEX = 1;
        
        // Delimiters to split the data in the lines
        String bigDelim = "\\|";
        String mediumDelim = ",";
        String smallDelim = ":";
        
        BufferedReader fileReader;
        try //try-catch block in case of errors and exceptions
        {
            fileReader = new BufferedReader(new FileReader(fileName));
            String line;
            
            // While the file reader has not reached the end of the text
            while ((line = fileReader.readLine()) != null) 
            {
                // User and food inventory array is split with a "|"
                userAndFoodInventory = line.split(bigDelim);
                
                // Player name is the first part of the array
                String playerName = userAndFoodInventory[PLAYER_NAME_INDEX];
                
                // Food inventory info string is the second part of the array
                String foodInventoryInfo = userAndFoodInventory[FOOD_INVENTORY_INDEX];
                
                // Check if user is a user in the system
                player = this.checkUser(playerName);
                
                // Food inventory info array is further separated into food inventory data which is split with ":"
                foodInventory = foodInventoryInfo.split(mediumDelim);
                
                if(foodInventory.length == FoodInventory.NUM_OF_FOODS)
                {
                    for(int i = 0; i < foodInventory.length; i++)
                    {
                        // An element of food data consists of a food name [0] and food count [1]
                        foodData = foodInventory[i].split(smallDelim);
                        foodName = foodData[FOOD_NAME_INDEX];
                        foodCount = Integer.parseInt(foodData[FOOD_COUNT_INDEX]);
                        
                        // If the food name is equal to a player's current food inventory which is already instantiated
                        if(foodName.equals(player.getFoodInventory().getFoods()[i].getFoodName()))
                        {
                            player.getFoodInventory().getFoods()[i].setFoodCount(foodCount); // It replaces the food object with the same name's food count of the actual player's food inventory.
                        }                 
                    }
                }
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /*
        checkUser Method
    
        Parameters: String username
        Return: Player object
        Description: Checks if a player already exists in the users HashSet and returns the player if they do exist in the HashSet.
                     If the player does not exist, a new player is created and returned.
    */
    public Player checkUser(String username) {
        Player p = null;

        for (Player player : users) {
            if (player.getName().equals(username)) {
                p = player;
                break;
            }
        }

        if (p == null) {
            p = new Player(username);
        }

        return p;
    }
    
    /*
        checkUserAndStartInput Method
    
        Parameters: Player p
        Return: None
        Description: Checks the HashSet users and startInput variable, using Player p as a parameter, before calling the confirmUserIdentity function.
    */
    public void checkUserAndStartInput(Player p) {
        int startInputNum = Integer.parseInt(startInput);
        Player confirmPlayer = p; // Assigns parameter player into a local variable
        
        if (startInputNum == 1) { // If startInput is Login (1)
            if (p != null && p.hasFosterPet && users.contains(p)) { // If the player is not null and the player has a foster pet, and it is contained in HashSet users
                System.out.println("We have found your name in our file system! You have an existing foster pet, named: " + p.fosterPet.getName()
                        + ".\nIs this you? "); // Customised message for found player
                confirmUserIdentity(confirmPlayer); // Calls confirmUserIdentity function
            } 
            
            else if (p != null && !p.hasFosterPet && users.contains(p)) { // If the player is not null and the player doesn't have a foster pet, and it is contained in HashSet users
                System.out.println("We have found your name in our file system! However, it seems like you do not have an existing foster."
                        + ".\nIs this you? "); // Customised message for found player
                confirmUserIdentity(confirmPlayer); // Calls confirmUserIdentity function
            } 
            
            else if (p != null && !p.hasFosterPet && !users.contains(p)) { // If the player is not null and the player is not contained in HashSet users
                System.out.println("Your name is not found within our records. That's okay though!"
                        + "\nStarting a new game of Forever Home..."); // Customised message for new player
                confirmPlayer = new Player(username); // Creates new player
                users.add(confirmPlayer); // Adds player to HashSet users
                Game game = new Game(confirmPlayer); // Generates a new game

            }
        } else if (startInputNum == 2) { // If startInput is Signup (2)
            if (p != null && p.hasFosterPet && users.contains(p)) { // If the player is not null and the player has a foster pet, and it is contained in HashSet users
                System.out.println("You chose to sign up with " + p.getName()
                        + ".\nThis name exists within our file system, with a foster pet named: " + p.fosterPet.getName()
                        + ".\nIs this really you? "); // Customised message for found player
                confirmUserIdentity(confirmPlayer); // Calls confirmUserIdentity function
            } 
            
            else if (p != null && !p.hasFosterPet && users.contains(p)) { // If the player is not null and the player doesn't have a foster pet, and it is contained in HashSet users
                System.out.println("You chose to sign up with " + p.getName()
                        + ".\nThis name exists within our file system, but you don't seem to have a foster pet."
                        + ".\nIs this really you? "); // Customised message for found player
                confirmUserIdentity(confirmPlayer);// Calls confirmUserIdentity function
            } 
            
            else if (p != null && !p.hasFosterPet && !users.contains(p)) { // If the player is not null and the player is not contained in HashSet users
                System.out.println("Signing you up for a new game of Forever Home!");  // Customised message for new player
                confirmPlayer = new Player(username); // Creates new player
                users.add(confirmPlayer); // Adds player to HashSet users
                Game game = new Game(confirmPlayer); // Generates a new game
            }
        
        } else {
            return; // If startInput is neither Login (1) or Signup (2) then the function returns
        }
    }

    /*
    NOTE: USE FOR FIXING ERRORS TMR!
    
        confirmUserIdentity  Method
    
        Parameters: String fileName
        Return: None
        Description: Gets a user to input either 1 or 2 to confirm their identity using a loop.
                     If the user confirms their identity, the ForeverHome game is launched and the loop breaks by returning true.
                     If the user doesn't confirm their identity, it redirects them back to the start of the program and the loop breaks by returning true.
                     Else, the user has to input again and the loop doesn't break;
    */
    
    public boolean confirmUserIdentity(Player p) {
        Game game;
        Player player = p;
        boolean isValid = false;
        int inputNum;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1. Yes, this is me."); // 1 - Confirmation of identity choice 
            System.out.println("2. No, this is not me."); // 2 - Rejecting identity choice
            try {
                System.out.print("Your choice: ");
                inputNum = scanner.nextInt();

                if (inputNum == 1) {
                    player.isPlaying = true;
                    System.out.println("We will now proceed to launch Forever Home!");
                    // If the player doesn't have a foster pet because they recently put it up for adoption
                    if(!player.hasFosterPet && !player.isPlaying && player.fosterPet.getIsAdopted()){
                        break;
                    }
                    // If the player has a foster pet
                    else if (player.hasFosterPet) {
                        game = new Game(p, p.fosterPet);
                    }
                    // If the player doesn't have a foster pet and is playing
                    else if (!player.hasFosterPet && player.isPlaying) {
                        player.isPlaying = true;
                        game = new Game(p);
                    }
                    // If the player doesn't have a foster pet and isn't playing
                    else if (!player.hasFosterPet && !player.isPlaying)
                    {
                        break;
                    }
                    
                    isValid = true; // Breaks the loop because it is a valid input

                } else if (inputNum == 2) { 
                    System.out.println("Is that so?! Well then, redirecting you back to the start...");
                    isValid = true; // Breaks the loop
                    start(); // Calls the start() function to reset
                } else { // User entered an inputNum beyond the range
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (InputMismatchException e) { // Catches any errors that pops up if user entered a different type of input than what was required
                System.out.println("Invalid input! Please try again.");
                scanner.next(); // Clears the scanner
            }

        } while (!isValid);
        return true; // Returns true when loop breaks
    }
    
    /*
        updateUserAndPetInfo Method
    
        Parameters: Player p
        Return: None
        Description: Updates the player info in users HashSet if the player exists; else, the new player is added into the HashSet.
                     The updated HashSet is written and stored in the text file storing user and pet info.
    */
    public void updateUserAndPetInfo(Player p) {
        Player modifiedPlayer = p;
        if (modifiedPlayer == null) { // If player is null, the function breaks and returns nothing
            return;
        }

        for(Player player : users){
            if(modifiedPlayer.getName().equals(player.getName())){ //If the modified player's name is the same as a player's name in users
                users.remove(player); // Old player with the same name is removed
                users.add(modifiedPlayer); // New player with the same name is added
                break; // Breaks the loop
            }
            else{
                users.add(modifiedPlayer); // New player is added into users
                break;
            }
        }

        try { // try-catch block in the case of errors or exceptions
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(usersAndPets_FileName)); // Destination of fileWriter, where the updated user and pet information will be stored
            for (Player player : users) {
                fileWriter.write(player.fileUserAndPetToString() + '\n'); // Overrides every player in the text file with the updated information of each player
            }
            fileWriter.close(); // Close the fileWriter after every player has been overriden
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
        updateUserAndFoodInventoryInfo Method
    
        Parameters: Player p
        Return: None
        Description: The updated HashSet is written and stored in the text file storing user and food inventory info.
    */
    public void updateUserAndFoodInventoryInfo(Player p)
    {
        try { // try-catch block in the case of errors or exceptions
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(usersAndFoodInventory_FileName)); // Destination of fileWriter, where the updated user and food inventory information will be stored
            for (Player player : users) { 
                fileWriter.write(player.fileUserAndFoodInventoryToString() + '\n'); // Overrides every player in the text file with the updated information of each player
            }
            fileWriter.close(); // Close the fileWriter after every player has been overriden
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
        checkFosterInstance Method
    
        Parameters: int animalType, String petName, int petHappiness, int petHUnger, int petHygiene, int petLevelXP, int petLevelXPBar, int petLevel
        Return: Animal object
        Description: Checks the animal instance according to animalType and generates a new animal object that is an instance of one of its classes depending on the value of animalType
    */
    public Animal checkFosterInstance(int animalType, String petName, int petHappiness, int petHunger, int petHygiene, int petLevelXP, int petLevelXPBar, int petLevel) {
        Animal a = null;
        switch (animalType) {
            case 1:
                a = new Dog(petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                break;
            case 2:
                a = new Cat(petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                break;
            case 3:
                a = new Rat(petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                break;
            case 4:
                a = new Parrot(petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                break;
            case 5:
                a = new Chicken(petName, petHappiness, petHunger, petHygiene, petLevelXP, petLevelXPBar, petLevel);
                break;
        }

        return a;
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
                System.out.println("Input is out of range.\nPlease try again.");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.\nPlease try again.");
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
    
    /*
        MAIN METHOD
    */
    public static void main(String[] args) {
        ForeverHome fhGame = new ForeverHome();
        fhGame.start(); // Starts ForeverHome game
    }
}
