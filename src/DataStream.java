import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class DataStream {
    
  
    static Scanner scnFile;
    
    //input student instances in List
    public static void InputStudentsToList(List<Student> students) throws FileNotFoundException
    {
    File studentFile = new File("C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\Students.txt");
    scnFile = new Scanner(studentFile);
    while(scnFile.hasNextLine())
    {
        String firstName=scnFile.nextLine();
        String middleName=scnFile.nextLine();
        String lastName=scnFile.nextLine();
        String email=scnFile.nextLine();
        String phone=scnFile.nextLine();
        String streetAddress=scnFile.nextLine();
        String city=scnFile.nextLine();
        String state=scnFile.nextLine();
        String zip=scnFile.nextLine();
        String identificationNumber=String.valueOf(Attendees.AttendeeCount);
        Attendees.AttendeeCount= Attendees.AttendeeCount+1;
        String birthDay=scnFile.nextLine();
        String birthMonth=scnFile.nextLine();
        String birthYear=scnFile.nextLine();
        String gradePointAverage=scnFile.nextLine();
        String dateAttendeeBegins=scnFile.nextLine();
        students.add(new Student(firstName,middleName,lastName,email,phone,streetAddress,city,state,zip,identificationNumber, birthDay,birthMonth,birthYear,gradePointAverage,dateAttendeeBegins));
        scnFile.nextLine();
    }
    scnFile.close();
    }

    public static void InputFacultyToList(List<Faculty> faculties) throws FileNotFoundException
    {
        File facultyFile = new File("C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\Faculty.txt");
        scnFile=new Scanner(facultyFile);
        while(scnFile.hasNextLine())
        {
            boolean tenured = false;
            String firstName=scnFile.nextLine();
            String middleName=scnFile.nextLine();
            String lastName=scnFile.nextLine();
            String email=scnFile.nextLine();
            String phone=scnFile.nextLine();
            String streetAddress=scnFile.nextLine();
            String city=scnFile.nextLine();
            String state=scnFile.nextLine();
            String zip=scnFile.nextLine();
            String identificationNumber=String.valueOf(Attendees.AttendeeCount);
            Attendees.AttendeeCount= Attendees.AttendeeCount+1;
            String birthDay=scnFile.nextLine();
            String birthMonth=scnFile.nextLine();
            String birthYear=scnFile.nextLine();
            String dateAttendeeBegins=scnFile.nextLine();
            String tenuredString=scnFile.nextLine();
            if(tenuredString.equals("true"))
            {
                tenured=true;
            }
            faculties.add(new Faculty(firstName,middleName,lastName,email,phone,streetAddress,city,state,zip,identificationNumber,birthDay,birthMonth,birthYear,dateAttendeeBegins,tenured));
            scnFile.nextLine();
        }
        scnFile.close();
    }

    public static void InputClassesToList(List<CollegeClass> classes,List<Faculty> faculties) throws FileNotFoundException
{
    File classFile = new File("C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\class.txt");
    scnFile=new Scanner(classFile);
        while(scnFile.hasNextLine())
        {
            String Department=scnFile.nextLine();
            String classLevel=scnFile.nextLine();
            String classDescription=scnFile.nextLine();
            String classId=scnFile.nextLine();
            int minimumAmountOfStudents=Integer.parseInt(scnFile.nextLine());
            int maximumAmountOfStudents=Integer.parseInt(scnFile.nextLine());

            classes.add(new CollegeClass(Department,classLevel,classDescription,classId,minimumAmountOfStudents,maximumAmountOfStudents));
            scnFile.nextLine();
            String seeNextLine=scnFile.nextLine();
            while(!(seeNextLine.equals("/")))
            {
                for (Faculty item : faculties) {
                    if (item.getLastName().equals(seeNextLine))
                    {
                        //not yet sure how i will be afiiliating a session to
                        //a faculty member, blank for now with nextline to read through file
                        // System.out.println(seeNextLine); this line used to debug and see what professors are being created
                        item.addToTeachingList(classes.get(classes.size()-1));
                        //above is deleted below is kept
                        seeNextLine=scnFile.nextLine();
                        break;
                    }
                }
            }
            scnFile.nextLine();
        }
        scnFile.close();
}

    public static void OutScheduledCourseSessions(List<CollegeClass> classes, List<Faculty> faculties,List<Student>students) throws IOException
    {
        /*
        ScheduledCourseSessions.txt: 
• Each course that was scheduled (full details)  
• Each session for that course (i.e. session id) 
• The full name and id number of the instructor for the session 
• The number of students in the session 
• For each student in the session, the full name and id number.  */
        int count=0;
        String filePath="C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\OutScheduledCourseSessions.txt";
        File classFile = new File(filePath);
        FileWriter fileWriter=new FileWriter(filePath);
        for (CollegeClass classItem : classes) {
            fileWriter.write(classItem.getDepartment()+"\n"+classItem.getClassId()+"\n"+classItem.getClassDescription()+"\n");
        }
        fileWriter.write("\n"+"\n");

        for (Faculty faculty : faculties) {
            for (Session session : faculty.getSessionsTeachingList()) {
                fileWriter.write(session.getSessionId()+"\n");
                fileWriter.write(faculty.getFirstName()+" "+faculty.getLastName()+": "+faculty.getIdentificationNumber()+"\n");
                fileWriter.write("students in each session: "+session.getNumberOfStudentsAttending()+"\n");
                count=count+1;
                for (Student student : students) {
                    if(student.getSessionsEnrolledInList().contains(session))
                    {
                        fileWriter.write(student.getFirstName()+" "+student.getMiddleName()+" "+student.getLastName()+" "+student.getIdentificationNumber()+"\n");
                    }
                }
                fileWriter.write("\n"+"\n");
            }
        }      
        fileWriter.write("\n"+"\n");
        fileWriter.close();
        Session.setCompleteAmountOfSessionsenrolled(count);
    }

    public static void OutUnscheduledCourseSessions(List<Faculty> faculties)throws IOException
    {
        int count=0;
        /*UnscheduledCourseSessions.txt: 
• Each course that was not scheduled (i.e. not even one session scheduled) along with the 
minimum number of students that needed to be in the course.  */
        String filePath="C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\OutUnscheduledCourseSessions.txt";
        File classFile = new File(filePath);
        FileWriter fileWriter=new FileWriter(filePath);
        for (Faculty faculty : faculties) {
            for (Session session : faculty.getSessionsTeachingList()) {
                if(session.getNumberOfStudentsAttending()==0)
                {
                    count=count+1;
                    fileWriter.write(session.getSessionId()+" ... minimum amount of students required: "+session.getMinimumAmountOfStudents()+"\n");
                }
            }
        }
        fileWriter.close();
        Session.setAmountOfSessionsUnenrolled(count);
    }

    public static void Faculty(List<Faculty> faculties,List<Student>students,List<CollegeClass>classes)throws IOException
    {
        /* Faculty.txt: 
• Print all details for each faculty member and list the full course details of each course 
and session he/she has scheduled along with the session id and number of students in 
that session */                                                                              //KEEP AS FACULTYOUT.TXT!
        String filePath="C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\FacultyOut.txt";
        File classFile = new File(filePath);
        FileWriter fileWriter=new FileWriter(filePath);
        for (Faculty faculty : faculties) {
            fileWriter.write(faculty.getFirstName()+"\n");
            fileWriter.write(faculty.getMiddleName()+"\n");
            fileWriter.write(faculty.getLastName()+"\n");
            fileWriter.write(faculty.getEmail()+"\n");
            fileWriter.write(faculty.getPhone()+"\n");
            fileWriter.write(faculty.getStreetAddress()+"\n");
            fileWriter.write(faculty.getCity()+"\n");
            fileWriter.write(faculty.getState()+"\n");
            fileWriter.write(faculty.getZip()+"\n");
            fileWriter.write(faculty.getIdentificationNumber()+"\n");
            fileWriter.write(faculty.getBirthDay()+"/"+faculty.getBirthMonth()+"/"+faculty.getBirthYear()+"\n"+"\n");
            for (Session session : faculty.getSessionsTeachingList()) {
                fileWriter.write(session.getTicketlessId()+"\n");
                fileWriter.write(session.getSessionId()+" contains: "+session.getNumberOfStudentsAttending()+" students"+"\n");
            }
            fileWriter.write("\n"+"\n");
        }
        fileWriter.write("\n");
        fileWriter.close();

    }

    public static void ScheduledStudents(List<Student> students, List<CollegeClass> classes)throws IOException
    {
        /* ScheduledStudents.txt: 
• For each student show all details and list the session id, course id, and course 
description for the classes taken.  */
    String filePath="C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\ScheduledStudents.txt";
    File classFile = new File(filePath);
    FileWriter fileWriter=new FileWriter(filePath);
    for (Student student : students) {
            fileWriter.write(student.getFirstName()+"\n");
            fileWriter.write(student.getMiddleName()+"\n");
            fileWriter.write(student.getLastName()+"\n");
            fileWriter.write(student.getEmail()+"\n");
            fileWriter.write(student.getPhone()+"\n");
            fileWriter.write(student.getStreetAddress()+"\n");
            fileWriter.write(student.getCity()+"\n");
            fileWriter.write(student.getState()+"\n");
            fileWriter.write(student.getZip()+"\n");
            fileWriter.write(student.getIdentificationNumber()+"\n");
            fileWriter.write(student.getBirthDay()+"/"+student.getBirthMonth()+"/"+student.getBirthYear()+"\n");
            fileWriter.write(student.getGradePointAverage()+"\n");
            for (Session session : student.getSessionsEnrolledInList()) {
                fileWriter.write(session.getSessionId()+"\n");
                for (CollegeClass item : classes) {
                    if(item.getClassId().equals(session.getTicketlessId()))
                    {
                        fileWriter.write(item.getClassId()+"\n");
                        fileWriter.write(item.getClassDescription()+"\n");
                    }
                }
            }
            fileWriter.write("\n"+"\n");
    }
    fileWriter.close();
    }

    public static void UnscheduledStudents(List<Student> students)throws IOException
    {
        /* 
         * UnscheduledStudents.txt: 
        • List full details for each student that has no classes to take.
        */
        int count=0;
        String filePath="C:\\Users\\Brand\\OneDrive\\Documents\\JavaProjects\\FinalExam\\src\\UnscheduledStudents.txt";
        File classFile = new File(filePath);
        FileWriter fileWriter=new FileWriter(filePath);
        for (Student student : students) {
            if(student.getSessionsEnrolledInList().size()==0)
            {
                count=count+1;
                fileWriter.write(student.getFirstName()+"\n");
                fileWriter.write(student.getMiddleName()+"\n");
                fileWriter.write(student.getLastName()+"\n");
                fileWriter.write(student.getEmail()+"\n");
                fileWriter.write(student.getPhone()+"\n");
                fileWriter.write(student.getStreetAddress()+"\n");
                fileWriter.write(student.getCity()+"\n");
                fileWriter.write(student.getState()+"\n");
                fileWriter.write(student.getZip()+"\n");
                fileWriter.write(student.getIdentificationNumber()+"\n");
                fileWriter.write(student.getBirthDay()+"/"+student.getBirthMonth()+"/"+student.getBirthYear()+"\n");
                fileWriter.write(student.getGradePointAverage()+"\n");
                fileWriter.write("\n"+"\n");
            }
        }
        fileWriter.close();
        Student.setAmountUnenrolled(count);
    }

}
