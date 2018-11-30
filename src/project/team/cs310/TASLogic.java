package project.team.cs310;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
// Muhammad Shakir
public class TASLogic 
{
    // Logic Added
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        //declare your longs
        long TIME1 = 0;
        long TIME2 = 0;
        
        //go through the ArrayList and get the punches
        for(int i = 0; i < dailypunchlist.size(); i++){
            Punch p = dailypunchlist.get(i);
            
            //this handles shift 2 & shift 3
            if(shift.getId() == 2 || shift.getId() == 3){
                //if the boolean flag is true
                if(p.getFLAG() == true){
                    TIME1 = p.getAdjustedtime().getTimeInMillis();
                }
                //if boolean flag is false
                if(p.getFLAG() == false){
                    TIME2 = p.getAdjustedtime().getTimeInMillis();
                }
            }
            
            
            //this handles shift 1
            if(shift.getId() == 1){
                if (p.getSELECT() == 1){
                    TIME1 = p.getAdjustedtime().getTimeInMillis();
                }
                if (p.getSELECT() == 0){
                    TIME2 = p.getAdjustedtime().getTimeInMillis();
                }
            }

            
            //check that both TIME1 & TIME2 have data
            if(TIME1 != 0 && TIME2 != 0){
      
                //get the difference
                long AccruedLong = TIME2 - TIME1;
                
                //convert long to minutes (and integer)
                //also minus 30 minutes due to lunch
                int Accrued = (int) (AccruedLong/60000)-30;
                
                //return the accrued time
                return Accrued;
            }
        }
        //without timeout
        if(TIME1 != 0 && TIME2 == 0){
            return 0;
        }
        //apply auto timeout
      return shift.getLunchDeduct();  
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist)
    {
        String json = "";
        ArrayList<HashMap<String, String>> jsonData = new ArrayList();
        for (Punch punch : dailypunchlist) 
        {
            HashMap<String, String> punchData = new HashMap<>();
            
            punchData.put("terminalid", String.valueOf(punch.getTerminalid()));
            punchData.put("badgeid", String.valueOf(punch.getBadgeid()));
            punchData.put("id", String.valueOf(punch.getId()));
            punchData.put("punchtypeid", String.valueOf(punch.getPunchtypeid()));
            punchData.put("punchdata", String.valueOf(punch.getPunchData()));
          
            punchData.put("originaltimestamp", String.valueOf(punch.getOriginaltime().getTimeInMillis()));
            
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(punch.getAdjustedtime().getTimeInMillis());
            gc.set(Calendar.SECOND, 0);
            
            punchData.put("adjustedtimestamp", String.valueOf(gc.getTimeInMillis()));
            
            System.out.println(gc.getTimeInMillis());
            jsonData.add(punchData);
        }
        json = JSONValue.toJSONString(jsonData);
        return json;
    }
}
