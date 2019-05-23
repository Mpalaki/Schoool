/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dao.HeadmasterDaoInterfaceImplementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class AssignmentCourseStudent {

    private Assignment assignment;
    private Course course;
    private User student;
    private int mark;

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public AssignmentCourseStudent(Assignment assignment, Course course, User student, int mark) {
        this.assignment = assignment;
        this.course = course;
        this.student = student;
        this.mark = mark;
    }

    public AssignmentCourseStudent() {
    }

    public AssignmentCourseStudent(Assignment assignment, Course course, User student) {
        this.assignment = assignment;
        this.course = course;
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Course getCourse() {
        return course;
    }

    public User getStudent() {
        return student;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.assignment);
        hash = 29 * hash + Objects.hashCode(this.course);
        hash = 29 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssignmentCourseStudent other = (AssignmentCourseStudent) obj;
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssignmentCourseStudent{" + "assignment=" + assignment + ", course=" + course + ", student=" + student + ", mark=" + mark + '}';
    }

    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();

    public List<AssignmentCourseStudent> listOfSubmittedAssignments() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from assignmentcoursestudent";
        List<AssignmentCourseStudent> submittedAssignments = new ArrayList();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentCourseStudent acs = new AssignmentCourseStudent();
                int idassignment = rs.getInt("idassignment");
                Assignment assignment = hm.getAssignmentById(idassignment);
                acs.setAssignment(assignment);
                int idcourse = rs.getInt("idcourse");
                Course course = hm.getCourseById(idcourse);
                acs.setCourse(course);
                int idstudent = rs.getInt("idusers");
                User student = hm.getUserById(idstudent);
                acs.setStudent(student);
                int mark = rs.getInt("mark");
                acs.setMark(mark);

                submittedAssignments.add(acs);
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

        return submittedAssignments;
    }

    public AssignmentCourseStudent getSubmittedAssignmentCourseStudentFromIds(int idassignment, int idcourse, int idstudent) {
//        Assignment as = hm.getAssignmentById(idassignment);
//        User s = hm.getUserById(idstudent);
//        Course c = hm.getCourseById(idcourse);
//        AssignmentCourseStudent acs = new AssignmentCourseStudent(as, c, s);
//        return acs;
        Connection conn = dbutils.createConnection();
        String sql = "select * from assignmentcoursestudent where idusers=? and idassignment=? and idcourse=? ;";
        User user = new User();
        AssignmentCourseStudent acs = new AssignmentCourseStudent();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idstudent);
            pst.setInt(2, idassignment);
            pst.setInt(3, idcourse);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = hm.getAssignmentById(idassignment);
                acs.setAssignment(assignment);
                Course course = hm.getCourseById(idcourse);
                acs.setCourse(course);
                User student = hm.getUserById(idstudent);
                acs.setStudent(student);
                int mark = rs.getInt("mark");
                acs.setMark(mark);

            }
        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return acs;
    }

    public boolean isAssignmentSubmitted(int idassignment, int idcourse, int idstudent) {
        AssignmentCourseStudent acs = getSubmittedAssignmentCourseStudentFromIds(idassignment, idcourse, idstudent);
        List<AssignmentCourseStudent> l = listOfSubmittedAssignments();
        if (l.contains(acs)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAssignmentMarked(int idassignment, int idcourse, int idstudent) {
        AssignmentCourseStudent acs = getAssignmentCourseStudentFromIds(idassignment, idcourse, idstudent);
        if (acs.getMark() < 0) {
            return false;
        } else {
            return true;
        }
    }

    public AssignmentCourseStudent getAssignmentCourseStudentFromIds(int idassignment, int idcourse, int idstudent) {
        AssignmentCourseStudent acs = getSubmittedAssignmentCourseStudentFromIds(idassignment, idcourse, idstudent);
        List<AssignmentCourseStudent> l = listOfSubmittedAssignments();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).equals(acs)) {
                return acs;
            }
        }
        return acs;
    }

}