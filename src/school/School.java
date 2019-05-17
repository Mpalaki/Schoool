/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import Dao.HeadmasterDaoInterfaceImplementation;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Assignment;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public class School {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
//        Course c = new Course("course1");
        HeadmasterDaoInterfaceImplementation h1 = new HeadmasterDaoInterfaceImplementation();
//        h1.insertCourse(c);
//        User trainer = new User("Alexandros","Porfiris","Alex123","student4");
        User student = new User(1, "bill", "karfakis", "Bill123", "student 43");
        User trainer = new User(5, "billis", "Efthymiou", "Bill123", "student 43");
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
//        h1.updateTrainer(trainer2);
//        UserCourse uc = new UserCourse(course);
//        h1.appointStudentsToCourse(2, 3);
//        h1.appointTrainersToCourse(4, 3);
//        int year, int month, int date,
//                     int hour, int minute, int second, int nano
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        Assignment assignment = new Assignment("Ass11",timestamp);
        h1.insertAssignment(assignment);
    }

}
