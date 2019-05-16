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
    private User user;
    private Course course;

    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public UserCourse() {
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "UserCourse{" + "user=" + user + ", course=" + course + '}';
    }
    
    
    
}
