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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.AssignmentCourse;
import model.AssignmentUser;
import model.Course;
import model.User;
import model.UserCourse;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class StudentDaoImplementation implements StudentDao {

    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();

    @Override
    public List<Course> viewDailySchedule(int idstudent, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        Connection conn = dbutils.createConnection();
//        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
//        List<User> trainers = new ArrayList();// αρχικοποιω την λιστα με τους trainers
//        String sql = "select c.idcourse,c.course_title,u.idusers,u.first_name,u.last_name\n"
//                + "from course c  \n"
//                + "inner join trainercourse a\n"
//                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
//                + "inner join users u\n"
//                + "on u.idusers=a.idusers where u.idrole=2 order by c.idcourse";
//        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
//            try {
//                PreparedStatement pst = conn.prepareStatement(sql);
//                ResultSet rs = null;
//                pst.setInt(1, idcourse);
//                rs = pst.executeQuery();
//                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
//                while (rs.next()) {
//                    User trainer = new User();
//                    int idtrainer = rs.getInt("idusers");
//                    trainer = getUserById(idtrainer);
//                    trainers.add(trainer);
//                }
//
//                System.out.println("The list of trainers for course: '" + course.getCourse_title() + "' is the below:");
//                for (int i = 0; i < trainers.size(); i++) {
//                    System.out.println(i + 1 + ". " + trainers.get(i).getFirstname() + " " + trainers.get(i).getLastname());
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        } else {
//            System.out.println("No such course record.");
//        }
//
//        return trainers;
    }

    @Override
    public List<Assignment> viewSubmissionDatesOfAssignmentsPerCourse(int idcourse) {
        List<Assignment> assignments = hm.getAssignmentsPerCourse(idcourse);
        Course course = hm.getCourseById(idcourse);
        System.out.println("The submission dates for the assignments for course: '" + course.getCourse_title() + "' is the below:");
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + ". " + assignments.get(i).getTitle() + ", submission date and time: " + assignments.get(i).getSubmission_date_time());
        }
        return assignments;
    }

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

    @Override
    public boolean submitAssignment(int idassignment, int idstudent, int idcourse) {
        List<AssignmentCourse> assignmentscourses = getAssignmentsPerCoursePerStudent(idstudent);
        if (assignmentscourses.isEmpty()) {
            return false;
        } else {
            Connection conn = dbutils.createConnection();
            String sql = "insert into assignmentcoursestudent(idassignment,idcourse,idusers,submitted)\n"
                    + "values(?,?,?,?);";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, idassignment);
                preparedStatement.setInt(2, idcourse);
                preparedStatement.setInt(3, idstudent);
                preparedStatement.setBoolean(4, true);
                preparedStatement.executeUpdate();

                System.out.println("Updated successfully.");
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    @Override
    public List<AssignmentCourse> viewAssignmentsPerCoursePerStudent(int idstudent) {
        Connection conn = dbutils.createConnection();
        List<AssignmentCourse> assignmentcourses = new ArrayList();// αρχικοποιω την λιστα με τους AssignmentCourse
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title,a.submission_date_time,u.idusers,u.first_name,u.last_name,b.mark,b.submitted\n"
                + "from assignment a\n"
                + "inner join assignmentcourse ac\n"
                + "on a.idassignment=ac.idassignment\n"
                + "inner join course c\n"
                + "on ac.idcourse=c.idcourse\n"
                + "inner join studentcourse sc\n"
                + "on c.idcourse=sc.idcourse\n"
                + "inner join users u\n"
                + "on sc.idusers=u.idusers\n"
                + "left join assignmentcoursestudent b\n"
                + "on b.idusers=u.idusers \n"
                + "where u.idusers=?\n"
                + "order by c.idcourse";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idstudent);
            rs = pst.executeQuery();
            User student = hm.getUserById(idstudent); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
            while (rs.next()) {
                AssignmentCourse ac = new AssignmentCourse();
                Assignment assignment = new Assignment();
                student = hm.getUserById(idstudent);
                int idassignment = rs.getInt("idassignment");
                assignment = hm.getAssignmentById(idassignment);
                int idcourse = rs.getInt("idcourse");
                Course course = hm.getCourseById(idcourse);
                int mark = rs.getInt("mark");
                ac.setMark(mark);
                boolean submitted = rs.getBoolean("submitted");
                ac.setSubmitted(submitted);
                ac.setAssignment(assignment);
                ac.setCourse(course);
                assignmentcourses.add(ac);
            }
            if (assignmentcourses.isEmpty()) {
                System.out.println("No assignments for you. ");

//                List assignments = hm.getAssignmentsPerCourse(course.getIdcourse());// returns all assignments for the course
//                if (assignments.isEmpty()) {
//                    List students = hm.getStudentsPerCourse(course.getIdcourse());// returns all students for the course
//                    if (students.isEmpty()) {
//                        System.out.println("No assignments, neither any students appointed to this course.");
//                    } else {
//                        System.out.println("No assignments appointed to this course.");
//                    }
//                } else {
//                    System.out.println("No students appointed to this course");
//                }
            } else {
                System.out.println("The list of assignemnts per course for student: '" + student.getFirstname() + " " + student.getLastname() + "' is the below:");
                for (int i = 0; i < assignmentcourses.size(); i++) {
                    System.out.println(i + 1 + ". Assignment ID: " + assignmentcourses.get(i).getAssignment().getIdassignment()
                            + ", Assignment title: " + assignmentcourses.get(i).getAssignment().getTitle() + ", "
                            + "Course ID: " + assignmentcourses.get(i).getCourse().getIdcourse()
                            + " Course name: " + assignmentcourses.get(i).getCourse().getCourse_title()
                            + ", mark= " + assignmentcourses.get(i).getMark() + ", " + assignmentcourses.get(i).toString());
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

        return assignmentcourses;
    }

    public List<AssignmentCourse> getAssignmentsPerCoursePerStudent(int idstudent) {
        Connection conn = dbutils.createConnection();
        List<AssignmentCourse> assignmentcourses = new ArrayList();// αρχικοποιω την λιστα με τους AssignmentCourse
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title,a.submission_date_time,u.idusers,u.first_name,u.last_name,b.mark,b.submitted\n"
                + "from assignment a\n"
                + "inner join assignmentcourse ac\n"
                + "on a.idassignment=ac.idassignment\n"
                + "inner join course c\n"
                + "on ac.idcourse=c.idcourse\n"
                + "inner join studentcourse sc\n"
                + "on c.idcourse=sc.idcourse\n"
                + "inner join users u\n"
                + "on sc.idusers=u.idusers\n"
                + "left join assignmentcoursestudent b\n"
                + "on b.idusers=u.idusers \n"
                + "where u.idusers=?\n"
                + "order by c.idcourse";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idstudent);
            rs = pst.executeQuery();
            User student = hm.getUserById(idstudent); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
            while (rs.next()) {
                AssignmentCourse ac = new AssignmentCourse();
                Assignment assignment = new Assignment();
                student = hm.getUserById(idstudent);
                int idassignment = rs.getInt("idassignment");
                assignment = hm.getAssignmentById(idassignment);
                int idcourse = rs.getInt("idcourse");
                Course course = hm.getCourseById(idcourse);
                int mark = rs.getInt("mark");
                ac.setMark(mark);
                boolean submitted = rs.getBoolean("submitted");
                ac.setSubmitted(submitted);
                ac.setAssignment(assignment);
                ac.setCourse(course);
                assignmentcourses.add(ac);
            }
            if (assignmentcourses.isEmpty()) {
                System.out.println("No assignments for you. ");

            } else {
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

        return assignmentcourses;
    }

}
