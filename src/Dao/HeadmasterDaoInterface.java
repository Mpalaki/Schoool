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
    public boolean insertCourse(Course course);

    public Map<Integer,Course> viewCourses();
    
    public Map<Integer,Course> getCourses();

    public Course viewCourseById(int idcouse);

    public void updateCourse(Course course);

    public boolean deleteCourse(int idcourse);

    public boolean appointStudentsToCourse(User student, Course course);

    public boolean appointTrainersToCourse(User trainer, Course course);

    public boolean appointAssignmentsToCourse(Assignment assignment, Course course);

    public boolean insertStudent(User student);

    public Map<Integer,User> viewStudents();
    
    public Map<Integer,User> getStudents();

    public User getUserById(int iduser);

    public void updateStudent(User student);

    public boolean deleteStudent(int idstudent);

    public boolean insertTrainer(User trainer);

    public Map<Integer,User> viewTrainers();
    
    public Map<Integer,User> getTrainers();

    public User viewTrainerById(int idtrainer);

    public User viewStudentById(int idstudent);

    public void updateTrainer(User trainer);

    public boolean deleteTrainer(int idtrainer);

    public boolean appointStudentToCourse(User student, Course course);

    public List<User> viewStudentsPerCourse(int idcourse);

    public boolean removeStudentFromCourse(int idcourse);
}
