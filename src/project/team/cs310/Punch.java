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
        Time time = new Time(d.getHours(), d.getMinutes(), d.getSeconds());
        
        
        //now we get the shift information and declare those as longs
       long shiftStart = s.getShiftStart().getTime();
       long shiftStop = s.getShiftStop().getTime();
       long lunchStart = s.getLunchStart().getTime();
       long lunchStop = s.getLunchStop().getTime();
       
       //convert those over to GregorianCalendar
       Time SStartTime = new Time(shiftStart);
       Time SStopTime =  new Time(shiftStop);
       Time LStartTime = new Time(lunchStart);
       Time LStopTime = new Time(lunchStop);
       
       // grace Period addition
       
       
       //declare the grace period and penalty deduction
       int GracePeriod = s.getGracePeriod();
       int ShiftDeduction = s.getInterval();
       int LunchDeduction = s.getLunchDeduct();
       
       //grace period additions
       SStartTime.setMinutes(SStartTime.getMinutes()+GracePeriod);
       
       //adjust the time to the nearest interval (this is all in interval of 15)
       //round to another hour (60 minutes)
       if (time.getMinutes() >= 53){
           //increase hour
           time.setHours(time.getHours()+1);
           //set minutes to 0
           time.setMinutes(00);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
       }
       //round to 45 minutes
       else if(time.getMinutes() < 53 && time.getMinutes() >= 38){
           time.setMinutes(45);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
       }
       //round to 30 minutes
       else if (time.getMinutes() < 38 && time.getMinutes() >= 23){
           time.setMinutes(30);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
       }
       //round to 15 minutes
       else if (time.getMinutes() < 23 && time.getMinutes() >= 8){
           time.setMinutes(15);
           time.setSeconds(00);
           AdjustedTimeOperation = " (Interval Round)";
       }
       //round to zero minutes
       else if (time.getMinutes() < 8){
           time.setMinutes(00);
           time.setSeconds(00);
 
           AdjustedTimeOperation = " (Interval Round)";
       }
       

       /* GLOBAL IF STATEMENT TO CHECK IF WORKING WEEKDAY, IF YES, BEGIN THE
       CHANGES AND ADJUSTMENTS TO TIMES */
       
       if(cal.get(Calendar.DAY_OF_WEEK)!=7 && cal.get(Calendar.DAY_OF_WEEK)!=1){
            //if shift is clockedin before actual shift, update time to
            //correct shift time
            
            if(time.before(SStartTime)){
                adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStartTime.getHours(), SStartTime.getMinutes()-5, 
                        SStartTime.getSeconds());
                AdjustedTimeOperation = " (Shift Start)";
            }
            //check if time is in between Shift start and before Lunch Start
            //If it is outside graceperiod also, Deduction
            if (time.after(SStartTime) && time.before(LStartTime)){
                adjustedtime.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE),SStartTime.getHours(),SStartTime.getMinutes()+10,
                        SStartTime.getSeconds());
                AdjustedTimeOperation = " (Shift Dock)";
            }
             // Check if time is after Lunch Start and Before Lunch End, If so
            // shift the clockouts to correct times
            
            if (time.after(LStartTime) && time.before(LStopTime)){
                if(time.getMinutes() >= 16){
                    adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                            cal.get(Calendar.DATE), LStopTime.getHours(),
                            LStopTime.getMinutes(), LStopTime.getSeconds());
                    AdjustedTimeOperation = "(Lunch Stop)";
                }
                if(time.getMinutes() < 16){
                    adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                            cal.get(Calendar.DATE), LStartTime.getHours(),
                            LStartTime.getMinutes(), LStartTime.getSeconds());
                    AdjustedTimeOperation = "(Lunch Start)";
                }
           
            }
            // Check if the time is after lunch stop and before Shift Stop, if so
            // if they are outside grace period, Deduct time.
            if (time.after(LStopTime) && time.before(SStopTime)){
                Time thold = new Time(time.getHours(),time.getMinutes(),time.getSeconds());
                adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStopTime.getHours(), SStopTime.getMinutes()-15,
                        SStopTime.getSeconds());
                AdjustedTimeOperation = " (Shift Dock)";
                
                if (time.equals(thold)){
                    AdjustedTimeOperation = " (Interval Round)";
                }
           
            }
            //if time is after Shift Stop, adjust back to correct clockout 
            if (time.after(SStopTime)){
                adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), SStopTime.getHours(), 
                        SStopTime.getMinutes()-5, SStopTime.getSeconds());
                AdjustedTimeOperation = " (Shift Stop)";
            }

       }
       
       // THIS HANDLES THE WEEKEND CASES
       if(cal.get(Calendar.DAY_OF_WEEK) == 7 || cal.get(Calendar.DAY_OF_WEEK) == 1){
           adjustedtime.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DATE), time.getHours(), 
                        time.getMinutes(), time.getSeconds());
       }
       
       
       
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

}
