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
import model.Schedule;
import model.User;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class HeadmasterDaoInterfaceImplementation implements HeadmasterDaoInterface {

    public HeadmasterDaoInterfaceImplementation() {
    }

//    TrainerDaoImplementation td = new TrainerDaoImplementation();
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
    public Course getCourseById(int idcouse) {
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
        String sql = "delete from course where idcourse=?";
        Map<Integer, Course> AllCourses = getCourses();
        if (AllCourses.containsKey(idcourse)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, idcourse);
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
        String sql = "delete from users where idusers=?";
        Map<Integer, User> allStudents = getStudents();
        if (allStudents.containsKey(idusers)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, idusers);
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
        String sql = "delete from users where idusers=?";
        Map<Integer, User> allTrainers = getTrainers();
        if (allTrainers.containsKey(idtrainer)) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, idtrainer);
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
    public Assignment getAssignmentById(int idassignment) {
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
        String sql = "delete from assignment where idassignment=?";
        Map<Integer, Assignment> allAssignments = getAssignments();
        if (allAssignments.containsKey(idassignment)) { // Checking if the requested record exists in the database.

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, idassignment);
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
        String sql = " INSERT INTO studentcourse (idusers,idcourse)"
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
//                    td.updateAssignmentCourseStudentTable(idcourse);

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
        String sql = " INSERT INTO trainercourse (idusers,idcourse)"
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
                + "inner join studentcourse a\n"
                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
                + "inner join users u\n"
                + "on u.idusers=a.idusers where u.idrole=1 order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    User student = new User();
                    int idstudent = rs.getInt("idusers");
                    student = getUserById(idstudent);
                    students.add(student);
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
        Connection conn = dbutils.createConnection();
        Map<Integer, User> allStudents = getStudents();// returns map with all students
        if (allStudents.containsKey(idstudent)) {// if the requested student record exists
            User student = getUserById(idstudent);// returns object student for the requested student id
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                List<User> studentsPerCourse = getStudentsPerCourse(idcourse);// returns list with all the students in the requested course
                if (studentsPerCourse.contains(student)) {// if the student is indeed appointed to the requested course
                    String sql = "delete from studentcourse\n"
                            + "where idusers=? and idcourse=?";
                    try {
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setInt(1, idstudent);
                        preparedStatement.setInt(2, idcourse);
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

                } else {
                    System.out.println("The student is not appointed to the requested course.");
                }

                return true;
            } else {
                System.out.println("No such course record.");
                return false;
            }

        } else {
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                System.out.println("No such student record.");
            } else {
                System.out.println("No such student record neither such course record");
            }
            return false;
        }

    }

    @Override
    public List<User> viewTrainersPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<User> trainers = new ArrayList();// αρχικοποιω την λιστα με τους trainers
        String sql = "select c.idcourse,c.course_title,u.idusers,u.first_name,u.last_name\n"
                + "from course c  \n"
                + "inner join trainercourse a\n"
                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
                + "inner join users u\n"
                + "on u.idusers=a.idusers where u.idrole=2 order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    User trainer = new User();
                    int idtrainer = rs.getInt("idusers");
                    trainer = getUserById(idtrainer);
                    trainers.add(trainer);
                }

                System.out.println("The list of trainers for course: '" + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < trainers.size(); i++) {
                    System.out.println(i + 1 + ". " + "ID: " + trainers.get(i).getIduser() + "; " + trainers.get(i).getFirstname() + " " + trainers.get(i).getLastname());
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

        return trainers;
    }

    @Override
    public List<User> getTrainersPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<User> trainers = new ArrayList();// αρχικοποιω την λιστα με τους trainers
        String sql = "select c.idcourse,c.course_title,u.idusers,u.first_name,u.last_name\n"
                + "from course c  \n"
                + "inner join trainercourse a\n"
                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
                + "inner join users u\n"
                + "on u.idusers=a.idusers where u.idrole=2 order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    User trainer = new User();
                    int idtrainer = rs.getInt("idusers");
                    trainer = getUserById(idtrainer);
                    trainers.add(trainer);
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

        return trainers;
    }

    @Override
    public boolean removeTrainerFromCourse(int idtrainer, int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, User> allTrainers = getTrainers();// returns map with all trainers
        if (allTrainers.containsKey(idtrainer)) {// if the requested trainer record exists
            User trainer = getUserById(idtrainer);// returns object trainer for the requested trainer id
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                List<User> trainersPerCourse = getTrainersPerCourse(idcourse);// returns list with all the trainers in the requested course
                if (trainersPerCourse.contains(trainer)) {// if the trainer is indeed appointed to the requested course
                    String sql = "delete from trainercourse\n"
                            + "where idusers=? and idcourse=?";
                    try {
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setInt(1, idtrainer);
                        preparedStatement.setInt(2, idcourse);
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

                } else {
                    System.out.println("The trainer is not appointed to the requested course.");
                }

                return true;
            } else {
                System.out.println("No such course record.");
                return false;
            }

        } else {
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                System.out.println("No such trainer record.");
            } else {
                System.out.println("No such trainer record neither such course record");
            }
            return false;
        }
    }

    @Override
    public List<Assignment> viewAssignmentsPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<Assignment> assignments = new ArrayList();// αρχικοποιω την λιστα με τa assignments
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title\n"
                + "from course c\n"
                + "inner join assignmentcourse b\n"
                + "on c.idcourse=b.idcourse and c.idcourse=?\n"
                + "inner join assignment a\n"
                + "on b.idassignment=a.idassignment order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    Assignment as = new Assignment();
                    int idassignment = rs.getInt("idassignment");
                    as = getAssignmentById(idassignment);
                    assignments.add(as);
                }

                System.out.println("The list of assignments for course: '" + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < assignments.size(); i++) {
                    System.out.println(i + 1 + ". ID: " + assignments.get(i).getIdassignment() + ", title: "+ assignments.get(i).getTitle() + ", submission date and time: " + assignments.get(i).getSubmission_date_time());
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

        return assignments;
    }

    @Override
    public List<Assignment> getAssignmentsPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<Assignment> assignments = new ArrayList();// αρχικοποιω την λιστα με τa assignments
        String sql = "select c.idcourse,c.course_title,a.idassignment,a.assignment_title\n"
                + "from course c\n"
                + "inner join assignmentcourse b\n"
                + "on c.idcourse=b.idcourse and c.idcourse=?\n"
                + "inner join assignment a\n"
                + "on b.idassignment=a.idassignment order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    Assignment as = new Assignment();
                    int idassignment = rs.getInt("idassignment");
                    as = getAssignmentById(idassignment);
                    assignments.add(as);
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

        return assignments;
    }

    @Override
    public boolean removeAssignmentFromCourse(int idassignment, int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Assignment> allAssignments = getAssignments();// returns map with all Assignments
        if (allAssignments.containsKey(idassignment)) {// if the requested Assignment record exists
            Assignment assignment = getAssignmentById(idassignment);// returns object Assignment for the requested Assignment id
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                List<Assignment> assignmentsPerCourse = getAssignmentsPerCourse(idcourse);// returns list with all the Assignments in the requested course
                if (assignmentsPerCourse.contains(assignment)) {// if the Assignment is indeed appointed to the requested course
                    String sql = "delete from assignmentcourse\n"
                            + "where idassignment=? and idcourse=?";
                    try {
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setInt(1, idassignment);
                        preparedStatement.setInt(2, idcourse);
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

                } else {
                    System.out.println("The assignment is not appointed to the requested course.");
                }

                return true;
            } else {
                System.out.println("No such course record.");
                return false;
            }

        } else {
            Map<Integer, Course> allCourses = getCourses();// returns map with all courses
            if (allCourses.containsKey(idcourse)) {// if the requested course record exists
                System.out.println("No such assignment record.");
            } else {
                System.out.println("No such assignment record neither such course record");
            }
            return false;
        }
    }

    @Override
    public List<User> viewStudentsPerCourse(int idcourse) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<User> students = new ArrayList();// αρχικοποιω την λιστα με τους μαθητες
        String sql = "select c.idcourse,c.course_title,u.idusers,u.first_name,u.last_name\n"
                + "from course c  \n"
                + "inner join studentcourse a\n"
                + "on c.idcourse=a.idcourse and c.idcourse=?\n"
                + "inner join users u\n"
                + "on u.idusers=a.idusers where u.idrole=1 order by c.idcourse";
        if (allCourses.containsKey(idcourse)) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, idcourse);
                rs = pst.executeQuery();
                Course course = getCourseById(idcourse); // καλω την getCourseById που επιστρεφει το course object για το id pou zitise
                while (rs.next()) {
                    User student = new User();
                    int idstudent = rs.getInt("idusers");
                    student = getUserById(idstudent);
                    students.add(student);
                }

                System.out.println("The list of students for course: '" + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println(i + 1 + ". " + "ID: " + students.get(i).getIduser() + "; " + students.get(i).getFirstname() + " " + students.get(i).getLastname());///////να το φτιαξω//////////
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
    public boolean scheduleDayToCourse(Course course, Date sqlDate) {
//        try {
//            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date);
//            Calendar c = Calendar.getInstance();
//            c.setTime(utilDate);
//            c.add(Calendar.DATE, 1);
//            utilDate = c.getTime();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Schedule schedule = new Schedule(course.getIdcourse(), sqlDate);
        Connection conn = dbutils.createConnection();
        String sql = " INSERT INTO schedules (idcourse,working_day)"
                + "VALUES (?,?);";
        List<Schedule> schedules = getSchedulePerCourse(course);
        if (!schedules.contains(schedule)) {
            try {

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, course.getIdcourse());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.executeUpdate();
                System.out.println("Successful appointment.");

            } catch (SQLException ex) {

                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println("The requested course is already scheduled for the requested date.");
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
            System.out.println("The requested course is already scheduled for the requested date.");
            return false;

        }

//        catch (ParseException ex) {
//            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public List<Schedule> viewSchedulePerCourse(Course course) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<Schedule> schedules = new ArrayList();// αρχικοποιω την λιστα με τa schedules
        String sql = "SELECT * FROM schoool.schedules where idcourse=?";
        if (allCourses.containsKey(course.getIdcourse())) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, course.getIdcourse());
                rs = pst.executeQuery();
                while (rs.next()) {
                    Schedule sc = new Schedule();
                    Date working_day = rs.getDate("working_day");
                    sc.setWorking_Day(working_day);

                    schedules.add(sc);
                }

                System.out.println("The list of scheduled days for course: '" + course.getCourse_title() + "' is the below:");
                for (int i = 0; i < schedules.size(); i++) {
                    System.out.println(i + 1 + ". " + schedules.get(i).getWorking_Day());
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

        return schedules;
    }

    @Override
    public List<Schedule> getSchedulePerCourse(Course course) {
        Connection conn = dbutils.createConnection();
        Map<Integer, Course> allCourses = getCourses();// καλω την getcourses για να ελεγξω αν υπαρχει το course που ζηταει
        List<Schedule> schedules = new ArrayList();// αρχικοποιω την λιστα με τa schedules
        String sql = "SELECT * FROM schoool.schedules where idcourse=?";
        if (allCourses.containsKey(course.getIdcourse())) { // αν οντως υπαρχει το course που ζητησε
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = null;
                pst.setInt(1, course.getIdcourse());
                rs = pst.executeQuery();
                while (rs.next()) {
                    Schedule sc = new Schedule();
                    Date working_day = rs.getDate("working_day");
                    sc.setWorking_Day(working_day);
                    int idcourse = rs.getInt("idcourse");
                    sc.setIdcourse(idcourse);

                    schedules.add(sc);
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
//            System.out.println("No such course record.");
        }

        return schedules;
    }

    @Override
    public boolean removeScheduleFromCourse(Course course, Date date) {
        Connection conn = dbutils.createConnection();
        String sql = " delete from schedules where idcourse=? and working_day=?";
        List<Schedule> schedules = getSchedulePerCourse(course);// returns all schedules
        

        for (int i = 0; i < schedules.size(); i++) {
//                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String s1 = formatter.format(schedules.get(i).getWorking_Day()).substring(0, 10);
            if (course.getIdcourse() == schedules.get(i).getIdcourse()) {
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, course.getIdcourse());
                    preparedStatement.setDate(2, date);
                    preparedStatement.executeUpdate();
                    System.out.println("The requested date is removed from the database.");

                } catch (SQLException ex) {

                    if (ex instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println("SQLIntegrityConstraintViolationException");
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
                if (i == (schedules.size() - 1)) {
                    System.out.println("The requested course is not scheduled for this date ");
                    return false;
                }
            }
        }
        return true;
    }

}
