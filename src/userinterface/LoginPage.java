/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import Dao.HeadmasterDaoInterfaceImplementation;
import Dao.StudentDaoImplementation;
import java.util.List;
import java.util.Scanner;
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
                displayMenuOptionsAccordingToRole(user.getIdrole(), user);
            } else {
                System.out.println("Invalid password.");
            }
        }
    }

    public void displayInitialStudentMenuOptions(User user) {
        Scanner in = new Scanner(System.in);
        String[] menuoptions = {"View daily schedule per courses.(Type 1)",
            "See the dates of submission of the Assignments per Course.(Type 2)",
            "Submit any Assignments.(Type 3)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
        int selection = in.nextInt();
        callRelevantMethod(selection, user);
    }

    public void displayInitialTrainerMenuOptions(User user) {
        String[] menuoptions = {"View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
    }

    public void displayInitialHeadmasterMenuOptions(User user) {
        String[] menuoptions = {
            "Creat.(Type 1)",
            "View.(Type 2)",
            "Update.(Type 3)",
            "Delete.(Type 4)"};
        System.out.println("Please select the type of action from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i + 1 + ". " + menuoptions[i]);
        }
    }

    public void selectSecondaryHeadmasterMenuOptions(int select) {
//        while(select!=1 || select!=2 || select!=3 || select!=4 ){
        if (select == 1) {
            displayCreateHeadmasterMenuOptions();
        } else if (select == 1) {
            displayViewHeadmasterMenuOptions();
        } else if (select == 1) {
            displayUpdateHeadmasterMenuOptions();
        } else if (select == 1) {
            displayDeleteHeadmasterMenuOptions();
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

    private void displayMenuOptionsAccordingToRole(int idrole, User user) {
        if (idrole == 1) {
            displayInitialStudentMenuOptions(user);
        } else if (idrole == 2) {
            displayInitialTrainerMenuOptions(user);
        } else {
            displayInitialHeadmasterMenuOptions(user);
        }
    }

    StudentDaoImplementation st = new StudentDaoImplementation();

    private void callRelevantMethod(int selection, User user) {
        if (selection == 1) {
            askStudentForCourseAndDate(user);
        } else if (selection == 2) {
            st.viewSubmissionDatesOfAssignmentsPerCourse(user.getIduser());
        } else if (selection == 3) {
            askStudentforAssignmentId(user);
        } else {
            displayInitialStudentMenuOptions(user);
        }
    }

    private void askStudentForCourseAndDate(User user) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the id of the course. ");
    }

//    private void askStudentForCourseId(User user) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter the id of the assignment. Please select one of the below:");
//        st.viewAssignmentsPerCoursePerStudent(user.getIduser());
//        int selection = in.nextInt();
//        st.viewSubmissionDatesOfAssignmentsPerCourse(user.getIduser());
//    }

    private void askStudentforAssignmentId(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
