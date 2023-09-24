/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */
public class Parrot extends Animal
{
    // Constructor
    public Parrot(String name){
        super(name);
        this.setAnimalFoodType(2);
    }
    
    // Used to generate Parrot objects based on the foster pet info retrieved from usersAndPets_data text file.
    public Parrot(String name, int happiness, int hunger, int hygiene, int levelXP, int levelXPBar, int level){
        super(name, happiness, hunger, hygiene, levelXP, levelXPBar, level);
        this.setAnimalFoodType(2);
    }
}
