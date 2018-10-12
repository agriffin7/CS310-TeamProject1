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
        private int shiftId; //check
        private String badgeId; //check
        private String punchId;
        private int terminalId; //check
        private int punchType; //check
        private String punchDescription;
        private String simpleDateFormat; //check
        private String eventData;
        
        public Punch(int terminalId, String badgeId, int shiftId, long originalts, int punchType) {
            originalTime = new GregorianCalendar();
            adjustedTime = new GregorianCalendar();
            this.terminalId = terminalId;
            this.badgeId = badgeId;
            this.shiftId = shiftId;
            this.punchType = punchType;
            
            simpleDateFormat = new SimpleDateFormat("EEE MM/dd/YYYY HH:mm:ss").format(originalTime.getTime()).toUpperCase();
        }
        
        
        
}
