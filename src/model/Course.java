/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idcourse;
        hash = 53 * hash + Objects.hashCode(this.course_title);
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
        final Course other = (Course) obj;
        if (this.idcourse != other.idcourse) {
            return false;
        }
        if (!Objects.equals(this.course_title, other.course_title)) {
            return false;
        }
        return true;
    }

}
