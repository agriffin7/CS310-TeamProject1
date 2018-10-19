package project.team.cs310;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.*;

    //EXAMPLE CONSTRUCTORS START
    public class TASDatabase {
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
                String username = "tasuser";
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
        
        
        //add new queries to the database
        
        /*constructors for creating Punch Objects*/
        //set punch object
        public void setPunch(){
        }
        
        
        //insert punch object into database
        public int insertPunch(Punch p) throws SQLException{
            int result =0, key=0;
            ResultSet keys = null;
            
            //get punch stuff
            int terminalID = p.getTerminalID();
            int punchType = p.getPunchType();
            Badge badgeID = p.getBadgeID();
            String b = badgeID.toString();
            Timestamp originalTimeStamp = p.getOriginalTime();
             
            //prepare query
            String sql = ("INSERT INTO event (BadgeID, originalTimeStamp, punchType, TerminalID) VALUES(?,?,?,?)");
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            //building the prepared statemnet 
            ps.setString(1, b);
            ps.setString(2, (new SimpleDateFormat ("yyy-MM-dd HH:mm-ss")).format(originalTimeStamp.getTime()));
            ps.setInt(3, terminalID);
            ps.setInt(4, punchType);
            
            //getting result frome executed query
            result = ps.executeUpdate();
                    
            //if data exists in the row
            if(result == 1){
                keys = ps.getGeneratedKeys();
            }
            
            //if there is more data
            if(keys.next()){
               key = keys.getInt(1);
            }
            
            return key;
        }
        
        
        //get punch object
        public Punch getPunch(int punchID){
            Connection();
                
        Punch p = null;
        try{
        //prepare the query
        query = "SELECT * FROM punch WHERE id = " + punchID;
        
        //execute the query
        try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next())
                {
                    // ID
                    int Id = rs.getInt("id");
                    
                    // Terminal ID
                    int terminalId = rs.getInt("terminalid");
                    
                    // Bagde ID
                    String badgeId = rs.getString("badgeid");
                    
                    //Punch type
                    int punchType = rs.getInt("punchtypeid");
                    
                    //Time Stamp
                    Timestamp tStamp = rs.getTimestamp("originaltimestamp");
                    
                    Badge b = getBadge(badgeId);
                    
                    //create a punch
                    p = new Punch(b, terminalId, punchType, tStamp);
                   
                    //set original time with setter
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
     return p;   
    }
        
       
        
        
        //set badge object
        public void setBadge(){
            
        }
        
        
        
        //get badge object
        public Badge getBadge(String ID){
            Connection();
            
            try{
                int BadgeID = 0;
                
                //prepare the query
                query = "SELECT * FROM badge";
                pstSelect = conn.prepareStatement(query);
            
                //execute the query
                System.out.println("Submitting Query...");
                boolean hasresults = pstSelect.execute();
            
                //get results
                System.out.println("Getting Results...");
            
                while (hasresults || pstSelect.getUpdateCount() != -1){
                
                    if (hasresults){
                    
                        //get result metadata
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        int columnCount = metadata.getColumnCount();
                    
                        /*get the key data*/
                        for (int i = 1; i <= columnCount; i++){
                            key = metadata.getColumnName(i);
                        }
                        
                        /*get data*/
                        while(resultset.next()){
                            /*begin next ResultRow set*/
                            
                            /*loop through ResultSet Columns, print values*/
                            //declare a string array
                            String[] ValueArray = new String[2];
                            
                            
                            for (int i = 1; i <= columnCount; i++){
                                value = resultset.getString(i);
                                
                                //add them to the array
                                ValueArray[i-1] = value;
                                
                                if(ValueArray[0].equals(ID)){
                                   if (ValueArray[1] == value){
                                       //declare a new badge
                                       Badge badge = new Badge();
                                       
                                      badge.setId(ValueArray[0]);
                                      badge.setDescription(ValueArray[1]);
                                      return badge; 
                                    }
                                }   
                            }
                        }
                    }
                    else{
                        int resultCount = pstSelect.getUpdateCount();
                    
                        if (resultCount == -1){
                            break;
                        }
                    }
                    //check for more data
                    hasresults = pstSelect.getMoreResults();
                }
                
            }
            catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            return null;
        }
        
        
        
        
        /*constructors for shift rules*/
        
        //get shifts 
        
        //this does ID in the database
        public Shift getShift(int shiftID){
        Connection();
            
            try{
                
                //prepare the query
                query = "SELECT * FROM shift";
                pstSelect = conn.prepareStatement(query);
            
                //execute the query
                System.out.println("Submitting Query...");
                boolean hasresults = pstSelect.execute();
            
                //get results
                System.out.println("Getting Results...");
            
                while (hasresults || pstSelect.getUpdateCount() != -1){
                
                    if (hasresults){
                    
                        //get result metadata
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        int columnCount = metadata.getColumnCount();
                    
                        /*get the key data*/
                        for (int i = 1; i <= columnCount; i++){
                            key = metadata.getColumnName(i);
                        }
                        
                        /*get data*/
                        while(resultset.next()){
                            /*begin next ResultRow set*/
                            
                            /*loop through ResultSet Columns, print values*/
                            
                            //declare a string Array 
                            String ValueArray[] = new String[10];
                            int TotalI = 0;
                            
                            for (int i = 1; i <= columnCount; i++){
                                TotalI++;
                                value = resultset.getString(i);
                                
                                // add them to the array
                                ValueArray[i-1] = value;
                                
                                
                                if (TotalI >= columnCount){
                                    
                                
                                //check the ints
                                if(shiftID == Integer.parseInt(ValueArray[0])){
                                    //declare a new shift object
                                    
                                    Shift shift = new Shift();
                                    //fill in shift object
                                    shift.setShiftid(Integer.parseInt(ValueArray[0]));
                                    shift.setDescription(ValueArray[1]);
                                    
                                    Time StartTime = Time.valueOf(ValueArray[2]);
                                    Time StopTime = Time.valueOf(ValueArray[3]);
                                    
                                    
                                    shift.setTimeStart(StartTime);
                                    shift.setTimeStop(StopTime);
                                    //caculate the time difference
                                    long TimeDiff = (StopTime.getTime() - StartTime.getTime())/1000/60;

                                    
                                    //add it to object
                                    shift.setTimeInterval(TimeDiff);
                                    
                                    
                                    shift.setInterval(Integer.parseInt(ValueArray[4]));
                                    shift.setGracePeriod(Integer.parseInt(ValueArray[5]));
                                    shift.setDock(Integer.parseInt(ValueArray[6]));
                                    
                                    Time LunchStart = Time.valueOf(ValueArray[7]);
                                    Time LunchStop = Time.valueOf(ValueArray[8]);
                                    
                                    shift.setLunchStart(LunchStart);
                                    shift.setLunchStop(LunchStop);
                                    //caculate difference 
                                    long BreakDiff = (LunchStop.getTime() - LunchStart.getTime())/1000/60;
                                    
                                    //add it to object
                                    shift.setLunchBreak(BreakDiff);
                                    
                                    
                                    shift.setLunchDeduct(Integer.parseInt(ValueArray[9]));
                                    
                                    //System.out.println(shift.toString());
                                    return shift;
                                    
                                }
                                
                                }
                                
                                
                            }
                        }
                    }
                    else{
                        int resultCount = pstSelect.getUpdateCount();
                    
                        if (resultCount == -1){
                            break;
                        }
                    }
                    //check for more data
                    hasresults = pstSelect.getMoreResults();
                }
                
            }
            catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            return null;
        }
        
        //this does badges
        public Shift getShift (Badge badge){
           try{
                
                //prepare the query
                query = "SELECT * FROM employee";
                pstSelect = conn.prepareStatement(query);
            
                //execute the query
                System.out.println("Submitting Query...");
                boolean hasresults = pstSelect.execute();
            
                //get results
                System.out.println("Getting Results...");
                
            String CODECHECK = "ERROR";
                while (hasresults || pstSelect.getUpdateCount() != -1){
                
                    if (hasresults){
                    
                        //get result metadata
                        resultset = pstSelect.getResultSet();
                        metadata = resultset.getMetaData();
                        int EmployeecolumnCount = metadata.getColumnCount();
                    
                        /*get the key data*/
                        for (int i = 1; i <= EmployeecolumnCount; i++){
                            key = metadata.getColumnName(i);
                        }
                        
                        /*get data*/
                        while(resultset.next()){
                            
                            //get the badge ID
                            String EmployeeArray[] = new String[10];
                            for (int i = 1; i <= EmployeecolumnCount; i++){
                                value = resultset.getString(i);
                                //add the data to employee array
                                EmployeeArray[i-1] = value;
                                
                                CODECHECK = resultset.getString(10);
                                
                            }
                            //CHECK FOR A MATCH IN BADGEID
                                if (EmployeeArray[9].equals(CODECHECK)){
                                    
                                    if (EmployeeArray[0].equals(badge.getId())){
                                       
                                    int SHIFTID = Integer.parseInt(EmployeeArray[6]);
                                    
                                    Shift shift = getShift(SHIFTID);
                                    System.out.println(shift.toString());
                                    return shift;
                                    }
                                }
                            
                        }
                    }
                    else{
                        int resultCount = pstSelect.getUpdateCount();
                    
                        if (resultCount == -1){
                            break;
                        }
                    }
                    //check for more data
                    hasresults = pstSelect.getMoreResults();
                }
                
            }
            catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            //remember to change these once finished
            return null;
        }
        
        //close the connection
        public void Close(){
            //is ok i guess
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
