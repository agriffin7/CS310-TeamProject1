package project.team.cs310;

// Muhammad Shakir  ..... 

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;

public class Punch
{
    // Create variables or fields
    private int id = 0;
    private int terminalid;
    private String badgeid = "";
    private GregorianCalendar originaltime = new GregorianCalendar();
    private GregorianCalendar adjustedtime = null;
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
        
        //
        
    }
    
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