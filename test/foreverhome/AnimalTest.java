/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package foreverhome;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yzape
 */
public class AnimalTest {

    Animal animal;

    public AnimalTest() {
        animal = null;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setName and getName method, of class Animal.
     */
    @Test
    public void testSetAndGetName1() {
        System.out.println("setName");
        String name = "Cookie";
        animal = new Rat(name);

        String actualName = animal.getName();
        assertEquals(actualName, name);
        
        name = null;
        try{
            animal = new Rat(name);
        } catch (NullPointerException e){
            System.out.println("The animal doesn't have a name.");
        }
        
        assertNull(animal.getName());
    }


    /**
     * Test of setAnimalFoodType method, of class Animal.
     */
    @Test
    public void testSetAndGetAnimalFoodType() {
        System.out.println("setAnimalFoodType");
        animal = new Cat("Cuppy");
        int actualFoodType = animal.getAnimalFoodType();
        int expected = 1;
        
        assertTrue(actualFoodType == expected);
    }

    /**
     * Test of animal functions, such as setHappiness, setHunger, etc., of class Animal
     */
    @Test
    public void testAnimalMethods(){
        System.out.println("animalMethods");
        
        FoodInventoryTest fTest = new FoodInventoryTest();
        animal = new Dog("Teddy");
        animal.setHunger(fTest.foodInventory.foodForAll.getFoodValue());
        int hungerValue = animal.getHunger();
        int expected = 10;
        assertEquals(expected, hungerValue);
        
        int hygieneValue = animal.getHygiene();
        expected = 0;
        assertEquals(expected, hygieneValue);
        
        animal.incHappiness();
        int happinessValue = animal.getHappiness();
        expected = 25;
        assertEquals(expected, happinessValue);
        
        int levelXPValue = animal.getLevelXP();
        assertEquals(levelXPValue, 0);
        
        int levelValue = animal.getLevel();
        assertEquals(levelValue, 0);
    }
    
    /**
     * Test of checkFosterPet method, of class Animal.
     */
    @Test
    public void testCheckFosterPet() {
        System.out.println("checkFosterPet");
        
        Animal fosterPet = new Chicken("Chico");
        String expResult = "chicken";
        String result = fosterPet.checkFosterPet(fosterPet);
        assertSame(expResult, result);
    }

}
