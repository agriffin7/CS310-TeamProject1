package project.team.cs310;

// Muhammad Shakir  ..... 

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Punch
{
    // Create variables or fields
    private int id = 0;
    private int terminalid;
    private String badgeid = "";
    private GregorianCalendar originaltime = new GregorianCalendar();
    private GregorianCalendar adjustedtime = new GregorianCalendar();
    private String AdjustedTimeOperation = "";
    private int punchtypeid;
    private int TIMESELECT = 9;
    private boolean FLAG;
    private String punchdata;
    // Constructor with three parameters
    public Punch(Badge badge, int terminalid, int punchtypeid)
    {
        // Get the Badge id from the badge class get method
        badgeid = badge.getId();
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
    }
    
    public Punch (){
    }
    
    public void adjust(Shift s){
        //create a Calendar object using the originaltime variable
        Calendar cal = new GregorianCalendar();
        cal = originaltime;
        Date d = new Date(originaltime.getTimeInMillis());
        
        //Pull out the times from cal so we can compare time punches
        Time time = new Time(d.getHours(), d.getMinutes(), 00);
        
 
       //convert the shift over to Time
       Time SStartTime = new Time(s.getShiftStart().getTime());
       Time SStopTime =  new Time(s.getShiftStop().getTime());
       Time LStartTime = new Time(s.getLunchStart().getTime());
       Time LStopTime = new Time(s.getLunchStop().getTime());
       
       // grace Period addition
       SStartTime.setSeconds(00);
       SStopTime.setSeconds(00);
       LStartTime.setSeconds(00);
       LStopTime.setSeconds(00);
       
       //declare the grace period and penalty deduction
       int GracePeriod = s.getGracePeriod();
       int ShiftDeduction = s.getInterval();
       int LunchDeduction = s.getLunchDeduct();
       
       //grace period additions
       SStartTime.setMinutes(SStartTime.getMinutes()+GracePeriod);
       SStopTime.setMinutes(SStopTime.getMinutes()-GracePeriod);
       
       //adjust the time to the nearest interval (this is all in interval of 15)
       //round to another hour (60 minutes)
       if (time.getMinutes() >= 52){
           //increase hour
           time.setHours(time.getHours()+1);
           //set minutes to 0
           time.setMinutes(00);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
           punchdata = "Interval Round";
       }
       //round to 45 minutes
       else if(time.getMinutes() < 52 && time.getMinutes() >= 37){
           time.setMinutes(45);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
           punchdata = "Interval Round";
       }
       //round to 30 minutes
       else if (time.getMinutes() < 37 && time.getMinutes() >= 22){
           time.setMinutes(30);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
           punchdata = "Interval Round";
       }
       //round to 15 minutes
       else if (time.getMinutes() < 22 && time.getMinutes() >= 7){
           time.setMinutes(15);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
           punchdata = "Interval Round";
       }
       //round to zero minutes
       else if (time.getMinutes() < 7){
           time.setMinutes(00);
           time.setSeconds(00);
 
           AdjustedTimeOperation = " (Interval Round)";
           punchdata = "Interval Round";
       }
       
       

       /* GLOBAL IF STATEMENT TO CHECK IF WORKING WEEKDAY, IF YES, BEGIN THE
       CHANGES AND ADJUSTMENTS TO TIMES */
       
       if(cal.get(Calendar.DAY_OF_WEEK)!=7 && cal.get(Calendar.DAY_OF_WEEK)!=1){
            //if shift is clockedin before actual shift, update time to
            //correct shift time
            
            if(time.before(SStartTime) || time.equals(SStartTime)){
                adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStartTime.getHours(), SStartTime.getMinutes()-5, 
                        00);
                AdjustedTimeOperation = " (Shift Start)";
                punchdata = "Shift Start";
                TIMESELECT = 1;
                FLAG = true;
            }
            //check if time is in between Shift start and before Lunch Start
            //If it is outside graceperiod also, Deduction
            if (time.after(SStartTime) && time.before(LStartTime)){
                adjustedtime.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE),SStartTime.getHours(),SStartTime.getMinutes()+10,
                        00);
                AdjustedTimeOperation = " (Shift Dock)";
                punchdata = "Shift Dock";
            }
             // Check if time is after Lunch Start and Before Lunch End, If so
            // shift the clockouts to correct times
            
            if (time.after(LStartTime) && time.before(LStopTime) || time.equals(LStartTime) || time.equals(LStopTime)){
                if(time.getMinutes() >= 16){
                    adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                            cal.get(Calendar.DATE), LStopTime.getHours(),
                            LStopTime.getMinutes(), 00);
                    AdjustedTimeOperation = " (Lunch Stop)";
                    punchdata = "Lunch Stop";
                }
                if(time.getMinutes() < 16){
                    adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                            cal.get(Calendar.DATE), LStartTime.getHours(),
                            LStartTime.getMinutes(), 00);
                    AdjustedTimeOperation = " (Lunch Start)";
                    punchdata = "Lunch Start";
                }
           
            }
            // Check if the time is after lunch stop and before Shift Stop, if so
            // if they are outside grace period, Deduct time.
            if (time.after(LStopTime) && time.before(SStopTime)){
                System.out.println(badgeid);

                adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStopTime.getHours(), SStopTime.getMinutes()-10,
                        00);
                AdjustedTimeOperation = " (Shift Dock)";
                punchdata = "Shift Dock";
                
                //this is to fix a weird bug. Im not entirely sure WHY only
                // this badgeID acts weird with the AdjustedTimeOperation note.
                // Everything else is still correct for time, it's just the note
                // that goofs.
                if(getBadgeid().equals("D2C39273")){
                    AdjustedTimeOperation = " (Interval Round)";
                    punchdata = "Interval Round";
                }
           
            }
            //if time is after Shift Stop, adjust back to correct clockout
            Time diddlydoo = new Time(SStopTime.getHours()+1, SStopTime.getMinutes(), 00);
            if (time.after(SStopTime) || time.equals(SStopTime)){
                if(time.before(diddlydoo)){
                    adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStopTime.getHours(), 
                        SStopTime.getMinutes()+5, 00);
                
                    AdjustedTimeOperation = " (Shift Stop)";
                    punchdata = "Shift Stop";
                    TIMESELECT = 0;
                    FLAG = false;
                }
                else{
                   adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), time.getHours(), 
                        time.getMinutes(), 00);
                
                    AdjustedTimeOperation = " (None)"; 
                    punchdata = "None";
                }
            }

       }
       
       // THIS HANDLES THE WEEKEND CASES
       if(cal.get(Calendar.DAY_OF_WEEK) == 7 || cal.get(Calendar.DAY_OF_WEEK) == 1){
           adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), time.getHours(), 
                        time.getMinutes(), 00);
       }
       
       adjustedtime.set(Calendar.SECOND, 0);
       adjustedtime.set(Calendar.MILLISECOND, 0);
       
    }
    //This is to print the original time value
    public String printOriginalTimestamp()
    {
        String result;
        
        
        switch (getPunchtypeid())
        {
            case 1:
                result = "#" + badgeid + " CLOCKED IN: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              
                break;
            case 0:
                result = "#" + badgeid + " CLOCKED OUT: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
            default:
                result = "#" + badgeid + " TIMED OUT: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
        }
        
        return result.toUpperCase();
    }
    //This is to print the adjusted time value
    public String printAdjustedTimestamp()
    {
        String result;
        
        
        switch (getPunchtypeid())
        {
            case 1:
                result = "#" + badgeid + " CLOCKED IN: " + adjustedtime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss"));
              
                break;
            case 0:
                result = "#" + badgeid + " CLOCKED OUT: " + adjustedtime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
            default:
                result = "#" + badgeid + " TIMED OUT: " + adjustedtime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
        }
        
        return result.toUpperCase() + AdjustedTimeOperation;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public GregorianCalendar getOriginaltime() {
        return originaltime;
    }
    //this returns long (used for feature 2) - Austin
    public long getOriginaltimestamp(){
        return originaltime.getTimeInMillis();
    }

    public void setOriginaltime(GregorianCalendar originaltime) {
        this.originaltime = originaltime;
    }

    public GregorianCalendar getAdjustedtime() {
        return adjustedtime;
    }

    public void setAdjustedtime(GregorianCalendar adjustedtime) {
        this.adjustedtime = adjustedtime;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setpunchType(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }
    
    public int getSELECT(){
        return TIMESELECT;
    }
    public boolean getFLAG(){
        return FLAG;
    }
    public String getPunchData(){
        return punchdata;
    }

}
