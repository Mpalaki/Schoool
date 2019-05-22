/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import Dao.HeadmasterDaoInterfaceImplementation;
import Dao.StudentDaoImplementation;
import Dao.TrainerDaoImplementation;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import model.Assignment;
import model.Course;
import model.Schedule;
import model.User;

/**
 *
 * @author Makis
 */
public class School {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
//        Course c = new Course("course1");
        HeadmasterDaoInterfaceImplementation h1 = new HeadmasterDaoInterfaceImplementation();
//        h1.insertCourse(c);
//        User trainer = new User("Alexandros","Porfiris","Alex123","student4");
//        User student = new User(6, "r ", "fotrrrrreinos", "fff555", "student 43");
        User trainer = new User(11, "giannis", "Efthymiou", "Bill123", "student 43");
//        User trainer2 = new User(5,"billis","Efthymiou","Bill123","student 43");
//        h1.viewStudentsById(1);
//        h1.viewCourses();
//        h1.viewCourseById(4);
//        Course course = new Course(3, "BC11");
//        h1.updateCourse(course);
//        h1.viewTrainers();
//        h1.viewTrainerById(1);
//        h1.viewStudentById(4);
//        h1.updateStudent(student);
//        h1.updateTrainer(trainer);
//        UserCourse uc = new UserCourse(course);
//        h1.appointStudentsToCourse(2, 3);
//        h1.appointTrainersToCourse(4, 3);
//        int year, int month, int date,
//                     int hour, int minute, int second, int nano
//        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
//        Assignment assignment = new Assignment(1,"Ass311",timestamp);
//        Assignment ass = new Assignment(1,"test1");
//                h1.updateAssignment(ass);

//        h1.updateAssignment(ass);
//h1.viewCourses();
//        h1.deleteAssignment(2);
//        h1.deleteAssignment(41);
//        h1.deleteCourse(55);
//        h1.updateStudent(student);
        Course c = h1.getCourseById(2);
//        h1.insertCourse(c);
//        h1.viewCourseById(2);
//        h1.deleteStudent(10);
//        h1.insertStudent(student);
//        h1.viewStudentById(1);
//        h1.viewStudentById(9);h1\\
//        h1.insertTrainer(trainer);
//        h1.deleteTrainer(1);
//        h1.viewAssignments();
//        h1.deleteAssignment(7);
//        h1.appointTrainersToCourse(7, 1);
//        h1.appointAssignmentsToCourse(5, 2);
//        h1.viewAssignmentsPerCourse(2);
//        h1.deleteCourse(6);
//        h1.removeStudentFromCourse(2,1);
//        h1.viewStudentsPerCourse(1);
//        h1.removeTrainerFromCourse(5, 1);
//        h1.removeAssignmentFromCourse(3, 2);
//        String s = "February 3, 2019";
//        dateInput(s);
//        h1.viewSchedulePerCourse(c);

//        Schedule schedule = new Schedule(c,"9/9/2019" );
//        Course c1 = new Course(12,"man");
//        Course c1 = h1.getCourseById(2);
//        h1.removeScheduleFromCourse(c,"3919-09-07");
//        h1.appointAssignmentsToCourse(8, 1);
//        StudentDaoImplementation sdi = new StudentDaoImplementation();
//        sdi.viewSubmissionDatesOfAssignmentsPerCourse(1);
        TrainerDaoImplementation t = new TrainerDaoImplementation();
        StudentDaoImplementation st = new StudentDaoImplementation();
        
//        t.viewCourses(4);
//        t.viewStudents(4, 2);
//            h1.appointAssignmentsToCourse(3,2);
//        h1.appointStudentsToCourse(1, 1);
//        h1.removeAssignmentFromCourse(4, 1);
//        h1.insertStudent(student);
//        h1.removeStudentFromCourse(1, 1);
//        t.viewAssignmentsPerStudentPerCourse(2);
//        t.updateAssignmentCourseStudentTable(1);
//            t.markAssignmentPerStudentPerCourse(3,1,2,80);
//        st.viewAssignmentsPerCoursePerStudent(15);
        st.submitAssignment(21, 21, 21);
    }

   
    
}
