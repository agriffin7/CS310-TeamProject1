package project.team.cs310;


public class Model {
    //EXAMPLE INSTANCE FIELD START
    private int shiftID, terminalid, punchtypeid, LunchBreak, adjustedTimeStamp;
    private Badge badge;
    //EXAMPLE INSTANCE FIELD START
    
    
    //EXAMPLE CONSTRUCTORS START
    public TASDatabase(){
        /*This class represents the application's connection to the TAS database.
        It will use JDBC and the MySQL driver, which is usually included with NetBeans and other IDEs.
        The constructor for this class should open a connection to the database, and the class should provide a
        "close()" method so that this connection can be closed in an orderly fashion.
        (See the lecture notes on JDBC for more information and examples on Java database programming.)
        This class should also provide methods which create the objects (Badge, Shift, and Punch).*/
    }

    public TASLogic(){
        /*TASLogic will implement:
        The business logic (such as computing the total minutes accrued by an employee within a single day)
        The application logic (such as converting an ordered list of Punch objects to JSON format)
        This class will initially be empty, so !!THERE IS NOTHING TO DO FOR THIS IN FEATURE 1!!; 
        the business and application logic will be added as static methods during subsequent features.*/
    }
    
    public Badge(){
        /*Badge class will contain information from the database about a single employee badge.
        The Badge class will be used to represent already-existing badge rulesets from the database.*/
    }
    
    public void Shift(int LunchBreak){
        /*Shift will contain the shift ruleset
        (that is, the starting time, stopping time, and other parameters of a single shift).*/
    }
    
    public void Punch(Badge badge, int terminalid, int punchtypeid){
        /*Punch will contain information about the original and adjusted timestamps. 
        These objects will be created and populated by the database class, 
        which will retrieve the necessary data from the database. 
        This may be existing punches, or new punches that have not yet been added to the database. 
        Newly-created punches do not yet have a unique ID (since this is assigned by the database),
        and their original timestamps should be initialized to the current time as reported by the system clock.*/
    }
    //EXAMPLE CONSTRUCTORS END
            
            
    //EXAMPLE METHODS OF TASDatabase STARTS
    public int getPunch(int punchtypeid){
        /*Method for creating an object of the Punch class.
         This method should query the database, retrieve the punch data from the database,
        populate a new Punch object, and then return this object to the caller.*/
        return Punch punch;
    }
    public int getBadge(Badge badge){
        /*Method for creating an object of the Badge class.
        This method should query the database, retrieve the badge data from the database,
        populate a new badge object, and then return this object to the caller.*/
        return Badge badge;
    }
    public int getShift(int shiftID){
        /*Method for creating an object of the Shift class.
        This method should query the database, retrieve the shift data from the database,
        populate a new shift object, and then return this object to the caller.
        This version of getShift accepts a !!shift ID as a parameter!!*/
        return Shift shift;
    }
    public int getShift(){
        /*Method for creating an object of the Shift class.
        This method should query the database, retrieve the shift data from the database,
        populate a new shift object, and then return this object to the caller.
        This version of getShift accepts a !!Badge object!! as a parameter!!*/
        return Shift shift;
    }
    public close(){
        
    }
    //EXAMPLE METHODS OF TASDatabase END
}
