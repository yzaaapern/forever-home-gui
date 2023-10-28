
package foreverhome;

/**
 *
 * @author AnnGa
 * Name: Ann Del Rosario
 * Student ID: 21143100
 */
public class ForeverHomeDB 
{
    /*
        INSTANCE AND CONSTANT VARIABLES
    */
    // Constants
    public static final String USER_DATA_TABLE = "user_data";
    public static final String PET_TABLE = "pet";
    public static final String FOOD_INVENTORY_TABLE = "food_inventory";
    
    // Instance variables
    private ForeverHomeDBOperations dbOperations;
    private ForeverHomeDBQueries dbQueries;
    private ForeverHomeDBManager dbManager;
    
    // Constructor
    public ForeverHomeDB(ForeverHomeDBOperations dbOperations, ForeverHomeDBQueries dbQueries)
    {
        this.setDBOperations(dbOperations);
        this.setDBQueries(dbQueries);
    }
    
    // Default constructor
    public ForeverHomeDB()
    {
        dbManager = ForeverHomeDBManager.getInstance();
        dbOperations = new ForeverHomeDBOperations(dbManager);
        dbQueries = new ForeverHomeDBQueries(dbManager);
    }

    // MAIN METHOD
    public static void main(String[] args) 
    {
//        ForeverHomeDBManager dbManager = ForeverHomeDBManager.getInstance();
//        ForeverHomeDBOperations dbOperations = new ForeverHomeDBOperations(dbManager);
//        ForeverHomeDBQueries dbQueries = new ForeverHomeDBQueries(dbManager);
//        
//        ForeverHomeDB db = new ForeverHomeDB(dbOperations, dbQueries);
//        
//        // Drop tables
////        db.getDBOperations().dropTable(ForeverHomeDB.PET_TABLE);
////        db.getDBOperations().dropTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
////        db.getDBOperations().dropTable(ForeverHomeDB.USER_DATA_TABLE);
//        
//        // Create tables
//        db.getDBOperations().createTable(ForeverHomeDB.USER_DATA_TABLE);
//        db.getDBOperations().createTable(ForeverHomeDB.PET_TABLE);
//        db.getDBOperations().createTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
//        
//        Animal pet1 = new Cat("catty");
//        PetData petData1 = new PetData(pet1, "anny");
//        db.getDBOperations().insertData(ForeverHomeDB.PET_TABLE, petData1);
//
//        Player user1 = new Player("Roxy");
//        UserData userData1 = new UserData(user1);
//        db.getDBOperations().insertData(ForeverHomeDB.USER_DATA_TABLE, userData1);
//
//        FoodInventoryData foodInventoryData1 = new FoodInventoryData(user1.getFoodInventory(), user1.getName());
//        db.getDBOperations().insertData(ForeverHomeDB.FOOD_INVENTORY_TABLE, foodInventoryData1);
//        
//        // Queries
//        System.out.println(dbQueries.getAll(ForeverHomeDB.USER_DATA_TABLE));
//        System.out.println(dbQueries.getAll(ForeverHomeDB.PET_TABLE));
//        System.out.println(dbQueries.getAll(ForeverHomeDB.FOOD_INVENTORY_TABLE));
//        System.out.println(dbQueries.getUserByUserName("anny"));
//        System.out.println(dbQueries.getPetByPetNameAndUserName("anny", "Pookie"));
//        System.out.println(dbQueries.getUserByFoodInventoryID("ecf77bba-0ca2-495c-b6f9-b6fb6fdb3aa6"));
//        System.out.println(dbQueries.getPetByPetID("03a5f174-5ca8-4f99-a46b-380e5c195837"));
//        System.out.println(dbQueries.getPetByPetID("843ff3bf-df2a-47f7-9542-0fe2664dc404"));
//        System.out.println(dbQueries.getFoodInventoryByUserName("anny"));
//        
//        // Closing connection
//        dbManager.closeConnection();
        
    }
    
    
    // METHODS
    
    public void dbSetUp()
    {
        try
        {
//            dbOperations.dropTable(ForeverHomeDB.USER_DATA_TABLE);
//            dbOperations.dropTable(ForeverHomeDB.PET_TABLE);
//            dbOperations.dropTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
            
            // Create tables
            dbOperations.createTable(ForeverHomeDB.USER_DATA_TABLE);
            dbOperations.createTable(ForeverHomeDB.PET_TABLE);
            dbOperations.createTable(ForeverHomeDB.FOOD_INVENTORY_TABLE);
        }
        catch(Throwable e)
        {
            System.out.println(e);
            System.out.println("error");
        }
    }
    
    /*
        getDBOperations method
    Parameters: None
    Returns: ForeverHomeOperations dbOperations
    Description: Returns the private dbOperations object
    */
    public ForeverHomeDBOperations getDBOperations() {
        return dbOperations;
    }

    /*
        setDBOperations method
    Parameters: ForeverHomeDBOperations dbOperations
    Returns: None
    Description: Sets the global dbOperations object to the input dbOperations object
    */
    public void setDBOperations(ForeverHomeDBOperations dbOperations) {
        this.dbOperations = dbOperations;
    }

    /*
        getDBQueries method
    Parameters: None
    Returns: ForeverHomeDBQueries dbQueries
    Description: Returns the private dbQueries object
    */
    public ForeverHomeDBQueries getDBQueries() {
        return dbQueries;
    }

    /*
        setDBQueries method
    Parameters: ForeverHomeDBQueries dbQueries
    Returns: None
    Description: Sets the the global dbQueries object to the input dbQueries object
    */
    public void setDBQueries(ForeverHomeDBQueries dbQueries) {
        this.dbQueries = dbQueries;
    }
    
    public ForeverHomeDBManager getDBManager(){
        return dbManager;
    }
            
}
