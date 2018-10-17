package project.team.cs310;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class Punch {
     /*Punch will contain information about the original and adjusted timestamps. 
        These objects will be created and populated by the database class, 
        which will retrieve the necessary data from the database. 
        This may be existing punches, or new punches that have not yet been added to the database. 
        Newly-created punches do not yet have a unique ID (since this is assigned by the database),
        and their original timestamps should be initialized to the current time as reported by the system clock.*/
        public Punch(){}
        private GregorianCalendar originalTime; //check
        private GregorianCalendar adjustedTime; //check
        private int ID;
        private int shiftID; //in constructor
        private String badgeID; //in constructor
        private int terminalID; //in constructor
        private int punchType; //in constructor
        private String simpleDateFormat; //check
        
        public Punch(int ID, int shiftID, String badgeID, int terminalID, int punchType, long tStamp) {
            originalTime = new GregorianCalendar();
            adjustedTime = new GregorianCalendar();
            this.shiftID = shiftID;
            this.badgeID = badgeID;
            this.terminalID = terminalID;
            this.punchType = punchType;
            long originaltimeStamp = tStamp;
            originalTime.setTimeInMillis(originaltimeStamp);
            originalTime = new GregorianCalendar();
            this.ID = ID;
            
            simpleDateFormat = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(originalTime.getTime()).toUpperCase();
        }

    public GregorianCalendar getOriginalTime() {
        return originalTime;
    }

    public void setOriginalTime(GregorianCalendar originalTime) {
        this.originalTime = originalTime;
    }

    public GregorianCalendar getAdjustedTime() {
        return adjustedTime;
    }

    public void setAdjustedTime(GregorianCalendar adjustedTime) {
        this.adjustedTime = adjustedTime;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return ID;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public String getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(String badgeID) {
        this.badgeID = badgeID;
    }

    public int getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(int terminalID) {
        this.terminalID = terminalID;
    }

    public int getPunchType() {
        return punchType;
    }

    public void setPunchType(int punchType) {
        this.punchType = punchType;
    }

    public String getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(String simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }
    
    public String printOriginalTimestamp(){
        return "#" + badgeID + " CLOCKED IN: " + originalTime;
    }
        
}
