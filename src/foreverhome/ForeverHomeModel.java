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
    public ForeverHomeData data;
    public DecrementStatsRunnable dsr;
    public Thread petDecrementStatsThread;
    public InteractionList interactionList;
    public Player player;
    public String username;

    /*
        OBJECT CONSTRUCTOR
     */
    public ForeverHomeModel() {
    }
    
    /*
        METHODS
    */
    
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
        
        // change later!!
        /*
            idea is to have a data login flag and 
            if that login flag is true then it calls the newGame function
        */
        if(true){
            
        }
        
        this.notifyObservers();
    }
    
    public void newPetFoster(){
        
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
    
    public void interact(){
        
    }
    
    public void buyFoodMenu(){
        
    }
    
    public void feedPetFoster(){
        
    }

}
