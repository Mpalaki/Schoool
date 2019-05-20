/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
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
    
    public List<Course> viewCourses(int idtrainer);
    
    public List<Course> getCourses(int idtrainer);
    
    public List<User> viewStudents(int idtrainer, int idcourse);
    
    
    
}
