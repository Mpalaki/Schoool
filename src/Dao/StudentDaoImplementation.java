/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.AssignmentCourse;
import model.AssignmentUser;
import model.Course;
import model.Schedule;
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
    public List<Schedule> viewDailySchedule(int idstudent, String date) {
        List<Schedule> schedules = new ArrayList();// αρχικοποιω την λιστα με τους schedules
        try {
            Connection conn = dbutils.createConnection();
            String sql = "SELECT studentcourse.idcourse,working_day\n"
                    + "FROM schedules\n"
                    + "inner join\n"
                    + "studentcourse\n"
                    + "on schedules.idcourse=studentcourse.idcourse\n"
                    + "where working_day=? and studentcourse.idusers=?;";
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(utilDate);
            c.add(Calendar.DATE, 1);
            utilDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // το date mou
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setDate(1, sqlDate);
                pst.setInt(2, idstudent);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    Course course = new Course();
                    int idcourse = rs.getInt("idcourse");
                    course = hm.getCourseById(idcourse);
                    schedule.setIdcourse(idcourse);
                    Date working_day = rs.getDate("working_day");
                    schedules.add(schedule);
                }

                System.out.println("The list of scheduled courses for date: '" + date + "' is the below:");
                for (int i = 0; i < schedules.size(); i++) {
                    Course c1 = hm.getCourseById(schedules.get(i).getIdcourse());
                    System.out.println(i + 1 + ". " + schedules.get(i).getIdcourse() + ", "
                            + c1.getCourse_title());
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

        } catch (ParseException ex) {
            Logger.getLogger(StudentDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    @Override
    public List<AssignmentCourse> viewSubmissionDatesOfAssignmentsPerCourse(int idstudent) {
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
                System.out.println("The list of assignemnts per course for student: '" + student.getFirstname() + " " + student.getLastname() + "' is the below:");
                for (int i = 0; i < assignmentcourses.size(); i++) {
                    System.out.println(i + 1 +"Submission datetime: "+assignmentcourses.get(i).getAssignment().getSubmission_date_time()+
                            ". Assignment ID: " + assignmentcourses.get(i).getAssignment().getIdassignment()
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

                System.out.println("Submit successful.");
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
                + "on b.idusers=u.idusers and a.idassignment=b.idassignment and c.idcourse=b.idcourse \n"
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
