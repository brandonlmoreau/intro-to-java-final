import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class CollegeClass 
{
    public String Department;
    //1a... 1b... or 3a... 3b
    private String classLevel;
    private String classDescription;
    //manually entered acronym for class with concatonated classLevel variable... Bio+1a.. Bio1a
    private String classId;
    
    private int minimumAmountOfStudents;
    private int maximumAmountOfStudents;
    CollegeClass(String Department, String classLevel,String classDescription,String classID,int minimumAmountOfStudents, int maximumAmountOfStudents)
    {
        this.Department = Department;
        this.classLevel = classLevel;
        this.classDescription = classDescription;
        this.classId=classID;
        this.minimumAmountOfStudents=minimumAmountOfStudents;
        this.maximumAmountOfStudents=maximumAmountOfStudents;
    }
    public String getDepartment() {
        return Department;
    }
    public String getClassDescription() {
        return classDescription;
    }
    public String getClassId() {
        return classId;
    }
    public String getClassLevel() {
        return classLevel;
    }
    public int getMaximumAmountOfStudents() {
        return maximumAmountOfStudents;
    }
    public int getMinimumAmountOfStudents() {
        return minimumAmountOfStudents;
    }

}

class Session
{
    private static int sessionCounter=1500;
    private String sessionId;
    private String ticketlessId;
    private int numberOfStudentsAttending;
    private int minimumAmountOfStudents;
    private int maximumAmountOfStudents;
    static private int amountOfSessionsUnenrolled=0;
    static private int completeAmountOfSessionsenrolled=0;


    Session(String sessionId,String ticketlessId, int minimumAmountOfStudents, int maximumAmountOfStudents)
    {
        this.sessionId= sessionId;
        this.ticketlessId=ticketlessId;
        numberOfStudentsAttending=0;
        this.minimumAmountOfStudents = minimumAmountOfStudents;
        this.maximumAmountOfStudents=maximumAmountOfStudents;
    }
    public static int getCompleteAmountOfSessionsenrolled() {
        return completeAmountOfSessionsenrolled;
    }
    public static void setCompleteAmountOfSessionsenrolled(int completeAmountOfSessionsenrolled) {
        Session.completeAmountOfSessionsenrolled = completeAmountOfSessionsenrolled;
    }
    public static void setAmountOfSessionsUnenrolled(int amountOfSessionsUnenrolled) {
        Session.amountOfSessionsUnenrolled = amountOfSessionsUnenrolled;
    }
    public static int getAmountOfSessionsUnenrolled() {
        return amountOfSessionsUnenrolled;
    }
    public static int getSessionCounter() {
        return sessionCounter;
    }
    public String getSessionId() {
        return sessionId;
    }
    public String getTicketlessId() {
        return ticketlessId;
    }
    public int getNumberOfStudentsAttending() {
        return numberOfStudentsAttending;
    }
    public static void increaseSessionCounter() {
        Session.sessionCounter = sessionCounter+5;
    }
    public void increaseNumberOfStudentsAttending( ) {
        numberOfStudentsAttending = numberOfStudentsAttending+1;
    }
    public int getMaximumAmountOfStudents() {
        return maximumAmountOfStudents;
    }
    public int getMinimumAmountOfStudents() {
        return minimumAmountOfStudents;
    }
}