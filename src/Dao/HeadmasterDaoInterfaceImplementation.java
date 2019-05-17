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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Course;
import model.User;
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
//            preparedStatement.setDate(2, course.getStart_date());
//            preparedStatement.setDate(3, course.getEnd_date());

            preparedStatement.executeUpdate();

            //conn.close();
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
            System.out.println("The list of songs is the below:");
            int i = 1;
            for (int id : AllCourses.keySet()) {
                System.out.println(i + ". " + AllCourses.get(id));
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HeadmasterDaoInterfaceImplementation.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

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

            //conn.close();
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
        }

        return AllStudents;
    }

    @Override
    public void updateStudent(User student) {
        int thesi = student.getIduser();
        User user = getUserById(thesi);
        int role = user.getIdrole();
        if (role == 1) {
            Connection conn = dbutils.createConnection();
            String sql = "update users set first_name=? , last_name=? , username=? , passw0rd=? , idrole=? where idusers=? ;";

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, student.getFirstname());
                preparedStatement.setString(2, student.getLastname());
                preparedStatement.setString(3, student.getUsername());
                preparedStatement.setString(4, student.getPassword());
                preparedStatement.setInt(5, 1);
                preparedStatement.setInt(6, student.getIduser());
                preparedStatement.executeUpdate();

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
    }

    @Override
    public boolean deleteStudent(int idusers) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from users where idusers=" + idusers;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

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
    public User viewStudentById(int idstudent) {
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
        }

        return AllStudents;
    }

    /////////////////////METHODS FOR USERS://///////////////////////////
    @Override
    public User getUserById(int iduser) {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users where idusers=?";
        User student = new User();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            pst.setInt(1, iduser);
            rs = pst.executeQuery();
            while (rs.next()) {
                student.setIduser(iduser);
                String firstname = rs.getString("first_name");
                student.setFirstname(firstname);
                String lastname = rs.getString("last_name");
                student.setLastname(lastname);
                String username = rs.getString("username");
                student.setUsername(username);
                int userrole = rs.getInt("idrole");
                student.setIdrole(userrole);

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

        return student;
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

            //conn.close();
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
        }

        return AllTrainers;
    }

    @Override
    public User viewTrainerById(int idtrainer) {
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
        int thesi = trainer.getIduser();
        User user = getUserById(thesi);
        int role = user.getIdrole();
        if (role == 2) {
            Connection conn = dbutils.createConnection();
            String sql = "update users set first_name=? , last_name=? , username=? , passw0rd=? , idrole=? where idusers=? ;";

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, trainer.getFirstname());
                preparedStatement.setString(2, trainer.getLastname());
                preparedStatement.setString(3, trainer.getUsername());
                preparedStatement.setString(4, trainer.getPassword());
                preparedStatement.setInt(5, 2);
                preparedStatement.setInt(6, trainer.getIduser());
                preparedStatement.executeUpdate();

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
    }

    @Override
    public boolean deleteTrainer(int idtrainer) {
        Connection conn = dbutils.createConnection();
        String sql = "delete from users where idusers=" + idtrainer;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Assignment> getAssignments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course viewAssignmentById(int idassignment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAssignment(int idassignment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /////////////////////METHODS FOR JUNCTION TABLES://///////////////////////////
    @Override
    public List<User> viewStudentsPerCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeStudentFromCourse(int idcourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
