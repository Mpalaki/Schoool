/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.Assignment;
import model.AssignmentUser;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public interface TrainerDao {

//  i. View all the Courses he / she is enrolled 
// ii. View all the Students per Course 
//iii. View all the Assignments per Student per Course 
// iv. Mark all the Assignments per Student per Course 
    public List<Course> viewCoursesPerTrainersCourse(int idtrainer);

    public List<Course> getCoursesPerTrainersCourse(int idtrainer);

    public List<User> viewStudentsPerCourse(int idtrainer, int idcourse);

   
    public List<AssignmentUser> viewAssignmentsPerStudentPerCourse(int idcourse);
    
    public List<AssignmentUser> getAssignmentsPerStudentPerCourse(int idcourse);
    
//    public boolean updateAssignmentCourseStudentTable(int idcourse);
    
    public boolean markAssignmentPerStudentPerCourse(int idassignment, int idstudent, int idcourse, int mark);

}
