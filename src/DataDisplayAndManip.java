import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class DataDisplayAndManip
{

    //display the basic method for showing menu
    static void DisplayAllSessions(List<Faculty> faculties)
    {
        for (Faculty fItem : faculties) {
            for (Session sItem : fItem.getSessionsTeachingList()) {
                System.out.println(sItem.getSessionId()+" taught by "+fItem.getLastName()+ " has ("+sItem.getNumberOfStudentsAttending()+") students");
            }
        }
    }
    static void DisplayManipOptions()
    {
        
        System.out.println("(a) add existing students to classes in sequential order");
        System.out.println("(b) select class and add to it");
    }
    
    static void AddStudentsInSequence(List<Faculty> faculties, List<Student> students)
    {   //for each teacher...
        for (Faculty faculty : faculties) {
            //select each of their sessions on by one...
            for (Session session : faculty.getSessionsTeachingList()) {
                //fill each session from above with students who are not already in the class
                List<String> holder= studentsEnrollableForGivenSessionAndAmount(students, session, session.getMaximumAmountOfStudents());
              /*  int numOfStudents=0;
                //above list is created to maintain student IDs, we filter through students to find anyone who 
                //is not signed up for 4 classes and is not signed up for this version of the class course already
                    for (Student student : students) 
                    {
                        //can enroll if not enrolled in similiar class/ have class availability
                        if(student.canEnrollcauseOfSize(session))
                        {
                            holder.add(student.getIdentificationNumber());
                            numOfStudents= numOfStudents+1;
                        }
                        if(numOfStudents==session.getMaximumAmountOfStudents())
                        {
                            break;
                        }
                    }*/
                    //only do below IF theres enough students
                    if(holder.size()>=session.getMinimumAmountOfStudents())
                    {   //refind students with matching IDs
                        SetEnrollmentOfStudentsWithAvailabilityList(holder,students,session);
                       /* for (String studentId : holder) {
                            for (Student student : students) {
                            if(studentId.equals(student.getIdentificationNumber()))
                            {
                            student.addClassToEnrollment(session);
                            }
                            }
                        }*/
                    }
            }
        }

    }
    static void AddStudentsToParticularClassLoop(List<Faculty> faculties,List<Student>students)
    {   Scanner inputScanner = new Scanner(System.in);
        String userInput;
        boolean classFound;
        List<String> potentialEntrollees;
            do {
                classFound=false;
                System.out.println();
                DisplayAllSessions(faculties);
                System.out.println();
                System.out.println("Listed above are all classes, select a class by typing in its full name (EX: Bio1a_T1234)");
                userInput = inputScanner.nextLine();
                for (Faculty faculty : faculties) {
                    for (Session session : faculty.getSessionsTeachingList()) {
                        //if user input does equal the id of a given session.(not case sensitive)
                        if(session.getSessionId().toLowerCase().equals(userInput))
                        {
                            classFound=true;
                            System.out.println("Would you like to fill the class up to its (a)minimum, (b) maximum, or (c) median between minimum and maximum?");
                            System.out.println("Type /'exit/' to exit the program");
                            userInput= inputScanner.nextLine();
                            potentialEntrollees = studentsEnrollableForGivenSessionAndAmount(students, session,amountToFillIndividualClass(userInput, session));
                            if(potentialEntrollees.size()>=session.getMinimumAmountOfStudents())
                            {
                                SetEnrollmentOfStudentsWithAvailabilityList(potentialEntrollees,students,session);
                            }
                            break;
                        }
                    }
                    if(classFound==true)
                    {
                        break;
                    }
                    
                }
                if((classFound==false)&&(!(userInput.equals("exit"))))
                {
                    System.out.println("Class not found");
                }
            } while (!(userInput.equals("exit")));
    }
    static private List<String> studentsEnrollableForGivenSessionAndAmount(List<Student> students,Session session,int studentsNeeded)
    {
        List<String> holder= new ArrayList<>();
                int numOfStudents=0;
                //above list is created to maintain student IDs, we filter through students to find anyone who 
                //is not signed up for 4 classes and is not signed up for this version of the class course already
                    for (Student student : students) 
                    {
                        //can enroll if not enrolled in similiar class/ have class availability
                        if(student.canEnrollcauseOfSize(session))
                        {
                            holder.add(student.getIdentificationNumber());
                            numOfStudents= numOfStudents+1;
                        }
                        if(numOfStudents==studentsNeeded)
                        {
                            break;
                        }
                    }
                    return holder;
    }
    static private int amountToFillIndividualClass(String input,Session session)
    {//(a)minimum, (b) maximum, or (c) median between minimum and maximum
        int returnValue=0;
        if(input.equals("a"))
        {
            returnValue=session.getMinimumAmountOfStudents();
        }
        else if(input.equals("b"))
        {
            returnValue=session.getMaximumAmountOfStudents();
        }
        else if(input.equals("c"))
        {
            returnValue=(session.getMaximumAmountOfStudents()+session.getMinimumAmountOfStudents())/2;
        }
        return returnValue;
    }
    static private void SetEnrollmentOfStudentsWithAvailabilityList(List<String> holder,List<Student>database,Session session)
    {
        for (String studentId : holder) {
            for (Student student : database) {
            if(studentId.equals(student.getIdentificationNumber()))
            {
            student.addClassToEnrollment(session);
            }
            }
        }
    }
    static void DisplayFinalData(List<Faculty> faculties,List<Student> students,List<CollegeClass> classes)
    {
        System.out.println("Total Students: "+students.size());
        System.out.println("Total Faculty: "+faculties.size());
        System.out.println("Total Courses: "+classes.size());
        System.out.println("Total Sessions: "+Session.getCompleteAmountOfSessionsenrolled());
        System.out.println("Total Unscheduled Sessions: "+Session.getAmountOfSessionsUnenrolled());
        System.out.println("Total UnEnrolled Students: "+Student.getAmountUnenrolled());

    }
}