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
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import userinterface.LoginPage;
import utils.dbutils;

/**
 *
 * @author Makis
 */
public class User {

    private int iduser;
    private String firstname;
    private String lastname;
    private String password;
    private String username;
    private int idrole;

    public User(String firstname, String lastname, String password, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }

    public User(int iduser, String firstname, String lastname, String password, String username) {
        this.iduser = iduser;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }

    public User(int iduser, String firstname, String lastname, String password) {
        this.iduser = iduser;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getUsername() {
        return username;
    }

    public int getIdrole() {
        return idrole;
    }

    public int getIduser() {
        return iduser;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "iduser=" + iduser + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.iduser;
        hash = 71 * hash + Objects.hashCode(this.firstname);
        hash = 71 * hash + Objects.hashCode(this.lastname);
        hash = 71 * hash + Objects.hashCode(this.username);
        hash = 71 * hash + this.idrole;
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
        final User other = (User) obj;
        if (this.iduser != other.iduser) {
            return false;
        }
        if (this.idrole != other.idrole) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    HeadmasterDaoInterfaceImplementation hm = new HeadmasterDaoInterfaceImplementation();

    public User getUserByUsername(String username) {
        User user = new User();
        Map<String, User> allUsers = getUsers();
        int i = 0;
        for (String un : allUsers.keySet()) {
            if (un.equals(username)) {
                user = allUsers.get(un);
            }
        }
        return user;
    }

    public boolean isUsernameValid(String username) {
        Map<String, User> allUsers = getUsers();
        if (allUsers.containsKey(username)) {
            return true;
        } else {
            System.out.println("Invalid username");
            return false;
        }
    }
    public Map<String, User> getUsers() {
        Connection conn = dbutils.createConnection();
        String sql = "select * from users";
        Map<String, User> AllUsers = new HashMap<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                int idusers = rs.getInt("idusers");
                user.setIduser(idusers);
                String first_name = rs.getString("first_name");
                user.setFirstname(first_name);
                String last_name = rs.getString("last_name");
                user.setLastname(last_name);
                String username = rs.getString("username");
                user.setUsername(username);
                String password = rs.getString("passw0rd");
                user.setPassword(password);
                int idrole = rs.getInt("idrole");
                user.setIdrole(idrole);

                AllUsers.put(user.getUsername(), user);
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

        return AllUsers;
    }

    public int checkUserRole(User user) {
        if (user.getIdrole() == 1) {
            return 1;
        } else if (user.getIdrole() == 2) {
            return 2;
        } else {
            return 3;
        }
    }

//    public String encode(String plainText) {
//        String encodedString = Base64.getEncoder().encodeToString(plainText.getBytes());
//        return encodedString;
//
//    }
//
//    public String decode(String encodedString) {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        String decodedString = new String(decodedBytes);
//        return decodedString;
//    }
    String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "~", "`", "!", "@", "#", "$", "%", "^", "&", "*"};
    String[] b = {"d", "o", "v", "b", "z", "g", "f", "s", "a", "e", "D", "p", "c", "H", "I", "h", "Y", "Z", "1", "w", "3", "A", "C", "6", "~", "T", "i", "j", "k", "l", "#", "$", "%", "^", "&", "*", "V", "W", "X", "2", "4", "U", "5", "7", "8", "9", "`", "m", "n", "q", "r", "t", "u", "x", "y", "B", "E", "F", "G", "P", "J", "N", "O", "Q", "S", "!", "K", "R", "@", "L", "M"};

    public String encrypt(String s) {
        String[] sarr = explode(s);
        String[] arr = new String[sarr.length];
        for (int i = 0; i < sarr.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (sarr[i].equals(a[j])) {
                    arr[i] = b[j];
                }
            }
        }
        return arrToString(arr);
    }

    public String uncrypt(String s) {
        String[] sarr = explode(s);
        String[] arr = new String[sarr.length];
        for (int i = 0; i < sarr.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (sarr[i].equals(b[j])) {
                    arr[i] = a[j];
                }
            }
        }
        return arrToString(sarr);
    }

    public String[] explode(String s) {
        String[] arr = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = String.valueOf(s.charAt(i));
        }
        return arr;
    }

    public String arrToString(String[] sa) {
        String s = "";
        for (int i = 0; i < sa.length; i++) {
            s = s + sa[i];
        }
        return s;
    }
}
