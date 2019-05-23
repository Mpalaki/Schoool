/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import Dao.HeadmasterDaoInterfaceImplementation;
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
                /////////////// CHECK ROLE OF USER ///////////////////////            
            } else {
                System.out.println("Invalid password.");
            }
        }
    }

    public void displayInitialStudentMenuOptions() {
        String[] menuoptions = {"View daily schedule per courses.(Type 1)",
            "See the dates of submission of the Assignments per Course.(Type 2)",
            "Submit any Assignments.(Type 3)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i+1+". "+menuoptions[i]);
        }
    }
    
    public void displayInitialTrainerMenuOptions() {
        String[] menuoptions = {"View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)"};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i+1+". "+menuoptions[i]);
        }
    }
    
    public void displayHeadmasterMenuOptions() {
        String[] menuoptions = {
            "View all the Courses.(Type 1)",
            "Create a course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",
            "View all the Courses you are enrolled in.(Type 1)",
            "View all the Students per Course.(Type 2)",
            "View all the Assignments per Student per Course.(Type 3)",
            "Mark all the Assignments per Student per Course. (Type 4)",};
        System.out.println("Please select an option from the below listed:");
        for (int i = 0; i < menuoptions.length; i++) {
            System.out.println(i+1+". "+menuoptions[i]);
        }
    }
}
