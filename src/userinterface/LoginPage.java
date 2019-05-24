/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import Dao.HeadmasterDaoInterfaceImplementation;
import Dao.StudentDaoImplementation;
import Dao.TrainerDaoImplementation;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public class LoginPage {

//    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();
    User u = new User();

    public void welcomePage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please enter your username.");
        String usernamename = in.next();
        if (u.isUsernameValid(usernamename)) {
            User user = u.getUserByUsername(usernamename);
            String password = user.getPassword();
            System.out.println("Please enter your password.");
            String enteredpassword = in.next();
            if (password.equals(enteredpassword)) {
                System.out.println("Welcome " + user.getFirstname() + "!");
                displayMenuOptionsAccordingToRole(user.getIdrole(), user.getIduser());
            } else {
                System.out.println("Invalid password.");
            }
        }
    }

    public void displayInitialStudentMenuOptions(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"View daily schedule per courses.(Type 1)",
            "See the dates of submission of the Assignments per Course.(Type 2)",
            "Submit any Assignments.(Type 3)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethod(selection, iduser);
        } catch (Exception e) {
            displayInitialStudentMenuOptions(iduser);
            in.next();
        }
    }

    public void displayInitialTrainerMenuOptions(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethodTrainer(selection, iduser);
        } catch (Exception e) {
            displayInitialTrainerMenuOptions(iduser);
            in.next();
        }
    }

    public void displayInitialHeadmasterMenuOptions(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "Creat.(Type 1)",
            "View.(Type 2)",
            "Update.(Type 3)",
            "Delete.(Type 4)"};
        System.out.println("Please select the type of action from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethodHeadm(selection, iduser);
        } catch (Exception e) {
            displayInitialHeadmasterMenuOptions(iduser);
            in.next();
        }
    }

    private void displayCreateHeadmasterMenuOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayViewHeadmasterMenuOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayUpdateHeadmasterMenuOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayDeleteHeadmasterMenuOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayMenuOptionsAccordingToRole(int idrole, int iduser) {
        if (idrole == 1) {
            displayInitialStudentMenuOptions(iduser);
        } else if (idrole == 2) {
            displayInitialTrainerMenuOptions(iduser);
        } else {
            displayInitialHeadmasterMenuOptions(iduser);
        }
    }

    StudentDaoImplementation st = new StudentDaoImplementation();

    private void callRelevantMethod(int selection, int iduser) {
        if (selection == 1) {
            askStudentForDate(iduser);
        } else if (selection == 2) {
            st.viewSubmissionDatesOfAssignmentsPerCourse(iduser);
        } else if (selection == 3) {
            askStudentforAssignmentId(iduser);
        } else {
            displayInitialStudentMenuOptions(iduser);
        }
    }

    private void askStudentForDate(int iduser) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the date of the course(yyyy-MM-dd). ");
        int x = 1;
        while (x == 1) {
            try {
                String date = in.next();
                Date sqldate = java.sql.Date.valueOf(dateInput(date, iduser));
                st.viewDailySchedule(iduser, sqldate);
                x = 2;
            } catch (Exception e) {
                System.out.println("you input an invalid date. Please input valid date(yyyy-MM-dd)");
            }
        }
    }

//    private void askStudentForCourseId(User user) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter the id of the assignment. Please select one of the below:");
//        st.viewAssignmentsPerCoursePerStudent(user.getIduser());
//        int selection = in.nextInt();
//        st.viewSubmissionDatesOfAssignmentsPerCourse(user.getIduser());
//    }
    private void askStudentforAssignmentId(int iduser) {
        st.viewAssignmentsPerCoursePerStudent(iduser);
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter ID for the assignment you wish to submit. ");
        inNextNotInt(in);
        int idassignment = in.nextInt();
        System.out.println("Please enter ID for the course of the assignment.");
        inNextNotInt(in);
        int idcourse = in.nextInt();
        st.submitAssignment(idassignment, iduser, idcourse);
    }

    public LocalDate dateInput(String userInput, int iduser) {
//        java.sql.Date sqlDate = null;
//        try {
//            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
//            Calendar c = Calendar.getInstance();
//            c.setTime(utilDate);
//            c.add(Calendar.DATE, 1);
//            utilDate = c.getTime();
//            sqlDate = new java.sql.Date(utilDate.getTime());
//        } catch (ParseException ex) {
//            askStudentForDate(user);
//            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
//        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        return date;

    }

    private void inNextNotInt(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Enter numerical.");
            in.next();
        }
    }
    TrainerDaoImplementation t = new TrainerDaoImplementation();

    HeadmasterDaoInterfaceImplementation hm1 = new HeadmasterDaoInterfaceImplementation();

    private void callRelevantMethodTrainer(int selection, int iduser) {
        if (selection == 1) {
            t.viewCoursesPerTrainer(iduser);
        } else if (selection == 2) {
            askTrainerForCourseID(iduser);
        } else if (selection == 3) {
            askTrainerforCourseIdView(iduser);
        } else if (selection == 4) {
            askTrainerforCourseIdMark(iduser);
        } else {
            User user = hm1.getUserById(iduser);
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    private void askTrainerForCourseID(int iduser) {
        t.viewCoursesPerTrainer(iduser);
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter ID for the course of which the students you want to see. ");
        inNextNotInt(in);
        int idcourse = in.nextInt();
        t.viewStudentsPerCourse(iduser, idcourse);
    }

    private void askTrainerforCourseIdView(int iduser) {
        t.viewCoursesPerTrainer(iduser);
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter ID for the course of which the  assignments per Student  you want to see. ");
        inNextNotInt(in);
        int idcourse = in.nextInt();
        t.viewAssignmentsPerStudentPerCourse(iduser, idcourse);
    }

    private void askTrainerforCourseIdMark(int iduser) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter ID for course of the assignment you wish to submit. ");
        t.viewCoursesPerTrainer(iduser);
        inNextNotInt(in);
        int idcourse = in.nextInt();
        t.viewAssignmentsPerStudentPerCourse(iduser, idcourse);
        System.out.println("Please enter ID of the assignment.");
        inNextNotInt(in);
        int idassignment = in.nextInt();
        System.out.println("Please enter ID of the student.");
        inNextNotInt(in);
        int idstudent = in.nextInt();
        System.out.println("Please enter your mark(numerical).");
        inNextNotInt(in);
        int idmark = in.nextInt();
        t.markAssignmentPerStudentPerCourse(idassignment, idstudent, idcourse, idmark);
    }

    private void callRelevantMethodHeadm(int selection, int iduser) {
        if (selection == 1) {
            callCreateMethods(iduser);
        } else if (selection == 2) {
            callViewMethods(iduser);
        } else if (selection == 3) {
            callUpdateMethods(iduser);
        } else if (selection == 4) {
            callRemoveMethods(iduser);
        } else {
            User user = hm1.getUserById(iduser);
            displayInitialHeadmasterMenuOptions(iduser);
        }
    }

    private void callCreateMethods(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"Insert a student.(Type 1)",
            "Insert a trainer.(Type 2)",
            "Insert a course.(Type 3)",
            "Insert an assignment.(Type 4)",
            "Appoint student to course.(Type 5)",
            "Appoint trainer to course.(Type 6)",
            "Appoint assignment to course.(Type 7)",
            "Schedule a day for course.(Type 8)",};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantCreateMethod(selection, iduser);
        } catch (Exception e) {
            callCreateMethods(iduser);
            in.next();
        }
    }

    private void callViewMethods(int iduser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void callUpdateMethods(int iduser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void callRemoveMethods(int iduser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void callRelevantCreateMethod(int selection, int iduser) {
        if (selection == 1) {
            insertStudent();
        } else if (selection == 2) {
            insertTrainer();
        } else if (selection == 3) {
            insertCourse();
        } else if (selection == 4) {
            insertAs();
        } else if (selection == 5) {
            appointStudentToCourse();
        } else if (selection == 6) {
            appointTrainerToCourse();
        } else if (selection == 7) {
            appointAssToCourse();
        } else if (selection == 8) {
            ScheduleDayToCourse();
        } else {
            User user = hm1.getUserById(iduser);
            callCreateMethods(iduser);
        }
    }

    private void insertStudent() {
        Scanner in = new Scanner(System.in);
        User student = new User();
        System.out.println("Please enter first name.");
        String fn = in.next();
        student.setFirstname(fn);
        System.out.println("Please enter last name.");
        String ln = in.next();
        student.setLastname(ln);
        System.out.println("Please enter username.");
        String un = in.next();
        student.setUsername(un);
        System.out.println("Please enter password.");
        String pw = in.next();
        student.setPassword(pw);
        student.setIdrole(1);
        hm1.insertStudent(student);
    }

    private void insertTrainer() {
        Scanner in = new Scanner(System.in);
        User trainer = new User();
        System.out.println("Please enter first name.");
        String fn = in.next();
        trainer.setFirstname(fn);
        System.out.println("Please enter last name.");
        String ln = in.next();
        trainer.setLastname(ln);
        System.out.println("Please enter username.");
        String un = in.next();
        trainer.setUsername(un);
        System.out.println("Please enter password.");
        String pw = in.next();
        trainer.setPassword(pw);
        trainer.setIdrole(2);
        hm1.insertTrainer(trainer);
    }

    private void insertCourse() {
        Scanner in = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Please enter course title.");
        String ct = in.next();
        course.setCourse_title(ct);
        hm1.insertCourse(course);
    }

    private void insertAs() {
        Scanner in = new Scanner(System.in);
        Assignment as = new Assignment();
        System.out.println("Please enter assignment title.");
        String at = in.next();
        as.setTitle(at);
        System.out.println("Please enter submission date and time.");
        String at1 = in.next();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        as.setSubmission_date_time(timestamp);
        hm1.insertAssignment(as);
    }

    private void appointStudentToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter student id.");
            hm1.viewStudents();
            int idstudent = in.nextInt();
            hm1.appointStudentsToCourse(idstudent, idcourse);
        } catch (Exception e) {
            appointStudentToCourse();
            in.next();
        }

    }

    private void appointTrainerToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter trainer id.");
            hm1.viewTrainers();
            int idstudent = in.nextInt();
            hm1.appointTrainersToCourse(idstudent, idcourse);
        } catch (Exception e) {
            appointTrainerToCourse();
            in.next();
        }
    }

    private void appointAssToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter assignment id.");
            hm1.viewAssignments();
            int idass = in.nextInt();
            hm1.appointAssignmentsToCourse(idass, idcourse);
        } catch (Exception e) {
            appointAssToCourse();
            in.next();
        }
    }

    private void ScheduleDayToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            Course course = hm1.getCourseById(idcourse);
            System.out.println("Please enter the date(yyyy-MM-dd).");
            String date = in.next();
            hm1.scheduleDayToCourse(course, date);
        } catch (Exception e) {
            appointAssToCourse();
            in.next();
        }
    }

}
