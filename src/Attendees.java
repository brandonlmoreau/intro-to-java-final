import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.ElementScanner14;

public class Attendees {
    public static int AttendeeCount=1500;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String streetAddress;
    protected String city;
    protected String state;
    protected String zip;
    protected String identificationNumber;
    protected String birthDay;
    protected String birthMonth;
    protected String birthYear;
    //dob is 3 variables"1"/"1"/"2000", this is one... "1/1/2000"
    protected String dateAttendeeBegins;

    Attendees(String firstName,String middleName, String lastName, String email,
    String phone,String streetAddress,String city,String state,String zip,
    String identificationNumber, String birthDay,String birthMonth,String birthYear,String dateAttendeeBegins)
    {
        this.firstName =firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.email=email;
        this.phone=phone;
        this.streetAddress=streetAddress;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.identificationNumber=identificationNumber;
        this.birthDay=birthDay;
        this.birthMonth=birthMonth;
        this.birthYear=birthYear;
        this.dateAttendeeBegins=dateAttendeeBegins;
    }

    public String getBirthDay() {
        return birthDay;
    }
    public String getBirthMonth() {
        return birthMonth;
    }
    public String getBirthYear() {
        return birthYear;
    }
    public String getCity() {
        return city;
    }
    public String getDateAttendeeBegins() {
        return dateAttendeeBegins;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getPhone() {
        return phone;
    }
    public String getState() {
        return state;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getZip() {
        return zip;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }
    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setDateAttendeeBegins(String dateAttendeeBegins) {
        this.dateAttendeeBegins = dateAttendeeBegins;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
}

class Student extends Attendees
{
    private List<Session> sessionsEnrolledInList;
    final private int MAX_CLASSES_STUDENT_CAN_TAKE =4;
    private String gradePointAverage;
    private static int amountUnenrolled=0;

    Student(String firstName,String middleName, String lastName, String email,
    String phone,String streetAddress,String city,String state,String zip,
    String identificationNumber, String birthDay,String birthMonth,String birthYear,String gradePointAverage,String dateAttendeeBegins)
    {
      super(firstName,middleName,lastName,email,phone,streetAddress,city,state,zip,identificationNumber,birthDay,birthMonth,birthYear,dateAttendeeBegins);
      this.gradePointAverage= gradePointAverage;
      sessionsEnrolledInList = new ArrayList<>();
    }
    public static int getAmountUnenrolled() {
        return amountUnenrolled;
    }
    public static void setAmountUnenrolled(int amountUnenrolled) {
        Student.amountUnenrolled = amountUnenrolled;
    }
    public List<Session> getSessionsEnrolledInList() {
        return sessionsEnrolledInList;
    }
    public int getMaxClassesYouCanTake() {
        return MAX_CLASSES_STUDENT_CAN_TAKE;
    }
    public String getGradePointAverage() {
        return gradePointAverage;
    }
    public void setGradePointAverage(String gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
    public void addClassToEnrollment(Session session)
    {
        if(canEnrollcauseOfSize(session)==true)
        {
            sessionsEnrolledInList.add(session);
            session.increaseNumberOfStudentsAttending();
        }
    }
    public boolean canEnrollcauseOfSize(Session session)
    {
        if(sessionsEnrolledInList.size()<4)
        {
           return canEnrollCauseNotAlreadyScheduledForIt(session);
        }
        else
        {
            return false;
        }
    }
    private boolean canEnrollCauseNotAlreadyScheduledForIt(Session potentialSession)
    {
        boolean matchForExistingEnrollment=false;
        if(sessionsEnrolledInList.size()>0)
        {
            for (Session enrolledSessions : sessionsEnrolledInList) {
                //edit, cannot account for ticket number
                if(enrolledSessions.getTicketlessId().equals(potentialSession.getTicketlessId()))
                {
                    matchForExistingEnrollment=true;
                    break;
    
                }
            }
        }
        
        if(matchForExistingEnrollment==true)
        {
            return false;

        }
        else
        {
            return true;
        }
    }
}
class Faculty extends Attendees
{
    private List<Session> sessionsTeachingList;
    private int MAX_CLASSES_FACULTY_CAN_TEACH;
    private boolean tenured;
    
    Faculty(String firstName,String middleName, String lastName, String email,
    String phone,String streetAddress,String city,String state,String zip,
    String identificationNumber, String birthDay,String birthMonth,String birthYear,String dateAttendeeBegins,boolean tenured)
    {
    super(firstName, middleName, lastName, email, phone, streetAddress, city, state, zip, identificationNumber, birthDay, birthMonth, birthYear,dateAttendeeBegins);
    this.tenured = tenured;
    sessionsTeachingList = new ArrayList<>();
    }

    public int getMAX_CLASSES_FACULTY_CAN_TEACH() {
        return MAX_CLASSES_FACULTY_CAN_TEACH;
    }
    public List<Session> getSessionsTeachingList() {
        return sessionsTeachingList;
    }

    public void addToTeachingList(CollegeClass classInput)
    {   //Bio+1a+"_T"+1500 = Bio1a_T1500
        String sessionIdWithoutTicketNumber=classInput.getClassId()+classInput.getClassLevel();
        String finalSessionId = sessionIdWithoutTicketNumber+"_T"+Session.getSessionCounter();
        sessionsTeachingList.add(new Session(finalSessionId,sessionIdWithoutTicketNumber,classInput.getMinimumAmountOfStudents(),classInput.getMaximumAmountOfStudents()));
        Session.increaseSessionCounter();
    }

}
