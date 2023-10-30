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
public class PlayerTest {
    
    public Player player;
    public PlayerTest() {
        player = null;
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
     * Test of player methods, of class Player.
     */
    @Test
    public void testPlayerMethods() {
        System.out.println("playerTest");
        String name = null;
        player = new Player(name);
        player.setName(name);
        assertNull(player.getName());
        
        name = "Yza";
        player = new Player(name);
        String actualName = player.getName();
        String expected = "Yza";
        assertEquals(expected, actualName);
        
        String password = "catPanD@s";
        player.setPassword(password);
        String actualPassword = player.getPassword();
        expected = "catPanD@s";
        assertEquals(expected, actualPassword);
        
        
    }
}

