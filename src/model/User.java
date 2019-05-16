/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
        return "User{" + "iduser=" + iduser + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", username=" + username + ", idrole=" + idrole + '}';
    }

    

}
