package project.team.cs310;

import java.sql.Time;
import java.text.SimpleDateFormat;


public class Shift {
    /*Shift will contain the shift ruleset
        (that is, the starting time, stopping time, and other parameters of a single shift).*/
        
        /*instance field*/
         private int shiftid, interval, dock, gracePeriod, lunchDeduct;
         private String description;
         private long lunchBreak;
         private long timeInterval;
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
            return timeStart;
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
        
        public void setTimeInterval(long timeInterval){
            this.timeInterval = timeInterval;
        }
        
        /*SETTER METHODS END*/
        
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
        output += timeInterval + " minutes);";
        output += " Lunch: " + lunchStartTime + " - ";
        output += lunchStopTime + " (";
        output += lunchBreak + " minutes)";
        return output;
        }
}
