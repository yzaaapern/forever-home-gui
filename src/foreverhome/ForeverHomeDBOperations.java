
package foreverhome;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author annga
 */
public class ForeverHomeDBOperations 
{    
    // Instance variables
    private ForeverHomeDBManager dbManager;
    
    // Constructor
    public ForeverHomeDBOperations(ForeverHomeDBManager dbManager)
    {
        this.dbManager = dbManager;
    }
    
    // METHODS
    
    // MAIN METHOD
    public static void main(String[] args) 
    {
        ForeverHomeDBManager dbManager = ForeverHomeDBManager.getInstance();
        ForeverHomeDBOperations dbOperations = new ForeverHomeDBOperations(dbManager);
        
        // Drop tables
        dbOperations.dropTable("USERDATA");
        dbOperations.dropTable(ForeverHomeDB.PET_TABLE);
        dbOperations.dropTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
        dbOperations.dropTable(ForeverHomeDB.USER_DATA_TABLE);
        
        // Create tables
        dbOperations.createTable(ForeverHomeDB.USER_DATA_TABLE);
        dbOperations.createTable(ForeverHomeDB.PET_TABLE);
        dbOperations.createTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
    }
    
    /* createTable method
    
    Parameters: String tableName
    Return: None
    Description: creates table with the tableName as its name
    */
    public void createTable(String tableName) 
    {
        String sqlCreateTable = generateCreateTableSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlCreateTable)) 
        {
            if (!tableExists(dbManager.getConnection(), tableName)) 
            {
                preparedStatement.executeUpdate();
                System.out.println("Table '" + tableName + "' created successfully.");
                insertSampleData(tableName);
            } 
            else 
            {
                System.out.println("Table '" + tableName + "' already exists.");
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }
    }
    
    private String generateCreateTableSQL(String tableName) 
    {
        // Generate SQL for creating the specified table
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName)) {
            return "CREATE TABLE user_data (" +
                "userName VARCHAR(20) PRIMARY KEY," +
                "userPassword VARCHAR(25)," +
                "userDabloons INT," +
                "userHasPet BOOLEAN" +
                ")";
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName)) {
            return "CREATE TABLE pet (" +
                "petID VARCHAR(36) PRIMARY KEY," +
                "petName VARCHAR(25)," +
                "petInstance VARCHAR(10)," +
                "petLevel INT," +
                "petLevelXP INT," +
                "petHunger INT," +
                "petHygiene INT," +
                "petHappiness INT," +
                "isAdopted BOOLEAN," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" +
                ")";
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "CREATE TABLE food_inventory (" +
                "foodInventoryID VARCHAR(36) PRIMARY KEY," +
                "foodInventory VARCHAR(255)," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" +
                ")";
        }
        return "";
    }

    /* dropTable method
    
    Parameters: String tableName
    Return: void
    Description: Drops specified table if it exists
    */
    public void dropTable(String tableName)
    {
        String sqlDropTable = "DROP TABLE " + tableName;
        
        try
        {
            if(this.tableExists(this.dbManager.getConnection(), tableName))
            {
                Statement statement = dbManager.getConnection().createStatement();
                statement.executeUpdate(sqlDropTable);
                System.out.println("Table '" + tableName + "' dropped successfully. ");
            }
            else
            {
                System.out.println("Table '" + tableName + "' does not exist");
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }
        
    }
    
    // INSERT DATA METHODS
    
    // INSERTING
    public void insertData(String tableName, Object data) 
    {
        String sqlInsert = generateInsertDataSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlInsert)) 
        {            
            if (tableExists(dbManager.getConnection(), tableName)) 
            {
                // Check if the primary key exists in the table
                if (primaryKeyExists(tableName, data)) {
//                    System.out.println("Record with the specified primary key already exists in table '" + tableName + "'.");
                    return;
                }
                else
                {
                    setInsertDataParameters(preparedStatement, tableName, data);
                    preparedStatement.executeUpdate();
//                    System.out.println("Data inserted into table '" + tableName + "' successfully.");
                }
            } 
            else 
            {
                System.out.println("Table '" + tableName + "' does not exist.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        

    }
    
    // SAMPLE DATA
    private void insertSampleData(String tableName) 
    {
        String sqlInsert = generateInsertDataSQL(tableName);

        // sample playerSample1
        Player playerSample1 = new Player("anny");
        playerSample1.setPassword("123");
        playerSample1.setDabloons(1000);
        playerSample1.hasFosterPet = true;
        UserData userData = new UserData(playerSample1);
        
        // sample petSample1
        Animal petSample1 = new Dog("Pookie");
        PetData petDataSample1 = new PetData(petSample1, playerSample1.getName());
        
        // sample foodInventory
        FoodInventoryData foodInventoryDataSample1 = new FoodInventoryData(playerSample1.getFoodInventory(), playerSample1.getName());
        
        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlInsert)) {
            if (tableExists(dbManager.getConnection(), tableName)) {
                // Set the sample data parameters based on the table name
                if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName)) {
                    // Example sample data for user_data table
                    preparedStatement.setString(1, userData.getUserName());
                    preparedStatement.setString(2, userData.getUserPassword());
                    preparedStatement.setInt(3, userData.getUserDabloons());
                    preparedStatement.setBoolean(4, userData.isUserHasPet());
                } else if (ForeverHomeDB.PET_TABLE.equals(tableName)) {
                    // Example sample data for petSample1 table
                    preparedStatement.setString(1, petDataSample1.getPetID());
                    preparedStatement.setString(2, petDataSample1.getPetName());
                    preparedStatement.setString(3, petDataSample1.getPetInstance());
                    preparedStatement.setInt(4, petDataSample1.getPetLevel());
                    preparedStatement.setInt(5, petDataSample1.getPetLevelXP());
                    preparedStatement.setInt(6, petDataSample1.getPetHunger());
                    preparedStatement.setInt(7, petDataSample1.getPetHappiness());
                    preparedStatement.setInt(8, petDataSample1.getPetHygiene());
                    preparedStatement.setBoolean(9, petDataSample1.getIsAdopted());
                    preparedStatement.setString(10, petDataSample1.getUserName());
                } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName)) {
                    // Example sample data for food_inventory table
                    preparedStatement.setString(1, foodInventoryDataSample1.getFoodInventoryID());
                    preparedStatement.setString(2, foodInventoryDataSample1.getFoodInventory());
                    preparedStatement.setString(3, foodInventoryDataSample1.getUserName());
                }

                // Execute the update
                preparedStatement.executeUpdate();
                System.out.println("Sample data for '" + tableName + "' inserted successfully.");
            } else {
                System.out.println("Table '" + tableName + "' does not exist.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    
    private String generateInsertDataSQL(String tableName) {
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName)) {
            return "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES (?, ?, ?, ?)";
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName)) {
            return "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, isAdopted, userName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "INSERT INTO food_inventory (foodInventoryID, foodInventory, userName) VALUES (?, ?, ?)";
        }
        return "";
    }    
    
    private void setInsertDataParameters(PreparedStatement preparedStatement, String tableName, Object data) throws SQLException 
    {
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName) && data instanceof UserData) {
            UserData userData = (UserData) data;
            preparedStatement.setString(1, userData.getUserName());
            preparedStatement.setString(2, userData.getUserPassword());
            preparedStatement.setInt(3, userData.getUserDabloons());
            preparedStatement.setBoolean(4, userData.isUserHasPet());
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName) && data instanceof PetData) {
            PetData petData = (PetData) data;
            preparedStatement.setString(1, petData.getPetID());
            preparedStatement.setString(2, petData.getPetName());
            preparedStatement.setString(3, petData.getPetInstance());
            preparedStatement.setInt(4, petData.getPetLevel());
            preparedStatement.setInt(5, petData.getPetLevelXP());
            preparedStatement.setInt(6, petData.getPetHunger());
            preparedStatement.setInt(7, petData.getPetHygiene());
            preparedStatement.setInt(8, petData.getPetHappiness());
            preparedStatement.setBoolean(9, petData.getIsAdopted());
            preparedStatement.setString(10, petData.getUserName());
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName) && data instanceof FoodInventoryData) {
            FoodInventoryData foodInventoryData = (FoodInventoryData) data;
            preparedStatement.setString(1, foodInventoryData.getFoodInventoryID());
            preparedStatement.setString(2, foodInventoryData.getFoodInventory());
            preparedStatement.setString(3, foodInventoryData.getUserName());
        }
    }
    
    // REMOVE DATA METHODS
    
    /**
    * removeData method
    *
    * Parameters: String tableName, String primaryKey
    * Return: None
    * Description: Removes a record from the specified table using the primary key.
    */
   public void removeData(String tableName, String primaryKey) {
       if (!tableExists(dbManager.getConnection(), tableName)) {
           System.out.println("Table '" + tableName + "' does not exist.");
           return;
       }

       if (!primaryKeyExists(tableName, primaryKey)) {
           System.out.println("Record with the specified primary key does not exist in table '" + tableName + "'.");
           return;
       }

       String sqlRemove = generateRemoveDataSQL(tableName);

       try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlRemove)) {
           setRemoveDataParameters(preparedStatement, tableName, primaryKey);
           int rowCount = preparedStatement.executeUpdate();
           if (rowCount > 0) {
               System.out.println("Record with primary key '" + primaryKey + "' removed from table '" + tableName + "'.");
           } else {
               System.out.println("Record removal failed.");
           }
       } catch (SQLException e) {
           handleSQLException(e);
       }
   }

   private String generateRemoveDataSQL(String tableName) {
       return "DELETE FROM " + tableName + " WHERE primaryKey = ?";
   }

   private void setRemoveDataParameters(PreparedStatement preparedStatement, String tableName, String primaryKey) throws SQLException {
       preparedStatement.setString(1, primaryKey);
   }
   
   // UPDATE DATA METHODS
   
   public void updateData(String tableName, Object data) 
   {
        String sqlUpdate = generateUpdateDataSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlUpdate)) {
            if (tableExists(dbManager.getConnection(), tableName)) {
                setUpdateDataParameters(preparedStatement, tableName, data);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
//                    System.out.println("Data updated in table '" + tableName + "' successfully.");
                } else {
//                    System.out.println("No records were updated.");
                }
            } else {
//                System.out.println("Table '" + tableName + "' does not exist.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private String generateUpdateDataSQL(String tableName) 
    {
        // Create the SQL statement for updating data in the specified table
        // Adapt this part to your specific use case
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName)) {
            return "UPDATE user_data SET userDabloons=?, userHasPet=? WHERE userName=?";
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName)) {
            return "UPDATE pet SET petLevel=?, petLevelXP=?, petHunger=?, petHygiene=?, petHappiness=?, isAdopted=? WHERE userName=? AND isAdopted=?";
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "UPDATE food_inventory SET foodInventory=? WHERE userName=?";
        }
        return "";
    }

    private void setUpdateDataParameters(PreparedStatement preparedStatement, String tableName, Object data) throws SQLException 
    {
        // Set the parameters for the update statement based on the table name and data object
        // Adapt this part to your specific use case
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName) && data instanceof UserData) {
            UserData userData = (UserData) data;
            preparedStatement.setInt(1, userData.getUserDabloons());
            preparedStatement.setBoolean(2, userData.isUserHasPet());
            preparedStatement.setString(3, userData.getUserName());
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName) && data instanceof PetData) {
            PetData petData = (PetData) data;
            preparedStatement.setInt(1, petData.getPetLevel());
            preparedStatement.setInt(2, petData.getPetLevelXP());
            preparedStatement.setInt(3, petData.getPetHunger());
            preparedStatement.setInt(4, petData.getPetHygiene());
            preparedStatement.setInt(5, petData.getPetHappiness());
            preparedStatement.setBoolean(6, petData.getIsAdopted());
            preparedStatement.setString(7, petData.getUserName());
            preparedStatement.setBoolean(8, false);
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName) && data instanceof FoodInventoryData) {
            FoodInventoryData foodInventoryData = (FoodInventoryData) data;
            preparedStatement.setString(1, foodInventoryData.getFoodInventory());
            preparedStatement.setString(2, foodInventoryData.getUserName());
        }
    }
    
    /* tableExists method
    
    Parameters: Connection connection, String tableName
    Returns: boolean
    Description: If the tableName exists under the connection, true is returned else false as the table does not exist.
    */
    public boolean tableExists(Connection connection, String tableName) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName.toUpperCase(), null);
            return tables.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // An error occurred; assume the table doesn't exist
        }
    }
    
    private void handleSQLException(SQLException e) {
        Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }
    
    /**
    * primaryKeyExists method
    *
    * Parameters: String tableName, String primaryKey
    * Return: boolean
    * Description: Checks if a record with the specified primary key exists in the table.
    */
   public boolean primaryKeyExists(String tableName, Object data) 
   {
        String sqlCheckExists = generateCheckPrimaryKeySQL(tableName);
        if (!tableExists(dbManager.getConnection(), tableName)) {
            System.out.println("Table '" + tableName + "' does not exist.");
            return false;
        }

        else
        {
            try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlCheckExists)) 
            {
                setCheckPrimaryKeyParameters(preparedStatement, tableName, data);
                try (ResultSet rs = preparedStatement.executeQuery()) 
                {
                    return rs.next(); // Returns true if a record with the specified primary key exists
                }
            } catch (SQLException e) {
                handleSQLException(e);
                return false; // Default to false if an error occurs
            }
        }
    }
   
   private String generateCheckPrimaryKeySQL(String tableName) {
       if(ForeverHomeDB.USER_DATA_TABLE.equals(tableName))
       {
           return "SELECT 1 FROM " + tableName + " WHERE userName = ?";
       }
       else if(ForeverHomeDB.PET_TABLE.equals(tableName))
       {
           return "SELECT 1 FROM " + tableName + " WHERE petID = ?";
       }
       else if(ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName))
       {
           return "SELECT 1 FROM " + tableName + " WHERE foodInventoryID = ?";
       }
       return "";
    }

    private void setCheckPrimaryKeyParameters(PreparedStatement preparedStatement, String tableName, Object data) throws SQLException 
    {
        // Set the parameters for the check based on the table name and data object
        if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName) && data instanceof UserData) {
            UserData userData = (UserData) data;
            preparedStatement.setString(1, userData.getUserName());
        } else if (ForeverHomeDB.PET_TABLE.equals(tableName) && data instanceof PetData) {
            PetData petData = (PetData) data;
            preparedStatement.setString(1, petData.getPetID());
        } else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName) && data instanceof FoodInventoryData) {
            FoodInventoryData foodInventoryData = (FoodInventoryData) data;
            preparedStatement.setString(1, foodInventoryData.getFoodInventoryID());
        }
    }
    
    public void saveData(UserData playerData, PetData petData, FoodInventoryData foodInventoryData){
        if(playerData!= null)
        {
            this.updateData(ForeverHomeDB.USER_DATA_TABLE, playerData);
        }
        if(petData!= null)
        {
            this.updateData(ForeverHomeDB.PET_TABLE, petData);
        }
        if(foodInventoryData != null)
        {
            this.updateData(ForeverHomeDB.FOOD_INVENTORY_TABLE, foodInventoryData);
        }
    }
}
