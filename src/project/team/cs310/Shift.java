package project.team.cs310;

// Muhammad Shakir  ..... 

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;

public class Shift
{
    // Create varaibels
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private Time shiftStart, shiftStop, lunchStart, lunchStop;
   
    // Constructor
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, String description, Time start, Time stop, Time lunchStart, Time lunchStop)
    {
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchDeduct = lunchDeduct;
        this.description = description;
        this.shiftStart = start;
        this.shiftStop = stop;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
    }
    
    // Gettter Methods

    public int getId()
    {
        return id;
    }

    public int getInterval()
    {
        return interval;
    }

    public int getGracePeriod()
    {
        return gracePeriod;
    }

    public int getDock()
    {
        return dock;
    }

    public int getLunchDeduct()
    {
        return lunchDeduct;
    }

    public String getDescription()
    {
        return description;
    }

    public Time getShiftStart()
    {
        return shiftStart;
    }

    public Time getShiftStop()
    {
        return shiftStop;
    }

    public Time getLunchStart()
    {
        return lunchStart;
    }

    public Time getLunchStop()
    {
        return lunchStop;
    }

    private long getElapsedTime(Time s, Time e)
    {
        Calendar startCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        startCal.setTimeInMillis(s.getTime());
        endCal.setTimeInMillis(e.getTime());

        long start, end;
        start = startCal.getTimeInMillis();
        end = endCal.getTimeInMillis();
        return (end - start) / (60 * 1000);
    }

    // OverRiding the toString Java Object method
    // Formatting Output  
    @Override
    public String toString()
    {
        String data = "";
        String startTime = (new SimpleDateFormat("HH:mm")).format(shiftStart.getTime());
        String stopTime = (new SimpleDateFormat("HH:mm")).format(shiftStop.getTime());
        String lunchStartTime = (new SimpleDateFormat("HH:mm")).format(lunchStart.getTime());
        String lunchStopTime = (new SimpleDateFormat("HH:mm")).format(lunchStop.getTime());
        
        data += description + ": ";
        data += startTime + " - ";
        data += stopTime + " (";
        data += getElapsedTime(shiftStart, shiftStop) + " minutes);";
        data += " Lunch: " + lunchStartTime + " - ";
        data += lunchStopTime + " (";
        data += getElapsedTime(lunchStart, lunchStop) + " minutes)";
        return data;
    }
}