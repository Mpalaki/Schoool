/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import Dao.HeadmasterDaoInterfaceImplementation;
import Dao.StudentDaoImplementation;
import Dao.TrainerDaoImplementation;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import model.Assignment;
import model.Course;
import model.User;

/**
 *
 * @author Makis
 */
public class LoginPage {

    User u = new User();

    public void welcomePage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please enter your username.");
        String usernamename = in.next();
        if (isUsernameValid(usernamename)) {
            User user = u.getUserByUsername(usernamename);
            String password = user.getPassword();
            System.out.println("Please enter your password.");
            String enteredpassword = in.next();
            String pw = u.encrypt(enteredpassword);
            if (pw.equals(password)) {
                System.out.println("Welcome " + user.getFirstname() + "!");
                displayMenuOptionsAccordingToRole(user.getIdrole(), user.getIduser());
            } else {
                System.err.println("Invalid password.");
                welcomePage();
            }
        }
    }

    public boolean isUsernameValid(String username) {
        Map<String, User> allUsers = u.getUsers();
        if (allUsers.containsKey(username)) {
            return true;
        } else {
            System.err.println("Invalid username");
            welcomePage();
            return false;
        }
    }

    public void displayInitialStudentMenuOptions(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"View daily schedule per courses.(Type 1)",
            "See the dates of submission of the Assignments per Course.(Type 2)",
            "Submit any Assignments.(Type 3)",
            "For exit type '0' and press Enter."};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethod(selection, iduser);
        } catch (Exception e) {
            displayInitialStudentMenuOptions(iduser);
        }
    }

    public void displayInitialTrainerMenuOptions(int iduser) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "For exit type '0' and press Enter."};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethodTrainer(selection, iduser);
        } catch (Exception e) {
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    public void displayInitialHeadmasterMenuOptions() {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "Creat.(Type 1)",
            "View.(Type 2)",
            "Update.(Type 3)",
            "Delete.(Type 4)",
            "For exit type '0' and press Enter."};
        System.out.println("Please select the type of action from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantMethodHeadm(selection);
        } catch (Exception e) {
            displayInitialHeadmasterMenuOptions();
        }
    }

    private void displayMenuOptionsAccordingToRole(int idrole, int iduser) {
        if (idrole == 1) {
            displayInitialStudentMenuOptions(iduser);
        } else if (idrole == 2) {
            displayInitialTrainerMenuOptions(iduser);
        } else {
            displayInitialHeadmasterMenuOptions();
        }
    }

    StudentDaoImplementation st = new StudentDaoImplementation();

    private void callRelevantMethod(int selection, int iduser) {
        if (selection == 1) {
            askStudentForDate(iduser);
        } else if (selection == 2) {
            st.viewSubmissionDatesOfAssignmentsPerCourse(iduser);
            displayInitialStudentMenuOptions(iduser);
        } else if (selection == 3) {
            askStudentforAssignmentId(iduser);
        } else if (selection == 0) {
            System.out.println("Goodbye.");
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
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(utilDate);
                c.add(Calendar.DATE, 1);
                utilDate = c.getTime();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                Date sqldate = java.sql.Date.valueOf(dateInput(date, iduser));
                st.viewDailySchedule(iduser, sqlDate);
                x = 2;
            } catch (Exception e) {
                System.err.println("you input an invalid date. Please input valid date(yyyy-MM-dd)");
            }
        }
        displayInitialStudentMenuOptions(iduser);
    }

    private void askStudentforAssignmentId(int iduser) {
        List l = st.viewAssignmentsPerCoursePerStudent(iduser);
        if (!l.isEmpty()) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter ID for the assignment you wish to submit. ");
            inNextNotInt(in);
            int idassignment = in.nextInt();
            System.out.println("Please enter ID for the course of the assignment.");
            inNextNotInt(in);
            int idcourse = in.nextInt();
            st.submitAssignment(idassignment, iduser, idcourse);
            displayInitialStudentMenuOptions(iduser);
        } else {
            displayInitialStudentMenuOptions(iduser);
        }
    }

    public LocalDate dateInput(String userInput, int iduser) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        return date;

    }

    private void inNextNotInt(Scanner in) {
        while (!in.hasNextInt()) {
            System.err.println("Enter numerical.");
            in.next();
        }
    }
    TrainerDaoImplementation t = new TrainerDaoImplementation();

    HeadmasterDaoInterfaceImplementation hm1 = new HeadmasterDaoInterfaceImplementation();

    private void callRelevantMethodTrainer(int selection, int iduser) {
        if (selection == 1) {
            t.viewCoursesPerTrainer(iduser);
            displayInitialTrainerMenuOptions(iduser);
        } else if (selection == 2) {
            askTrainerForCourseID(iduser);
        } else if (selection == 3) {
            askTrainerforCourseIdView(iduser);
        } else if (selection == 4) {
            askTrainerforCourseIdMark(iduser);
        } else if (selection == 0) {
            System.out.println("Goodbye.");
        } else {
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    private void askTrainerForCourseID(int iduser) {
        List l = t.viewCoursesPerTrainer(iduser);
        if (!l.isEmpty()) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter ID for the course of which the students you want to see. ");
            inNextNotInt(in);
            int idcourse = in.nextInt();
            t.viewStudentsPerCourse(iduser, idcourse);
            displayInitialTrainerMenuOptions(iduser);
        } else {
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    private void askTrainerforCourseIdView(int iduser) {
        List l = t.viewCoursesPerTrainer(iduser);
        if (!l.isEmpty()) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter ID for the course of which the  assignments per Student  you want to see. ");
            inNextNotInt(in);
            int idcourse = in.nextInt();
            t.viewAssignmentsPerStudentPerCourse(iduser, idcourse);
            displayInitialTrainerMenuOptions(iduser);
        } else {
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    private void askTrainerforCourseIdMark(int iduser) {
        Scanner in = new Scanner(System.in);
        List l = t.viewCoursesPerTrainer(iduser);
        if (!l.isEmpty()) {
            System.out.println("Please enter ID for course of the assignment you wish to submit. ");
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
            displayInitialTrainerMenuOptions(iduser);
        } else {
            displayInitialTrainerMenuOptions(iduser);
        }
    }

    private void callRelevantMethodHeadm(int selection) {
        if (selection == 1) {
            callCreateMethods();
        } else if (selection == 2) {
            callViewMethods();
        } else if (selection == 3) {
            callUpdateMethods();
        } else if (selection == 4) {
            callRemoveMethods();
        } else if (selection == 0) {
            System.out.println("Goodbye.");;
        } else {
            displayInitialHeadmasterMenuOptions();
        }
    }

    private void callCreateMethods() {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "Insert a student.(Type 1)",
            "Insert a trainer.(Type 2)",
            "Insert a course.(Type 3)",
            "Insert an assignment.(Type 4)",
            "Appoint student to course.(Type 5)",
            "Appoint trainer to course.(Type 6)",
            "Appoint assignment to course.(Type 7)",
            "Schedule a day for course.(Type 8)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantCreateMethod(selection);
        } catch (Exception e) {
            callCreateMethods();
        }
    }

    private void callViewMethods() {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "View all students.(Type 1)",
            "View all trainers.(Type 2)",
            "View all courses.(Type 3)",
            "View all assignments.(Type 4)",
            "View all Students per Courses.(Type 5)",
            "View all Trainers per Courses.(Type 6)",
            "View all Assignments per Courses.(Type 7)",
            "View all Schedules per Courses.(Type 8)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantViewMethod(selection);
        } catch (Exception e) {
            callViewMethods();
        }
    }

    private void callUpdateMethods() {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "Update student.(Type 1)",
            "Update trainer.(Type 2)",
            "Update course.(Type 3)",
            "Update assignment.(Type 4)",};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantUpdateMethod(selection);
        } catch (Exception e) {
            callUpdateMethods();
        }
    }

    private void callRemoveMethods() {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {
            "Delete student.(Type 1)",
            "Delete trainer.(Type 2)",
            "Delete course.(Type 3)",
            "Delete assignment.(Type 4)",
            "Delete Student from Course.(Type 5)",
            "Delete Trainer from Course.(Type 6)",
            "Delete Assignment from Course.(Type 7)",
            "Delete Schedule from Course.(Type 8)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        try {
            int selection = in.nextInt();
            callRelevantRemoveMethod(selection);
        } catch (Exception e) {
            callRemoveMethods();
        }
    }

    private void callRelevantCreateMethod(int selection) {
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
            callCreateMethods();
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
        String hashed = u.encrypt(pw);
        student.setPassword(hashed);
        student.setIdrole(1);
        hm1.insertStudent(student);
        displayInitialHeadmasterMenuOptions();
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
        String hashed = u.encrypt(pw);
        trainer.setPassword(hashed);
        trainer.setIdrole(2);
        hm1.insertTrainer(trainer);
        displayInitialHeadmasterMenuOptions();
    }

    private void insertCourse() {
        Scanner in = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Please enter course title.");
        String ct = in.next();
        course.setCourse_title(ct);
        hm1.insertCourse(course);
        displayInitialHeadmasterMenuOptions();
    }

    private void insertAs() {
        Scanner in = new Scanner(System.in);
        Assignment as = new Assignment();
        try {
            System.out.println("Please enter assignment title.");
            String at = in.next();
            as.setTitle(at);
            System.out.println("Please enter submission date and time(yyyy-mm-dd hh:mm:ss).");
            String at1 = in.next();
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2019-07-23 10:10:10.0");
            as.setSubmission_date_time(timestamp);
            hm1.insertAssignment(as);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            insertAs();

        }
    }

    private void appointStudentToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        Map<Integer, Course> m = hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            if (m.containsKey(idcourse)) {

                System.out.println("Please enter student id.");
                hm1.viewStudents();
                int idstudent = in.nextInt();
                hm1.appointStudentsToCourse(idstudent, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such course record.");
                appointStudentToCourse();
            }
        } catch (Exception e) {
            appointStudentToCourse();
        }

    }

    private void appointTrainerToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        Map<Integer, Course> m = hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            if (m.containsKey(idcourse)) {

                System.out.println("Please enter trainer id.");
                hm1.viewTrainers();
                int idstudent = in.nextInt();
                hm1.appointTrainersToCourse(idstudent, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such course record.");
                appointTrainerToCourse();
            }
        } catch (Exception e) {
            appointTrainerToCourse();
        }
    }

    private void appointAssToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        Map<Integer, Course> m = hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            if (m.containsKey(idcourse)) {

                System.out.println("Please enter assignment id.");
                hm1.viewAssignments();
                int idass = in.nextInt();
                hm1.appointAssignmentsToCourse(idass, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such course record.");
                appointAssToCourse();
            }
        } catch (Exception e) {
            appointAssToCourse();
        }
    }

    public void ScheduleDayToCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        Map<Integer, Course> m = hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            if (m.containsKey(idcourse)) {
                Course course = hm1.getCourseById(idcourse);
                System.out.println("Please enter the date(yyyy-MM-dd).");
                String date = in.next();
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(utilDate);
                c.add(Calendar.DATE, 1);
                utilDate = c.getTime();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                hm1.scheduleDayToCourse(course, sqlDate);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such course record.");
                ScheduleDayToCourse();
            }
        } catch (Exception e) {
            ScheduleDayToCourse();
        }
    }

    private void callRelevantViewMethod(int selection) {
        if (selection == 1) {
            hm1.viewStudents();
            displayInitialHeadmasterMenuOptions();
        } else if (selection == 2) {
            hm1.viewTrainers();
            displayInitialHeadmasterMenuOptions();
        } else if (selection == 3) {
            hm1.viewCourses();
            displayInitialHeadmasterMenuOptions();
        } else if (selection == 4) {
            hm1.viewAssignments();
            displayInitialHeadmasterMenuOptions();
        } else if (selection == 5) {
            viewStudentsPerCourse();
        } else if (selection == 6) {
            viewTrainersPerCourse();
        } else if (selection == 7) {
            viewAssignemntsPerCourse();
        } else if (selection == 8) {
            viewSchedulePerCourse();
        } else {
            callViewMethods();
        }
    }

    private void viewStudentsPerCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            hm1.viewStudentsPerCourse(idcourse);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            viewStudentsPerCourse();
        }

    }

    private void viewTrainersPerCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            hm1.viewTrainersPerCourse(idcourse);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            viewTrainersPerCourse();
        }
    }

    private void viewAssignemntsPerCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            hm1.viewAssignmentsPerCourse(idcourse);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            viewAssignemntsPerCourse();
        }
    }

    private void viewSchedulePerCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            Course c = hm1.getCourseById(idcourse);
            hm1.viewSchedulePerCourse(c);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            viewSchedulePerCourse();
        }
    }

    private void callRelevantUpdateMethod(int selection) {
        if (selection == 1) {
            updateStudent();
        } else if (selection == 2) {
            updateTrainer();
        } else if (selection == 3) {
            updateCourse();
        } else if (selection == 4) {
            updateAssignment();
        } else {
            callUpdateMethods();
        }
    }

    private void updateStudent() {
        Scanner in = new Scanner(System.in);
        User student = new User();
        System.out.println("Please enter student id.");
        Map<Integer, User> m = hm1.viewStudents();

        try {
            int idstudent = in.nextInt();
            if (m.containsKey(idstudent)) {
                student.setIduser(idstudent);
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
                String hashed = u.encrypt(pw);
                student.setPassword(hashed);
                student.setIdrole(1);
                hm1.updateStudent(student);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such student record.");
                updateStudent();
            }
        } catch (Exception e) {
            updateStudent();
        }

    }

    private void updateTrainer() {
        Scanner in = new Scanner(System.in);
        User student = new User();
        System.out.println("Please enter trainer id.");
        Map<Integer, User> m = hm1.viewTrainers();
        try {
            int idstudent = in.nextInt();
            if (m.containsKey(idstudent)) {
                student.setIduser(idstudent);
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
                String hashed = u.encrypt(pw);
                student.setPassword(hashed);
                student.setIdrole(2);
                hm1.updateTrainer(student);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such trainer record.");
                updateTrainer();
            }
        } catch (Exception e) {
            updateTrainer();
        }
    }

    private void updateCourse() {
        Scanner in = new Scanner(System.in);
        Course course = new Course();
        System.out.println("Please enter course id.");
        Map<Integer, Course> m = hm1.viewCourses();
        try {
            int idc = in.nextInt();
            if (m.containsKey(idc)) {
                course.setIdcourse(idc);
                System.out.println("Please enter course title.");
                String ct = in.next();
                course.setCourse_title(ct);
                hm1.updateCourse(course);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such course record.");
                updateCourse();
            }
        } catch (Exception e) {
            updateCourse();
        }
    }

    private void updateAssignment() {
        Scanner in = new Scanner(System.in);
        Assignment as = new Assignment();
        System.out.println("Please enter assignment id.");
        Map<Integer, Assignment> m = hm1.viewAssignments();
        try {
            int ida = in.nextInt();
            if (m.containsKey(ida)) {
                as.setIdassignment(ida);
                System.out.println("Please enter assignment title.");
                String at = in.next();
                as.setTitle(at);
                System.out.println("Please enter submission date and time(yyyy-mm-dd hh:mm:ss).");
                String at1 = in.next();
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2019-07-23 10:10:10.0");
                as.setSubmission_date_time(timestamp);
                hm1.updateAssignment(as);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No such assignment record.");
                updateAssignment();
            }
        } catch (Exception e) {
            updateAssignment();

        }
    }

    private void callRelevantRemoveMethod(int selection) {
        if (selection == 1) {
            deleteStudent();
        } else if (selection == 2) {
            deleteTrainer();
        } else if (selection == 3) {
            deleteCourse();
        } else if (selection == 4) {
            deleteAs();
        } else if (selection == 5) {
            deleteStudentCourse();
        } else if (selection == 6) {
            deleteTrainerCourse();
        } else if (selection == 7) {
            deleteAsCourse();
        } else if (selection == 8) {
            deleteScheduleCourse();
        } else {
            callCreateMethods();
        }
    }

    private void deleteStudent() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter student id.");
        hm1.viewStudents();
        try {
            int idstudent = in.nextInt();
            hm1.deleteStudent(idstudent);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            deleteStudent();
        }
    }

    private void deleteTrainer() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter trainer id.");
        hm1.viewTrainers();
        try {
            int idstudent = in.nextInt();
            hm1.deleteTrainer(idstudent);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            deleteTrainer();
        }
    }

    private void deleteCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idstudent = in.nextInt();
            hm1.deleteCourse(idstudent);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            deleteCourse();
        }
    }

    private void deleteAs() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter assignment id.");
        hm1.viewAssignments();
        try {
            int idstudent = in.nextInt();
            hm1.deleteAssignment(idstudent);
            displayInitialHeadmasterMenuOptions();
        } catch (Exception e) {
            deleteAs();
        }
    }

    private void deleteStudentCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter student id.");
            List l = hm1.viewStudentsPerCourse(idcourse);
            if (!l.isEmpty()) {
                int idstudent = in.nextInt();
                hm1.removeStudentFromCourse(idstudent, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.out.println("No students enrolled in this course.");
                displayInitialHeadmasterMenuOptions();
            }
        } catch (Exception e) {
            deleteStudentCourse();
        }
    }

    private void deleteTrainerCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter trainer id.");
            List l = hm1.viewTrainersPerCourse(idcourse);
            if (!l.isEmpty()) {
                int idstudent = in.nextInt();
                hm1.removeTrainerFromCourse(idstudent, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No trainer enrolled in this course.");
                displayInitialHeadmasterMenuOptions();
            }
        } catch (Exception e) {
            deleteTrainerCourse();
        }
    }

    private void deleteAsCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            System.out.println("Please enter assignment id.");
            List l = hm1.viewAssignmentsPerCourse(idcourse);
            if (!l.isEmpty()) {
                int idstudent = in.nextInt();
                hm1.removeAssignmentFromCourse(idstudent, idcourse);
                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No assignment enrolled in this course.");
                displayInitialHeadmasterMenuOptions();
            }
        } catch (Exception e) {
            deleteAsCourse();
        }
    }

    private void deleteScheduleCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter course id.");
        hm1.viewCourses();
        try {
            int idcourse = in.nextInt();
            Course course = hm1.getCourseById(idcourse);
            List l = hm1.viewSchedulePerCourse(course);
            if (!l.isEmpty()) {
                System.out.println("Please enter the date(yyyy-MM-dd).");

                String date = in.next();
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(utilDate);
                c.add(Calendar.DATE, 1);
                utilDate = c.getTime();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                Date sqldate = java.sql.Date.valueOf(dateInput(date, iduser));
                hm1.removeScheduleFromCourse(course, sqlDate);

                displayInitialHeadmasterMenuOptions();
            } else {
                System.err.println("No scheduled days for this course.");
                displayInitialHeadmasterMenuOptions();
            }
        } catch (Exception e) {
            deleteScheduleCourse();
        }
    }

}
