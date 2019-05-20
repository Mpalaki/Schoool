/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.Assignment;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public class StudentDaoImplementation implements StudentDao {

    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();

    @Override
    public List<Course> viewDailySchedule(int idstudent, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Assignment> viewSubmissionDatesOfAssignmentsPerCourse(int idcourse) {
        List<Assignment> assignments = hm.getAssignmentsPerCourse(idcourse);
        Course course = hm.getCourseById(idcourse);
        System.out.println("The submission dates for the assignments for course: '" + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < assignments.size(); i++) {
                    System.out.println(i + 1 + ". " + assignments.get(i).getTitle() + ", submission date and time: " + assignments.get(i).getSubmission_date_time());
                }
    return assignments;}

    @Override
    public boolean submitAssignments(int idstudent, int idassignment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAssignmentCourseStudent(int idstudent) {
    
    }

    @Override
    public List<User> getCoursePerStudent(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
