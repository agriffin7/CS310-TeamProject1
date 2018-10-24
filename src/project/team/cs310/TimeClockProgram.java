package project.team.cs310;

// Muhammad Shakir  ..... 
// Main Class
public class TimeClockProgram
{
    public static void main(String[] args)
    {
        TASDatabase db = new TASDatabase();

        Shift s1 = db.getShift(1);
        Shift s2 = db.getShift(2);
        Shift s3 = db.getShift(3);
        
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());
        
        Badge b1 = db.getBadge("12565C60");
        Badge b2 = db.getBadge("08D01475");
        Badge b3 = db.getBadge("D2CC71D4");
        Badge b4 = db.getBadge("4DAC9951");
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        System.out.println(b4.toString());
        
        Punch p1 = db.getPunch(3433);
        Punch p2 = db.getPunch(3440);
        Punch p3 = db.getPunch(3325);
        Punch p4 = db.getPunch(1963);
        Punch p5 = db.getPunch(5702);
        
        System.out.println(p1.printOriginalTimestamp());
        System.out.println(p2.printOriginalTimestamp());
        System.out.println(p3.printOriginalTimestamp());
        System.out.println(p4.printOriginalTimestamp());
        System.out.println(p5.printOriginalTimestamp());        

    }    
}