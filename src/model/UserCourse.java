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
public class UserCourse {
    private int idusers;
    private int idcourse;

    public UserCourse() {
    }

    public UserCourse(int idusers, int idcourse) {
        this.idusers = idusers;
        this.idcourse = idcourse;
    }

    public int getIdusers() {
        return idusers;
    }

    public int getIdcourse() {
        return idcourse;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    public void setIdcourse(int idcourse) {
        this.idcourse = idcourse;
    }

    @Override
    public String toString() {
        return "UserCourse{" + "idusers=" + idusers + ", idcourse=" + idcourse + '}';
    }

    

       
}
