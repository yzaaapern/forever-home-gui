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
    private String userName; // FK
    
    
    // Constructor
    
    // New pet within the database (doesn't have it's own ID yet)
    public PetData(String petName, String petInstance, int petLevel, int petLevelXP, int petHunger, int petHygiene, int petHappiness, String userName)
    {
        this.petID = generateRandomPetID();
        this.setPetName(petName);
        this.setPetInstance(petInstance);
        this.setPetLevel(petLevel);
        this.setPetLevelXP(petLevelXP);
        this.setPetHunger(petHunger);
        this.setPetHygiene(petHygiene);
        this.setPetHappiness(petHappiness);
        this.setUserName(userName);
    }
    
    // New pet within the database
    public PetData(Animal pet, String userName)
    {
        this.petID = generateRandomPetID();
        this.setPetName(pet.getName());
        this.setPetInstance(this.checkPetInstance(pet));
        this.setPetLevel(pet.getLevel());
        this.setPetLevelXP(pet.getLevelXP());
        this.setPetHunger(pet.getHunger());
        this.setPetHygiene(pet.getHygiene());
        this.setPetHappiness(pet.getHappiness());
        this.setUserName(userName);
    }
    
    // Existing pet within the database
    public PetData(String petID, String petName, String petInstance, int petLevel, int petLevelXP, int petHunger, int petHygiene, int petHappiness, String userName)
    {
        this.petID = petID;
        this.setPetName(petName);
        this.setPetInstance(petInstance);
        this.setPetLevel(petLevel);
        this.setPetLevelXP(petLevelXP);
        this.setPetHunger(petHunger);
        this.setPetHygiene(petHygiene);
        this.setPetHappiness(petHappiness);
        this.setUserName(userName);
    }
    
    // GET & SET METHODS
    
    /**
     * @return the petID
     */
    public String getPetID() {
        return petID;
    }

    /**
     * @return the petName
     */
    public String getPetName() {
        return petName;
    }

    /**
     * @param petName the petName to set
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * @return the petInstance
     */
    public String getPetInstance() {
        return petInstance;
    }

    /**
     * @param petInstance the petInstance to set
     */
    public void setPetInstance(String petInstance) {
        this.petInstance = petInstance;
    }

    /**
     * @return the petLevel
     */
    public int getPetLevel() {
        return petLevel;
    }

    /**
     * @param petLevel the petLevel to set
     */
    public void setPetLevel(int petLevel) {
        this.petLevel = petLevel;
    }

    /**
     * @return the petLevelXP
     */
    public int getPetLevelXP() {
        return petLevelXP;
    }

    /**
     * @param petLevelXP the petLevelXP to set
     */
    public void setPetLevelXP(int petLevelXP) {
        this.petLevelXP = petLevelXP;
    }

    /**
     * @return the petHunger
     */
    public int getPetHunger() {
        return petHunger;
    }

    /**
     * @param petHunger the petHunger to set
     */
    public void setPetHunger(int petHunger) {
        this.petHunger = petHunger;
    }

    /**
     * @return the petHygiene
     */
    public int getPetHygiene() {
        return petHygiene;
    }

    /**
     * @param petHygiene the petHygiene to set
     */
    public void setPetHygiene(int petHygiene) {
        this.petHygiene = petHygiene;
    }

    /**
     * @return the petHappiness
     */
    public int getPetHappiness() {
        return petHappiness;
    }

    /**
     * @param petHappiness the petHappiness to set
     */
    public void setPetHappiness(int petHappiness) {
        this.petHappiness = petHappiness;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // METHODS
    
    private String checkPetInstance(Animal pet)
    {
        if(pet instanceof Dog)
        {
            return "dog";
        }
        else if(pet instanceof Cat)
        {
            return "cat";
        }
        else if(pet instanceof Rat)
        {
            return "rat";
        }
        else if(pet instanceof Parrot)
        {
            return "parrot";
        }
        else
        {
            return "chicken";
        }
    }
    
    // Generate a random petID using UUID
    private String generateRandomPetID()
    {
        return UUID.randomUUID().toString();
    }
    
    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetData petData = (PetData) o;
        return  Objects.equals(getPetID(), petData.getPetID());
    }
    
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(getPetID());
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
}
