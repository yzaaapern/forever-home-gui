/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */

// Rat is an animal
public class Rat extends Animal{
    
    // Constructor
    public Rat(String name){
        super(name);
        this.setAnimalFoodType(2);
    }
    
    // Used to generate Rat objects based on the foster pet info retrieved from usersAndPets_data text file.
    public Rat(String name, int happiness, int hunger, int hygiene, int levelXP, int levelXPBar, int level){
        super(name, happiness, hunger, hygiene, levelXP, levelXPBar, level);
        this.setAnimalFoodType(2);
    }
}
