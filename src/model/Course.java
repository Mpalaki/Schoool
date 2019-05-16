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
public class Course {

    private int idcourse;
    private String course_title;

    public Course() {
    }

    public Course(String course_title) {
        this.course_title = course_title;
    }

    public Course(int idcourse, String course_title) {
        this.idcourse = idcourse;
        this.course_title = course_title;
    }

    public int getIdcourse() {
        return idcourse;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setIdcourse(int idcourse) {
        this.idcourse = idcourse;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    @Override
    public String toString() {
        return "Course{" + "idcourse=" + idcourse + ", course_title=" + course_title + '}';
    }

}
