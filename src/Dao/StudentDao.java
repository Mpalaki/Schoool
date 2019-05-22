/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.Assignment;
import model.AssignmentCourse;
import model.Course;
import model.User;
import model.UserCourse;

/**
 *
 * @author Makis
 */
public interface StudentDao {

//    i.See his / her daily Schedule per Course 
//    ii.See the dates of submission of the Assignments per Course
//    iii.Submit any Assignments 
    
    public List<Course> viewDailySchedule(int idstudent, String date);
    
    public List<Assignment> viewSubmissionDatesOfAssignmentsPerCourse(int idcourse);
    
    public boolean submitAssignments(int idstudent, int idassignment);
    
    public void updateAssignmentCourseStudent(int idstudent);
    
    public List<User> getCoursePerStudent(int idcourse);
    
    public boolean submitAssignment(int idassignment,int idstudent,int idcourse);

    public List<AssignmentCourse> viewAssignmentsPerCoursePerStudent(int idstudent);
    
    public List<AssignmentCourse> getAssignmentsPerCoursePerStudent(int idstudent);
}
