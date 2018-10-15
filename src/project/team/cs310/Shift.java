package project.team.cs310;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Shift 
{
    private int shiftid, gracePeriod, dock, lunchDeduct;
    private String description;
    private long lunchBreak;
    private Time timeStart = new Time(0);
    private Time timeStop = new Time(0);
    private Time lunchStart = new Time(0);
    private Time lunchStop = new Time(0);    
    
    public Shift()
    {
        //empty constructor
    }
    public Shift(int shiftid, String description, Time timeStart, Time timeStop, int interval, int gracePeriod, int dock, Time lunchStart, Time lunchStop, long lunchBreak, int lunchDeduct)
    {
       this.shiftid = shiftid;
       this.description = description;
       this.timeStart = timeStart;
       this.timeStop = timeStop;

       this.gracePeriod = gracePeriod;
       this.dock = dock;
       this.lunchStart = lunchStart;
       this.lunchStop = lunchStop;
       this.lunchBreak = lunchBreak;
       this.lunchDeduct = lunchDeduct;
    }
    
    // getters and setters

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
    public void setShiftid(int shiftid) 
{
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

    private long getElapsedTime(Time tStart, Time tend)
    {
        Calendar startCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        startCal.setTimeInMillis(tStart.getTime());
        endCal.setTimeInMillis(tend.getTime());
        long initial, ending;
        initial = startCal.getTimeInMillis();
        ending = endCal.getTimeInMillis();
        return (ending - initial)/60000;
    }
    
    @Override
    public String toString(){
        String output = "";
        String startTime = (new SimpleDateFormat("HH:mm")).format(timeStart.getTime());
        String stopTime = (new SimpleDateFormat("HH:mm")).format(timeStop.getTime());
        String lunchStartTime = (new SimpleDateFormat("HH:mm")).format(lunchStart.getTime());
        String lunchStopTime = (new SimpleDateFormat("HH:mm")).format(lunchStop.getTime());
        output += description + ": ";
        output += startTime + " - ";
        output += stopTime + " (";
        output += getElapsedTime(timeStart, timeStop) + " minutes);";
        output += " Lunch: " + lunchStartTime + " - ";
        output += lunchStopTime + " (";
        output += getElapsedTime(lunchStart, lunchStop) + " minutes)";
        return output;
    }
}    
