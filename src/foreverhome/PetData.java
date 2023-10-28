/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author AnnGa
 */
public class PetData 
{
    // INSTANCE VARIABLES
    
    private String petID; // PK
    // Attributes
    private String petName;
    private String petInstance; // not sure about this one
    private int petLevel;
    private int petLevelXP;
    private int petHunger;
    private int petHygiene;
    private int petHappiness;
    private boolean isAdopted;
    private String userName; // FK
    
    
    // Constructor
    // New pet within the database (doesn't have it's own ID yet)
    public PetData(Animal pet, String userName)
    {
        this.petID = generateRandomPetID();
        this.setPetName(pet.getName());
        this.setPetInstance(pet.checkFosterPet(pet));
        this.setPetLevel(pet.getLevel());
        this.setPetLevelXP(pet.getLevelXP());
        this.setPetHunger(pet.getHunger());
        this.setPetHygiene(pet.getHygiene());
        this.setPetHappiness(pet.getHappiness());
        this.setIsAdopted(false);
        this.setUserName(userName);
    }
    
    // Existing pet within the database
    public PetData(String petID, String petName, String petInstance, int petLevel, int petLevelXP, int petHunger, int petHygiene, int petHappiness, boolean isAdopted, String userName)
    {
        this.petID = petID;
        this.setPetName(petName);
        this.setPetInstance(petInstance);
        this.setPetLevel(petLevel);
        this.setPetLevelXP(petLevelXP);
        this.setPetHunger(petHunger);
        this.setPetHygiene(petHygiene);
        this.setPetHappiness(petHappiness);
        this.setIsAdopted(isAdopted);
        this.setUserName(userName);
    }
    
    // Existing pet within the database
    public PetData(String petID, Animal pet, boolean isAdopted, String userName)
    {
        this.petID = petID;
        this.setPetName(pet.getName());
        this.setPetInstance(pet.checkFosterPet(pet));
        this.setPetLevel(pet.getLevel());
        this.setPetLevelXP(pet.getLevelXP());
        this.setPetHunger(pet.getHunger());
        this.setPetHygiene(pet.getHygiene());
        this.setPetHappiness(pet.getHappiness());
        this.setIsAdopted(isAdopted);
        this.setUserName(userName);
    }
    
    // GET & SET METHODS
    
    public String getPetID() {
        return petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetInstance() {
        return petInstance;
    }

    public void setPetInstance(String petInstance) {
        this.petInstance = petInstance;
    }

    public int getPetLevel() {
        return petLevel;
    }

    public void setPetLevel(int petLevel) {
        this.petLevel = petLevel;
    }

    public int getPetLevelXP() {
        return petLevelXP;
    }

    public void setPetLevelXP(int petLevelXP) {
        this.petLevelXP = petLevelXP;
    }

    public int getPetHunger() {
        return petHunger;
    }

    public void setPetHunger(int petHunger) {
        this.petHunger = petHunger;
    }

    public int getPetHygiene() {
        return petHygiene;
    }

    public void setPetHygiene(int petHygiene) {
        this.petHygiene = petHygiene;
    }

    public int getPetHappiness() {
        return petHappiness;
    }

    public void setPetHappiness(int petHappiness) {
        this.petHappiness = petHappiness;
    }

    public boolean getIsAdopted()
    {
        return isAdopted;
    }
    
    public void setIsAdopted(boolean adoptedStatus)
    {
        this.isAdopted = adoptedStatus;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // METHODS
    
    // Generate a random petID using UUID
    private String generateRandomPetID()
    {
        return UUID.randomUUID().toString();
    }
    
    // Override equals method
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetData petData = (PetData) o;
        return Objects.equals(getPetName(), petData.getPetName()) &&
               Objects.equals(getUserName(), petData.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetName(), getUserName());
    }

    
    // toString method
    @Override
    public String toString() 
    {
        return "PetData{" +
                "petID='" + getPetID() + '\'' +
                ", petName='" + getPetName() + '\'' +
                ", petInstance='" + getPetInstance() + '\'' +
                ", petLevel=" + getPetLevel() +
                ", petLevelXP=" + getPetLevelXP() +
                ", petHunger=" + getPetHunger() +
                ", petHygiene=" + getPetHygiene() +
                ", petHappiness=" + getPetHappiness() +
                ", userName='" + getUserName() + '\'' +
                '}';
    }
    
    // toAnimal method
    public Animal toAnimal()
    {
        Animal pet = null;
        if (this.petInstance.equalsIgnoreCase("dog")) {
                pet = new Dog(petName);
            } else if (this.petInstance.equalsIgnoreCase("cat")) {
                pet = new Cat(petName);
            } else if (this.petInstance.equalsIgnoreCase("rat")) {
                pet = new Rat(petName);
            } else if (this.petInstance.equalsIgnoreCase("parrot")) {
                pet = new Parrot(petName);
            } else if (this.petInstance.equalsIgnoreCase("chicken")) {
                pet = new Chicken(petName);
            }
        pet.setLevel(petLevel);
        pet.setLevelXP(petLevelXP);
        pet.setHunger(petHunger);
        pet.setHygiene(petHygiene);
        pet.setHappiness(petHappiness);
        pet.setIsAdopted(isAdopted);
        
        return pet;
    }
}
