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
public class ForeverHomeModelTest {
    
    public ForeverHomeModelTest() {
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
     * Test of setPlayer method, of class ForeverHomeModel.
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        String username = "Anny";
        ForeverHomeModel instance = new ForeverHomeModel();
        instance.setPlayer(username);
        String playerName = instance.player.getName();
        
        assertEquals(username, playerName);
    }

    /**
     * Test of buyFood method, of class ForeverHomeModel.
     */
    @Test
    public void testBuyFood() {
        System.out.println("buyFood");
        Food food = new FoodType1("Kibble", 20, 10, 0);
        
        ForeverHomeModel instance = new ForeverHomeModel();
        instance.setPlayer("Anny");
        instance.player.setDabloons(100);
        instance.buyFood(food);
        
        
    }
    
}
