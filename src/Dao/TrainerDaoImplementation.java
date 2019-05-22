/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.AssignmentUser;
import model.Course;
import model.User;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class TrainerDaoImplementation implements TrainerDao {

    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();

    @Override
    public List<Course> viewCoursesPerTrainersCourse(int idtrainer) {
        Connection conn = dbutils.createConnection();
        List<Course> courses = new ArrayList();// αρχικοποιω την λιστα με τa courses
        String sql = "select c.idcourse,c.course_title\n"
                + "from course c\n"
                + "inner join usercourse uc\n"
                + "on uc.idcourse=c.idcourse\n"
                + "where idusers=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idtrainer);
            rs = pst.executeQuery();
            User trainer = hm.getUserById(idtrainer); // καλω την getUserById που επιστρεφει το trainer object για το id pou zitise
            while (rs.next()) {
                Course course = new Course();
                int idcourse = rs.getInt("idcourse");
                course = hm.getCourseById(idcourse);
                courses.add(course);
            }

            System.out.println("The list of courses for trainer: '" + trainer.getFirstname() + " " + trainer.getLastname() + "' is the below:");
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(i + 1 + ". ID: " + courses.get(i).getIdcourse() + "; Title: " + courses.get(i).getCourse_title());
            }

        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return courses;
    }

    @Override
    public List<Course> getCoursesPerTrainersCourse(int idtrainer) {
        Connection conn = dbutils.createConnection();
        List<Course> courses = new ArrayList();// αρχικοποιω την λιστα με τa courses
        String sql = "select c.idcourse,c.course_title\n"
                + "from course c\n"
                + "inner join usercourse uc\n"
                + "on uc.idcourse=c.idcourse\n"
                + "where idusers=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idtrainer);
            rs = pst.executeQuery();
            User trainer = hm.getUserById(idtrainer); // καλω την getUserById που επιστρεφει το trainer object για το id pou zitise
            while (rs.next()) {
                Course course = new Course();
                int idcourse = rs.getInt("idcourse");
                course = hm.getCourseById(idcourse);
                courses.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return courses;
    }

    @Override
    public List<User> viewStudents(int idtrainer, int idcourse) {
        User trainer = hm.getUserById(idtrainer);// returns object trainer
        List<User> students = null;// initialize list with students
        List<Course> courses = getCoursesPerTrainersCourse(idtrainer);// returns the courses the trainer is enrolled in
        Course course = hm.getCourseById(idcourse);// returns object course
        if (courses.contains(course)) {// if the given idcourse belongs to a course the trainer is enrolled in
            students = hm.getStudentsPerCourse(idcourse);// returns list with all students for the given course
            if (students.isEmpty()) {
                System.out.println("No students enrolled for the course.");
            } else {
                System.out.println("The list of students for course: '" + course.getIdcourse() + ", " + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println(i + 1 + ". ID: " + students.get(i).getIduser() + "; Νame: " + students.get(i).getFirstname() + " " + students.get(i).getLastname());
                }
            }

        } else {
            System.out.println("No such course record among your enrollments.");
        }

        return students;
    }

    @Override
    public List<AssignmentUser> viewAssignmentsPerStudentPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = hm.getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<AssignmentUser> assignmentusers = new ArrayList();// αρχικοποιω την λιστα με τους assignmentusers
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title,a.submission_date_time,u.idusers,u.first_name,u.last_name\n"
                + "from assignment a\n"
                + "inner join assignmentcourse ac\n"
                + "on a.idassignment=ac.idassignment\n"
                + "inner join course c\n"
                + "on ac.idcourse=c.idcourse\n"
                + "inner join studentcourse sc\n"
                + "on c.idcourse=sc.idcourse\n"
                + "inner join users u\n"
                + "on sc.idusers=u.idusers\n"
                + "where c.idcourse=?\n"
                + "order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = hm.getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    AssignmentUser au = new AssignmentUser();
                    User student = new User();
                    Assignment assignment = new Assignment();
                    int idstudent = rs.getInt("idusers");
                    student = hm.getUserById(idstudent);
                    int idassignment = rs.getInt("idassignment");
                    assignment = hm.getAssignmentById(idassignment);
                    au.setAssignment(assignment);
                    au.setUser(student);
                    assignmentusers.add(au);
                }
                if (assignmentusers.isEmpty()) {
                    List assignments = hm.getAssignmentsPerCourse(course.getIdcourse());// returns all assignments for the course
                    if (assignments.isEmpty()) {
                        List students = hm.getStudentsPerCourse(course.getIdcourse());// returns all students for the course
                        if (students.isEmpty()) {
                            System.out.println("No assignments, neither any students appointed to this course.");
                        } else {
                            System.out.println("No assignments appointed to this course.");
                        }
                    } else {
                        System.out.println("No students appointed to this course");
                    }
                } else {
                    System.out.println("The list of assignemnts per students for course: '" + course.getCourse_title() + "' is the below:");
                    for (int i = 0; i < assignmentusers.size(); i++) {
                        System.out.println(i + 1 + ". Assignment ID: " + assignmentusers.get(i).getAssignment().getIdassignment()
                                + ", Assignment title: " + assignmentusers.get(i).getAssignment().getTitle() + ", "
                                + "Student ID: " + assignmentusers.get(i).getUser().getIduser()
                                + " Student name: " + assignmentusers.get(i).getUser().getFirstname() + " " + assignmentusers.get(i).getUser().getLastname());///////να το φτιαξω//////////
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            System.out.println("No such course record.");
        }

        return assignmentusers;
    }

    @Override
    public List<AssignmentUser> getAssignmentsPerStudentPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = hm.getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<AssignmentUser> assignmentusers = new ArrayList();// αρχικοποιω την λιστα με τους assignmentusers
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title,a.submission_date_time,u.idusers,u.first_name,u.last_name\n"
                + "from assignment a\n"
                + "inner join assignmentcourse ac\n"
                + "on a.idassignment=ac.idassignment\n"
                + "inner join course c\n"
                + "on ac.idcourse=c.idcourse\n"
                + "inner join studentcourse sc\n"
                + "on c.idcourse=sc.idcourse\n"
                + "inner join users u\n"
                + "on sc.idusers=u.idusers\n"
                + "where c.idcourse=?\n"
                + "order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = hm.getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    AssignmentUser au = new AssignmentUser();
                    User student = new User();
                    Assignment assignment = new Assignment();
                    int idstudent = rs.getInt("idusers");
                    student = hm.getUserById(idstudent);
                    int idassignment = rs.getInt("idassignment");
                    assignment = hm.getAssignmentById(idassignment);
                    au.setAssignment(assignment);
                    au.setUser(student);
                    assignmentusers.add(au);
                }

            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            System.out.println("No such course record.");
        }

        return assignmentusers;
    }

//    @Override
//    public boolean updateAssignmentCourseStudentTable(int idcourse) {
//        Connection conn = dbutils.createConnection();
//        String sql = " INSERT INTO assignmentcoursestudent (idassignment,idcourse,idusers)"
//                + "VALUES (?,?,?);";
//        List<AssignmentUser> assignmentusers = getAssignmentsPerStudentPerCourse(idcourse);// returns all assignmentusers per cpurse
//        for (AssignmentUser au : assignmentusers) {
//            try {
//                PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                int idassignment = au.getAssignment().getIdassignment();
//                preparedStatement.setInt(1, idassignment);
//                preparedStatement.setInt(2, idcourse);
//                int idusers = au.getUser().getIduser();
//                preparedStatement.setInt(3, idusers);
//                
//                preparedStatement.addBatch();
//                
//            } catch (SQLException ex) {
//
//                if (ex instanceof SQLIntegrityConstraintViolationException) {
//                } else {
//                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class
//                            .getName()).log(Level.SEVERE, null, ex);
//                }
//
//            } finally {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return true;
//
//    }
    @Override
    public boolean markAssignmentPerStudentPerCourse(int idassignment, int idstudent, int idcourse, int mark) {
        List<AssignmentUser> assignmentusers = getAssignmentsPerStudentPerCourse(idcourse);
        for (int i = 0; i < assignmentusers.size(); i++) {
            if (assignmentusers.get(i).getAssignment().getIdassignment() == idassignment
                    && assignmentusers.get(i).getUser().getIduser() == idstudent) {
                Connection conn = dbutils.createConnection();
                String sql = " INSERT INTO assignmentcoursestudent (idassignment,idcourse,idusers,mark,submitted)"
                        + "VALUES (?,?,?,?,?);";
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, idassignment);
                    preparedStatement.setInt(2, idstudent);
                    preparedStatement.setInt(3, idcourse);
                    preparedStatement.setInt(4, mark);
                    preparedStatement.setInt(5, 1);

                    preparedStatement.executeUpdate();
                    System.out.println("Assignment marked.");

                } catch (SQLException ex) {

                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println("The assignment is already marked.");

                    } else {
                        Logger.getLogger(HeadmasterDaoInterfaceImplementation.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                return true;
            } else {
                if (i == assignmentusers.size() - 1) {
                    System.out.println("No such record.");
                }
            }
        }
        return true;
    }

}
