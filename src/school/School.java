/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import java.text.ParseException;
import userinterface.LoginPage;

/**
 *
 * @author Makis
 */
public class School {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // ACCOUNTS FOR TESTING:
        // HEADMASTER: USERNAME="gp" , PASSWORD="gp"
        // STUDENT: USERNAME="mak" , PASSWORD="mak"
        // TRAINER: USERNAME="sp" , PASSWORD="sp"

        LoginPage lp = new LoginPage();
        lp.welcomePage();

    }
}
