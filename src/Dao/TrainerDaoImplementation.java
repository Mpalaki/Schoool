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
    public List<Course> viewCourses(int idtrainer) {
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
    public List<Course> getCourses(int idtrainer) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
