package project.team.cs310;
import java.sql.*;



//I removed the constructors and instead created classes for the objects.
//I'll be implementing constructors for these classes in our meeting today.
// - Austin


public class Model {
    //EXAMPLE INSTANCE FIELD START
    private int shiftID, terminalid, punchtypeid, LunchBreak, adjustedTimeStamp;
    private Badge badge;
    //EXAMPLE INSTANCE FIELD START
}
    
    
    //EXAMPLE CONSTRUCTORS START
    class TASDatabase {
        /*This class represents the application's connection to the TAS database.
        It will use JDBC and the MySQL driver, which is usually included with NetBeans and other IDEs.
        The constructor for this class should open a connection to the database, and the class should provide a
        "close()" method so that this connection can be closed in an orderly fashion.
        (See the lecture notes on JDBC for more information and examples on Java database programming.)
        This class should also provide methods which create the objects (Badge, Shift, and Punch).*/
        
        
    }

    class TASLogic {
        /*TASLogic will implement:
        The business logic (such as computing the total minutes accrued by an employee within a single day)
        The application logic (such as converting an ordered list of Punch objects to JSON format)
        This class will initially be empty, so !!THERE IS NOTHING TO DO FOR THIS IN FEATURE 1!!; 
        the business and application logic will be added as static methods during subsequent features.*/
        
        
    }
    
//    class Badge {
        /*Badge class will contain information from the database about a single employee badge.
        The Badge class will be used to represent already-existing badge rulesets from the database.*/
        
        
//}

    class Badge 
    {
        private String description;
        private int id;
        public Badge(int id, String d) 
        {
            description = d;
            this.id = id;
        }
    
    @Override
    public String toString()
    {
        return Integer.toString(id) + " " + description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public void setId(int id) 
    {    
        this.id = id;
    }

    public String getDescription() 
    {
        return description;
    }

    public int getId() 
    {
        return id;
    }
}
    class Punch {
         /*Punch will contain information about the original and adjusted timestamps. 
        These objects will be created and populated by the database class, 
        which will retrieve the necessary data from the database. 
        This may be existing punches, or new punches that have not yet been added to the database. 
        Newly-created punches do not yet have a unique ID (since this is assigned by the database),
        and their original timestamps should be initialized to the current time as reported by the system clock.*/
        
        
        
    }

    class Shift {
        /*Shift will contain the shift ruleset
        (that is, the starting time, stopping time, and other parameters of a single shift).*/
        
        
    }

