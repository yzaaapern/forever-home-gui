package foreverhome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class ForeverHomeDBManager {
    // Instance variables
    private static final String DATABASE_URL = "jdbc:derby:ForeverHomeDatabase;create=true";
    private static final String DATABASE_USER = "foreverhome";
    private static final String DATABASE_PASSWORD = "77";
    private Connection connection = null;

    // Constructor
    public ForeverHomeDBManager() 
    {
        establishConnection();
    }

    // METHODS
    
    // MAIN METHOD
    public static void main(String[] args) 
    {
        ForeverHomeDBManager foreverHomeDBManager = new ForeverHomeDBManager();
        System.out.println(foreverHomeDBManager.getConnection());
    }
    
    // getConnection method
    public Connection getConnection() 
    {
        return this.connection;
    }
    
    // Establish a foreverHomeDBManager connection
    private void establishConnection() 
    {
        if(this.connection == null)
        {
            try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println(DATABASE_URL + " connected...");
            
            } catch (SQLException e) {
                Logger.getLogger(ForeverHomeDBManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    // myQuery method
    public ResultSet myQuery(String sql)
    {
        Connection connection = this.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }   catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    //myUpdate method
    public void myUpdate(String sql)
    {
        Connection connection = this.connection;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Close the foreverHomeDBManager connection
    public void closeConnection() 
    {
        if (connection != null) 
        {
            try 
            {
            connection.close();
            System.out.println("Database connection closed.");
            }
            catch (SQLException e) 
            {
            Logger.getLogger(ForeverHomeDBManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


}
