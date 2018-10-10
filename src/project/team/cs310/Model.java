package project.team.cs310;
import java.sql.*;


public class Model {
   
    //EXAMPLE CONSTRUCTORS START
    class TASDatabase {
        /*This class represents the application's connection to the TAS database.
        It will use JDBC and the MySQL driver, which is usually included with NetBeans and other IDEs.
        The constructor for this class should open a connection to the database, and the class should provide a
        "close()" method so that this connection can be closed in an orderly fashion.
        (See the lecture notes on JDBC for more information and examples on Java database programming.)
        This class should also provide methods which create the objects (Badge, Shift, and Punch).*/

        
        Connection conn = null;
        PreparedStatement pstSelect = null, pstUpdate = null;
        ResultSet resultset = null;
        ResultSetMetaData metadata = null;
        
        String query, key, value;
        
        
        //CONSTRUCTORS
        //create and implement connection to databse
        public void Connection() {
            
            try{
        
                //Identify the server
                String server = ("jdbc:mysql://localhost/tas");
                String username = "tasuer";
                String password = "WarRoomF";
                System.out.println("Connecting to " + server + "...");
                
                //load the MYSQL JDBC Driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                //open the connection
                conn = DriverManager.getConnection(server, username,password);
                
                //test connection
                if (conn.isValid(0)) {
                    System.out.println("Connected Successfully!");
                }
               
            }
        
            catch(Exception ex){
                System.out.println(ex);
            } 
        }
        
        
        //Pull queries from the database
        
        
        /*constructors for creating Punch Objects*/
        //set punch object
        public void setPunch(){
            
        }
        //get punch object
        public Punch getPunch(int punchInt){
            
            
             //remember to change these once constructors are finished
            return null;
        }
        
        /*constructors for creating Badge Objects*/
        //set badge object
        public void setBadge(){
            
        }
        //get badge object
        public Badge getBadge(String ID){
            
            
            //remember to change these once constructors are finished
            return null;
        }
        
        
        /*constructors for shift rules*/
        
        //get shift (gets the rulesets for the badge?)
        public Shift getShift(Badge b){
            
            
            //remember to change these once constructors are finished
            return null;
        }
        
        
    }

    class TASLogic {
        /*TASLogic will implement:
        The business logic (such as computing the total minutes accrued by an employee within a single day)
        The application logic (such as converting an ordered list of Punch objects to JSON format)
        This class will initially be empty, so !!THERE IS NOTHING TO DO FOR THIS IN FEATURE !!; 
        the business and application logic will be added as static methods during subsequent features.*/
        
        
    }

    class Badge {
        
        private String description;
        private int id;
        
        public Badge(int id, String d){
            description = d;
            this.id = id;
        }
    
    @Override
    public String toString(){
        return Integer.toString(id) + " " + description;
    }
    // set badge description
    public void setDescription(String description){
        this.description = description;
    }
    //set badge ID
    public void setId(int id) {    
        this.id = id;
    }
    //get badge descrption
    public String getDescription() {
        return description;
    }
    //get badge ID
    public int getId() {
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
        
        /*instance field*/
         private int shiftid, interval, dock, gracePeriod, lunchDeduct;
         private String description;
         private long lunchBreak;
         private Time timeStart = new Time(0);
         private Time timeStop = new Time(0);
         private Time lunchStart = new Time(0);
         private Time lunchStop = new Time(0);
        /*instance field*/
         
         public Shift(){
             //empty constructor
         }
         
         public Shift(int shiftid, String description, Time timeStart, Time timeStop, int interval, int gracePeriod, int dock, Time lunchStart, Time lunchStop, long lunchBreak, int lunchDeduct){
            this.shiftid = shiftid;
            this.description = description;
            this.timeStart = timeStart;
            this.timeStop = timeStop;
            this.interval = interval;
            this.gracePeriod = gracePeriod;
            this.dock = dock;
            this.lunchStart = lunchStart;
            this.lunchStop = lunchStop;
            this.lunchBreak = lunchBreak;
            this.lunchDeduct = lunchDeduct;
            
         }
         
         /*GETTER METHODS START*/
        public int getShiftid() {
            return shiftid;
        }
         
        public String getDescription() {
            return description;
        }
        
        public Time getTimeStart(){
            return timeStop;
        }
        
        public Time getTimeStop(){
            return timeStop;
        }
        
        public long getInterval() {
            return interval;
        }
        
        public long getGracePeriod() {
            return gracePeriod;
        }
        
        public long getDock(){
            return dock;
        }
        
        public Time getLunchStart() {
            return lunchStart;
        }

        public Time getLunchStop() {
            return lunchStop;
        }
        
        public long getLunchBreak(){
            return lunchBreak;
        }
        
        public long getLunchDeduct() {
            return lunchDeduct;
        }
        
        /*GETTER METHODS END*/
        

        /*SETTER METHODS START*/
        public void setShiftid(int shiftid) {
            this.shiftid = shiftid;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public void setTimeStart(Time timeStart) {
            this.timeStart = timeStart;
        }

        public void setTimeStop(Time timeStop) {
            this.timeStop = timeStop;
        }
        
        public void setInterval(int interval) {
            this.interval = interval;
        }
        
        public void setGracePeriod(int gracePeriod) {
            this.gracePeriod = gracePeriod;
        }

        public void setLunchStart(Time lunchStart) {
            this.lunchStart = lunchStart;
        }

        public void setLunchStop(Time lunchStop) {
            this.lunchStop = lunchStop;
        }

        public void setLunchBreak(long lunchBreak) {
            this.lunchBreak = lunchBreak;
        }

        public void setLunchDeduct(int lunchDeduct) {
            this.lunchDeduct = lunchDeduct;
        }

        public void setDock(int dock) {
            this.dock = dock;
        }
        /*SETTER METHODS END*/
        
        @Override
        public String toString(){
            return Integer.toString(shiftid)+" "+description;
        }
       
        
    }
}
