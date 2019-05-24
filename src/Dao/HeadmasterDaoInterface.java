/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import model.Assignment;
import model.Course;
import model.Schedule;
import model.User;

/**
 *
 * @author Makis
 */
public interface HeadmasterDaoInterface {

    public boolean insertCourse(Course course);

    public Map<Integer, Course> viewCourses();

    public Map<Integer, Course> getCourses();

    public Course viewCourseById(int idcouse);

    public Course getCourseById(int idcouse);

    public void updateCourse(Course course);

    public boolean deleteCourse(int idcourse);

    /////////////////////METHODS FOR ASSIGNMENTS://///////////////////////////
    public boolean insertAssignment(Assignment assignment);

    public Map<Integer, Assignment> viewAssignments();

    public Map<Integer, Assignment> getAssignments();

    public Assignment viewAssignmentById(int idassignment);

    public Assignment getAssignmentById(int idassignment);

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

    public User viewStudentById(int idstudent);// check!!!!!!!!!!   ////////////

    public void updateTrainer(User trainer);

    public boolean deleteTrainer(int idtrainer);

    /////////////////////METHODS FOR JUNCTION TABLES://///////////////////////////
    public boolean appointStudentsToCourse(int idstudent, int idcourse);

    public boolean appointTrainersToCourse(int idtrainer, int idcourse);

    public boolean appointAssignmentsToCourse(int idassignment, int idcourse);

    public List<User> viewStudentsPerCourse(int idcourse);

    public List<User> getStudentsPerCourse(int idcourse);

    public boolean removeStudentFromCourse(int idstudent, int idcourse);

    public List<User> viewTrainersPerCourse(int idcourse);

    public List<User> getTrainersPerCourse(int idcourse);

    public boolean removeTrainerFromCourse(int idtrainer, int idcourse);

    public List<Assignment> viewAssignmentsPerCourse(int idcourse);

    public List<Assignment> getAssignmentsPerCourse(int idcourse);

    public boolean removeAssignmentFromCourse(int idassignment, int idcourse);

    /////////////////////METHODS FOR SCHEDULE TABLE://///////////////////////////
    public boolean scheduleDayToCourse(Course course, Date date);

    public List<Schedule> viewSchedulePerCourse(Course course);

    public List<Schedule> getSchedulePerCourse(Course course);

    public boolean removeScheduleFromCourse(Course course, Date date);

}
