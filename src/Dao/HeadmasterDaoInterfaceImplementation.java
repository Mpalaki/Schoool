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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Course;
import model.User;
import model.UserCourse;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class HeadmasterDaoInterfaceImplementation implements HeadmasterDaoInterface {

    public HeadmasterDaoInterfaceImplementation() {
    }

    /////////////////////METHODS FOR COURSES://///////////////////////////
    @Override
    public boolean insertCourse(Course course) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO course (course_title)"
                + "VALUES (?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, course.getCourse_title());
            preparedStatement.executeUpdate();

            System.out.println("Successful insert.");

        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public Map<Integer, Course> viewCourses() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from course";
        Map<Integer, Course> AllCourses = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                int idcourse = rs.getInt("idcourse");
                course.setIdcourse(idcourse);
                String course_title = rs.getString("course_title");
                course.setCourse_title(course_title);

                AllCourses.put(course.getIdcourse(), course);
            }
            System.out.println("The list of courses is the below:");
            int i = 1;
            for (int id : AllCourses.keySet()) {
                System.out.println(i + ". " + AllCourses.get(id));
                i++;
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

        return AllCourses;
    }

    @Override
    public Course viewCourseById(int idcouse) {
        Connection conn = dbutils.createConnection();
        String sql = "select * from course where idcourse=?";
        Course course = new Course();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idcouse);
            rs = pst.executeQuery();
            while (rs.next()) {
                course.setIdcourse(idcouse);
                String course_title = rs.getString("course_title");
                course.setCourse_title(course_title);

            }
            System.out.println(course.toString());
        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return course;
    }

    @Override
    public void updateCourse(Course course) {
        Connection conn = dbutils.createConnection();
        String sql = "update course set course_title=? where idcourse=? ;";
        Map<Integer, Course> AllCourses = getCourses();
        if (AllCourses.containsKey(course.getIdcourse())) { // Checking if the requested record exists in the database.

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, course.getCourse_title());
                preparedStatement.setInt(2, course.getIdcourse());
                preparedStatement.executeUpdate();

                System.out.println("Updated successfully.");

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
            System.out.println("No such record exists.");
        }

    }

    @Override
    public boolean deleteCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from course where idcourse=" + idcourse;
        Map<Integer, Course> AllCourses = getCourses();
        if (AllCourses.containsKey(idcourse)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("Successful deletion.");

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

            return true;
        } else {
            System.out.println("No such record exists.");
            return false;
        }
    }

    @Override
    public Map<Integer, Course> getCourses() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from course";
        Map<Integer, Course> AllCourses = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                int idcourse = rs.getInt("idcourse");
                course.setIdcourse(idcourse);
                String course_title = rs.getString("course_title");
                course.setCourse_title(course_title);

                AllCourses.put(course.getIdcourse(), course);
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

        return AllCourses;
    }

    /////////////////////METHODS FOR STUDENTS://///////////////////////////
    @Override
    public boolean insertStudent(User student) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO users (first_name, last_name, username, passw0rd, idrole)"
                + "VALUES (?,?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setString(3, student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5, 1);

            preparedStatement.executeUpdate();
            System.out.println("Successful insert.");

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
        return true;
    }

    @Override
    public Map<Integer, User> viewStudents() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idrole=1";
        Map<Integer, User> AllStudents = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                User student = new User();
                int idusers = rs.getInt("idusers");
                student.setIduser(idusers);
                String first_name = rs.getString("first_name");
                student.setFirstname(first_name);
                String last_name = rs.getString("last_name");
                student.setLastname(last_name);
                String username = rs.getString("username");
                student.setUsername(username);
                String password = rs.getString("passw0rd");
                student.setPassword(password);
                int idrole = rs.getInt("idrole");
                student.setIdrole(idrole);

                AllStudents.put(student.getIduser(), student);
            }
            System.out.println("The list of students is the below:");
            int i = 1;
            for (int id : AllStudents.keySet()) {
                System.out.println(i + ". " + AllStudents.get(id));
                i++;
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

        return AllStudents;
    }

    @Override
    public void updateStudent(User student) {
        Connection conn = dbutils.createConnection();
        String sql = "update users set first_name=? , last_name=? , username=? , passw0rd=? , idrole=? where idusers=? ;";
        Map<Integer, User> allStudents = getStudents();
        if (allStudents.containsKey(student.getIduser())) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, student.getFirstname());
                preparedStatement.setString(2, student.getLastname());
                preparedStatement.setString(3, student.getUsername());
                preparedStatement.setString(4, student.getPassword());
                preparedStatement.setInt(5, 1);
                preparedStatement.setInt(6, student.getIduser());
                preparedStatement.executeUpdate();
                System.out.println("Successful update.");

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
        } else {
            System.out.println("No such student record (ID: " + student.getIduser() + ") exists.");
        }
    }

    @Override
    public boolean deleteStudent(int idusers) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from users where idusers=" + idusers;
        Map<Integer, User> allStudents = getStudents();
        if (allStudents.containsKey(idusers)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("Successful deletion.");

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

            return true;

        } else {
            System.out.println("No such student record (ID: " + idusers + ") exists.");
            return false;
        }
    }

    @Override
    public User viewStudentById(int idstudent) { // THELEI DIORTHOSI AN APOFASISO NA TIN KRATISO///////////////
        User student = getUserById(idstudent);
        int role = student.getIdrole();
        if (role == 1) {
            Connection conn = dbutils.createConnection();
            String sql = "select * from users where idusers=?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idstudent);
                rs = pst.executeQuery();
                while (rs.next()) {
                    student.setIduser(idstudent);
                    String firstname = rs.getString("first_name");
                    student.setFirstname(firstname);
                    String lastname = rs.getString("last_name");
                    student.setLastname(lastname);
                    String username = rs.getString("username");
                    student.setUsername(username);
                    student.setIdrole(1);

                    System.out.println(student.toString());

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

        } else {
            System.out.println("the record with the id: " + student.getIduser() + " is not a record for student");
        }
        return student;
    }

    @Override
    public Map<Integer, User> getStudents() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idrole=1";
        Map<Integer, User> AllStudents = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                User student = new User();
                int idusers = rs.getInt("idusers");
                student.setIduser(idusers);
                String first_name = rs.getString("first_name");
                student.setFirstname(first_name);
                String last_name = rs.getString("last_name");
                student.setLastname(last_name);
                String username = rs.getString("username");
                student.setUsername(username);
                String password = rs.getString("passw0rd");
                student.setPassword(password);
                int idrole = rs.getInt("idrole");
                student.setIdrole(idrole);

                AllStudents.put(student.getIduser(), student);
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

        return AllStudents;
    }

    /////////////////////METHODS FOR USERS://///////////////////////////
    @Override
    public User getUserById(int iduser) {// THELEI DIORTHOSI I PETAMA ////////////////////
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idusers=?";
        User user = new User();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, iduser);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setIduser(iduser);
                String firstname = rs.getString("first_name");
                user.setFirstname(firstname);
                String lastname = rs.getString("last_name");
                user.setLastname(lastname);
                String username = rs.getString("username");
                user.setUsername(username);
                int userrole = rs.getInt("idrole");
                user.setIdrole(userrole);

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

        return user;
    }

    /////////////////////METHODS FOR TRAINERS://///////////////////////////
    @Override
    public boolean insertTrainer(User trainer) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO users (first_name, last_name, username, passw0rd, idrole)"
                + "VALUES (?,?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, trainer.getFirstname());
            preparedStatement.setString(2, trainer.getLastname());
            preparedStatement.setString(3, trainer.getUsername());
            preparedStatement.setString(4, trainer.getPassword());
            preparedStatement.setInt(5, 2);

            preparedStatement.executeUpdate();
            System.out.println("Successful insert.");

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
        return true;
    }

    @Override
    public Map<Integer, User> viewTrainers() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idrole=2";
        Map<Integer, User> AllTrainers = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                User trainer = new User();
                int idusers = rs.getInt("idusers");
                trainer.setIduser(idusers);
                String first_name = rs.getString("first_name");
                trainer.setFirstname(first_name);
                String last_name = rs.getString("last_name");
                trainer.setLastname(last_name);
                String username = rs.getString("username");
                trainer.setUsername(username);
                String password = rs.getString("passw0rd");
                trainer.setPassword(password);
                int idrole = rs.getInt("idrole");
                trainer.setIdrole(idrole);

                AllTrainers.put(trainer.getIduser(), trainer);
            }
            System.out.println("The list of trainers is the below:");
            int i = 1;
            for (int id : AllTrainers.keySet()) {
                System.out.println(i + ". " + AllTrainers.get(id));
                i++;
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

        return AllTrainers;
    }

    @Override
    public User viewTrainerById(int idtrainer) {//////////AN TIN KRATISO NA TIN FTIAKSO//////////////
        User trainer = getUserById(idtrainer);
        int role = trainer.getIdrole();
        if (role == 2) {
            Connection conn = dbutils.createConnection();
            String sql = "select * from users where idusers=?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idtrainer);
                rs = pst.executeQuery();
                while (rs.next()) {
                    trainer.setIduser(idtrainer);
                    String firstname = rs.getString("first_name");
                    trainer.setFirstname(firstname);
                    String lastname = rs.getString("last_name");
                    trainer.setLastname(lastname);
                    String username = rs.getString("username");
                    trainer.setUsername(username);
                    trainer.setIdrole(2);

                    System.out.println(trainer.toString());

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

        } else {
            System.out.println("the record with the id: " + trainer.getIduser() + " is not a record for trainer");
        }
        return trainer;
    }

    @Override
    public void updateTrainer(User trainer) {
        Connection conn = dbutils.createConnection();
        String sql = "update users set first_name=? , last_name=? , username=? , passw0rd=? , idrole=? where idusers=? ;";
        Map<Integer, User> allTrainers = getTrainers();
        if (allTrainers.containsKey(trainer.getIduser())) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, trainer.getFirstname());
                preparedStatement.setString(2, trainer.getLastname());
                preparedStatement.setString(3, trainer.getUsername());
                preparedStatement.setString(4, trainer.getPassword());
                preparedStatement.setInt(5, 2);
                preparedStatement.setInt(6, trainer.getIduser());
                preparedStatement.executeUpdate();

                System.out.println("Succesfull update.");

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
        } else {
            System.out.println("No such trainer record (ID: " + trainer.getIduser() + ") exists.");
        }
    }

    @Override
    public boolean deleteTrainer(int idtrainer) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from users where idusers=" + idtrainer;
        Map<Integer, User> allTrainers = getTrainers();
        if (allTrainers.containsKey(idtrainer)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();

                System.out.println("Successful deletion.");

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

            return true;
        } else {
            System.out.println("No such trainer record (ID: " + idtrainer + ") exists.");
            return false;
        }
    }

    @Override
    public Map<Integer, User> getTrainers() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idrole=2";
        Map<Integer, User> AllTrainers = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                User trainer = new User();
                int idusers = rs.getInt("idusers");
                trainer.setIduser(idusers);
                String first_name = rs.getString("first_name");
                trainer.setFirstname(first_name);
                String last_name = rs.getString("last_name");
                trainer.setLastname(last_name);
                String username = rs.getString("username");
                trainer.setUsername(username);
                String password = rs.getString("passw0rd");
                trainer.setPassword(password);
                int idrole = rs.getInt("idrole");
                trainer.setIdrole(idrole);

                AllTrainers.put(trainer.getIduser(), trainer);
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

        return AllTrainers;
    }

    /////////////////////METHODS FOR ASSIGNMENTS://///////////////////////////
    @Override
    public boolean insertAssignment(Assignment assignment) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO assignment (assignment_title, submission_date_time)"
                + "VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, assignment.getTitle());
            preparedStatement.setTimestamp(2, assignment.getSubmission_date_time());

            preparedStatement.executeUpdate();
            System.out.println("Successful insert.");

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
        return true;
    }

    @Override
    public Map<Integer, Assignment> viewAssignments() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from assignment";
        Map<Integer, Assignment> allAssignments = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = new Assignment();
                int idassignment = rs.getInt("idassignment");
                assignment.setIdassignment(idassignment);
                String assignment_title = rs.getString("assignment_title");
                assignment.setTitle(assignment_title);
                Timestamp submission_date_time = rs.getTimestamp("submission_date_time");
                assignment.setSubmission_date_time(submission_date_time);

                allAssignments.put(assignment.getIdassignment(), assignment);
            }
            System.out.println("The list of assignments is the below:");
            int i = 1;
            for (int id : allAssignments.keySet()) {
                System.out.println(i + ". " + allAssignments.get(id));
                i++;
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

        return allAssignments;
    }

    @Override
    public Map<Integer, Assignment> getAssignments() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from assignment";
        Map<Integer, Assignment> allAssignments = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = new Assignment();
                int idassignment = rs.getInt("idassignment");
                assignment.setIdassignment(idassignment);
                String assignment_title = rs.getString("assignment_title");
                assignment.setTitle(assignment_title);
                Timestamp submission_date_time = rs.getTimestamp("submission_date_time");
                assignment.setSubmission_date_time(submission_date_time);

                allAssignments.put(assignment.getIdassignment(), assignment);
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

        return allAssignments;
    }

    @Override
    public Assignment viewAssignmentById(int idassignment) {///////////NA TIN ALLAKSO AN TIN KRATISO/////////////
        Connection conn = dbutils.createConnection();
        String sql = "select * from assignment where idassignment=?";
        Assignment assignment = new Assignment();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, idassignment);
            rs = pst.executeQuery();
            while (rs.next()) {
                assignment.setIdassignment(idassignment);
                String assignment_title = rs.getString("assignment_title");
                assignment.setTitle(assignment_title);
                Timestamp submission_date_time = rs.getTimestamp("submission_date_time");
                assignment.setSubmission_date_time(submission_date_time);

            }
            System.out.println(assignment.toString());
        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return assignment;
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        Connection conn = dbutils.createConnection();
        String sql = "update assignment set assignment_title=?, submission_date_time=? where idassignment=? ;";
        Map<Integer, Assignment> allAssignments = getAssignments();
        if (allAssignments.containsKey(assignment.getIdassignment())) { // Checking if the requested record exists in the database.

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, assignment.getTitle());
                preparedStatement.setTimestamp(2, assignment.getSubmission_date_time());
                preparedStatement.setInt(3, assignment.getIdassignment());
                preparedStatement.executeUpdate();

                System.out.println("Updated successfully.");

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
            System.out.println("No such record exists.");
        }
    }

    @Override
    public boolean deleteAssignment(int idassignment) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from assignment where idassignment=" + idassignment;
        Map<Integer, Assignment> allAssignments = getAssignments();
        if (allAssignments.containsKey(idassignment)) { // Checking if the requested record exists in the database.

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("Successful deletion.");

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

            return true;
        } else {
            System.out.println("No such record exists.");
            return false;
        }
    }

    /////////////////////METHODS FOR JUNCTION TABLES://///////////////////////////
    @Override
    public boolean appointStudentsToCourse(int idstudent, int idcourse) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO usercourse (idusers,idcourse)"
                + "VALUES (?,?);";
        Map<Integer, User> allStudents = getStudents();
        Map<Integer, Course> allCourses = getCourses();
        if (allStudents.containsKey(idstudent)) {
            if (allCourses.containsKey(idcourse)) {
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setInt(1, idstudent);
                    preparedStatement.setInt(2, idcourse);

                    preparedStatement.executeUpdate();
                    System.out.println("Successful appointment.");

                } catch (SQLException ex) {

                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println("The student is already appointed to this course.");
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
                System.out.println("There is not any course with the ID: " + idcourse);
                return false;
            }
        } else {
            if (allCourses.containsKey(idcourse)) {
                System.out.println("There is not any student with the ID: " + idstudent);
                return false;
            } else {
                System.out.println("There is not any student with the ID: " + idstudent + " neither"
                        + " any course with the ID: " + idcourse);
                return false;
            }

        }
    }

    @Override
    public boolean appointTrainersToCourse(int idtrainer, int idcourse) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO usercourse (idusers,idcourse)"
                + "VALUES (?,?);";
        Map<Integer, User> allTrainers = getTrainers();
        Map<Integer, Course> allCourses = getCourses();
        if (allTrainers.containsKey(idtrainer)) {
            if (allCourses.containsKey(idcourse)) {
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setInt(1, idtrainer);
                    preparedStatement.setInt(2, idcourse);

                    preparedStatement.executeUpdate();
                    System.out.println("Successful appointment.");

                } catch (SQLException ex) {

                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println("The trainer is already appointed to this course.");
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
                System.out.println("There is not any course with the ID: " + idcourse);
                return false;
            }
        } else {
            if (allCourses.containsKey(idcourse)) {
                System.out.println("There is not any trainer with the ID: " + idtrainer);
                return false;
            } else {
                System.out.println("There is not any trainer with the ID: " + idtrainer + " neither"
                        + " any course with the ID: " + idcourse);
                return false;
            }

        }
    }

    @Override
    public boolean appointAssignmentsToCourse(int idassignment, int idcourse) {
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO assignmentcourse (idassignment,idcourse)"
                + "VALUES (?,?);";
        Map<Integer, Assignment> allAssignments = getAssignments();
        Map<Integer, Course> allCourses = getCourses();
        if (allAssignments.containsKey(idassignment)) {
            if (allCourses.containsKey(idcourse)) {
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setInt(1, idassignment);
                    preparedStatement.setInt(2, idcourse);

                    preparedStatement.executeUpdate();
                    System.out.println("Successful appointment.");

                } catch (SQLException ex) {

                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println("The assignment is already appointed to this course.");
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
                System.out.println("There is not any course with the ID: " + idcourse);
                return false;
            }
        } else {
            if (allCourses.containsKey(idcourse)) {
                System.out.println("There is not any assignment with the ID: " + idassignment);
                return false;
            } else {
                System.out.println("There is not any assignment with the ID: " + idassignment + " neither"
                        + " any course with the ID: " + idcourse);
                return false;
            }

        }
    }

    @Override
    public List<User> getStudentsPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<User> students = new ArrayList();// αρχικοποιω την λιστα με τους μαθητες
        String sql = "select c.idcourse,c.course_title,u.idusers,u.first_name,u.last_name\n"
                + "from course c  \n"
                + "inner join usercourse a\n"
                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
                + "inner join users u\n"
                + "on u.idusers=a.idusers where u.idrole=1 order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το κορς που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();// να φτιαξω getCourseById method!!!!!!!!////////////////////////////////
                Course course = viewCourseById(idcourse); // καλω την viewCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    User student = new User();
                    int idstudent = rs.getInt("idusers");
                    student = getUserById(idstudent);
                    students.add(student);
                }

                System.out.println("The list of students for " + course.toString() + " is the below:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println(i + 1 + ". " + students.get(i).toString());///////να το φτιαξω//////////
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

        return students;
    }

    @Override
    public boolean removeStudentFromCourse(int idstudent, int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Integer> viewTrainersPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Integer> getTrainersPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeTrainerFromCourse(int idtrainer, int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Integer> viewAssignmentsPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Integer> getAssignmentsPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAssignmentFromCourse(int idassignment, int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Integer> viewStudentsPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
