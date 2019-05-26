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
import model.AssignmentCourseStudent;
import model.Course;
import model.Schedule;
import model.User;
import userinterface.LoginPage;

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

        //////////////////  MAKE USERNAME UNIQUE IN DATABASE  /////////////////
//        h1.insertCourse(c);
//        User trainer = new User("Alexandros","Porfiris","Alex123","student4");
        User student = new User(6, "Sofia ", "Foteinou", "code", "sofo");
        User trainer = new User(11, "giannis", "Efthymiou", "Bill123", "student 43");
//        User trainer2 = new User(5,"billis","Efthymiou","Bill123","student 43");


        Course c = h1.getCourseById(2);


//        sdi.viewSubmissionDatesOfAssignmentsPerCourse(1);
        TrainerDaoImplementation t = new TrainerDaoImplementation();
        StudentDaoImplementation st = new StudentDaoImplementation();

//        t.viewCourses(4);
//        t.viewStudents(4, 2);

//        System.out.println(t.getAssignmentsPerStudentPerCourse(2));
        AssignmentCourseStudent acs = new AssignmentCourseStudent();
 
        User user = new User();
//        System.out.println(user.getUserByUsername("student2"));

        LoginPage lp = new LoginPage();
//        lp.displayInitialStudentMenuOptions();
        /////////////////// APPOINT SCHEDULE TO COURSE, MISSING CHECK FOR COURSE ////////////////
                lp.welcomePage();
//String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "~", "`", "!", "@", "#", "$", "%", "^", "&", "*"};
//    String[] b = {"d", "o", "v", "b", "z", "g", };
        User u = new User();
//        System.out.println(u.encrypt("gp"));
    
//        lp.welcomePage();
//                                h1.viewStudentById(19);

//        System.out.println(hashed);
    }}

    
    

