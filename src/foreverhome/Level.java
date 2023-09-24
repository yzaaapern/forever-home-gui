package foreverhome;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author yzape
 * Name: Yza Pernia
 * Student ID: 21137984
 * Date Created: 06/08/2023
 * 
 * The Level interface is a collection of required methods that will be implemented by the Animal abstract class.
 * Each animal rescue will start with the lowest level and slowly build up their exp to reach the highest level. 
 * Once the animal rescue has reached the highest level, this means that the foster animal is ready for adoption.
 */
public interface Level {
    
    /*
        CONSTANT VARIABLES
    */
    public final int DEFAULT_LEVELXP_CAP = 100;
    public final int INC_LEVELXP_CAP = 40;
    public final int INC_EXP = 10;
    public final int DEC_EXP = 15;
    public static final int MAX_LEVEL = 10;
    
    /*
        METHODS
    */
    public boolean checkLevelForInteract(int level);
    
    public boolean checkLevelForIncLevel();
    
    public void incLevel();
  
    public void incLevelXP();
    
    public void decLevelXP();
    
    public void resetLevelXP();
}
