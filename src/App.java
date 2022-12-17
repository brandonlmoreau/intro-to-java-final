import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.event.ListDataEvent;


public class App {
    public static void main(String[] args) throws Exception {

    Scanner inputScanner = new Scanner(System.in);
    List<CollegeClass> classes= new ArrayList<>();
    List<Student> students= new ArrayList<>();
    List<Faculty> faculties= new ArrayList<>();
    DataStream.InputStudentsToList(students);
    DataStream.InputFacultyToList(faculties);
    DataStream.InputClassesToList(classes,faculties);   
    DataDisplayAndManip.DisplayAllSessions(faculties);
    System.out.println();
    DataDisplayAndManip.DisplayManipOptions();
    String input = inputScanner.next();
    if(input.equals("a"))
    {
        DataDisplayAndManip.AddStudentsInSequence(faculties,students);
    }
    else if(input.equals("b"))
    {       
        DataDisplayAndManip.AddStudentsToParticularClassLoop(faculties,students);
    }
    DataStream.OutScheduledCourseSessions(classes, faculties, students);
    DataStream.OutUnscheduledCourseSessions(faculties);
    DataStream.Faculty(faculties, students, classes);
    DataStream.ScheduledStudents(students, classes);
    DataStream.UnscheduledStudents(students);
    DataDisplayAndManip.DisplayFinalData(faculties, students,classes);

    
    }
}
