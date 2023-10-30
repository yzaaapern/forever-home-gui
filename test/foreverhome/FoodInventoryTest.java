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
public class FoodInventoryTest {

    public FoodInventory foodInventory;

    public FoodInventoryTest() {
        foodInventory = new FoodInventory();
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
     * Test of getFoods method, of class FoodInventory.
     */
    @Test
    public void testGetFoods() {
        System.out.println("getFoods");
        FoodInventory instance = new FoodInventory();
        Food[] result = instance.getFoods();
        assertNotNull(result);
    }

    /**
     * Test of setFoods method, of class FoodInventory.
     */
    @Test
    public void testSetFoods() {
        System.out.println("setFoods");

        Food foodForAll = new FoodType0("Food for All", 10, 6, 5);
        Food kibble = new FoodType1("Kibble", 20, 10, 0);
        Food cannedFood = new FoodType1("Canned Food", 30, 12, 0);
        Food veggieMix = new FoodType2("Veggie Mix", 20, 10, 0);
        Food seeds = new FoodType2("Seeds", 30, 12, 0);
        Food rainbowTreat = new FoodType0("Rainbow Treat", 50, 20, 0);
        Food water = new FoodType0("Rainbow Treat", 50, 20, 0);

        Food[] newFoods = {foodForAll, kibble, cannedFood, veggieMix, seeds, rainbowTreat, water};
        FoodInventory instance = new FoodInventory();
        instance.setFoods(newFoods);
        
        assertSame(instance.kibble.getFoodName(), newFoods[1].getFoodName());

    }

}
