/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import java.util.Map;
import model.Assignment;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public interface HeadmasterDaoInterface {

//    Head Master, create any courses needed , appoint
//
//    the trainer(s)
//
//and the students under each of the courses, appoint assignments to each of the courses
//    i.CRUD on Courses ii
//    . CRUD on Students iii
//    . CRUD on Assignments iv
//    . CRUD on Trainers v
//    . CRUD on Students per Courses vi
//    . CRUD on Trainers per Courses vii
//    . CRUD on Assignments per Courses viii
//    . CRUD on Schedule per Courses 
    /////////////////////METHODS FOR COURSES://///////////////////////////
    public boolean insertCourse(Course course);

    public Map<Integer, Course> viewCourses();

    public Map<Integer, Course> getCourses();

    public Course viewCourseById(int idcouse);

    public void updateCourse(Course course);

    public boolean deleteCourse(int idcourse);

    /////////////////////METHODS FOR ASSIGNMENTS://///////////////////////////
    public boolean insertAssignment(Assignment assignment);

    public Map<Integer, Assignment> viewAssignments();

    public Map<Integer, Assignment> getAssignments();

    public Assignment viewAssignmentById(int idassignment);

    public void updateAssignment(Assignment assignment);

    public boolean deleteAssignment(int idassignment);

    /////////////////////METHODS FOR STUDENTS://///////////////////////////
    public boolean insertStudent(User student);

    public Map<Integer, User> viewStudents();

    public Map<Integer, User> getStudents();

    public void updateStudent(User student);

    public boolean deleteStudent(int idstudent);

    /////////////////////METHODS FOR USERS://///////////////////////////
    public User getUserById(int iduser);

    /////////////////////METHODS FOR TRAINERS://///////////////////////////
    public boolean insertTrainer(User trainer);

    public Map<Integer, User> viewTrainers();

    public Map<Integer, User> getTrainers();

    public User viewTrainerById(int idtrainer);

    public User viewStudentById(int idstudent);

    public void updateTrainer(User trainer);

    public boolean deleteTrainer(int idtrainer);

    /////////////////////METHODS FOR JUNCTION TABLES://///////////////////////////
    public boolean appointStudentsToCourse(int idstudent, int idcourse);

    public boolean appointTrainersToCourse(int idtrainer, int idcourse);

    public boolean appointAssignmentsToCourse(int idassignment, int idcourse);

    public Map<Integer,Integer> viewStudentsPerCourse(int idcourse);
    
    public List<User> getStudentsPerCourse(int idcourse);

    public boolean removeStudentFromCourse(int idstudent, int idcourse);
    
    public Map<Integer,Integer> viewTrainersPerCourse(int idcourse);
    
    public Map<Integer,Integer> getTrainersPerCourse(int idcourse);

    public boolean removeTrainerFromCourse(int idtrainer, int idcourse);
    
    public Map<Integer,Integer> viewAssignmentsPerCourse(int idcourse);
    
    public Map<Integer,Integer> getAssignmentsPerCourse(int idcourse);

    public boolean removeAssignmentFromCourse(int idassignment, int idcourse);
    
}
