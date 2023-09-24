/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 */
// Dog is an animal
public class Dog extends Animal{
    
    /*
        OBJECT CONSTRUCTORS
    */
    public Dog(String name){
        super(name);
        this.setAnimalFoodType(1);
    }
    
    // Used to generate Chicken objects based on the foster pet info retrieved from usersAndPets_data text file.
    public Dog(String name, int happiness, int hunger, int hygiene, int levelXP, int levelXPBar, int level){
        super(name, happiness, hunger, hygiene, levelXP, levelXPBar, level);
        this.setAnimalFoodType(1);
    }
}
