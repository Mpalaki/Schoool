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
public class AssignmentCourse {

    private Assignment assignment;
    private Course course;
    private int mark;
    private boolean submitted;

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public int getMark() {
        return mark;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public AssignmentCourse() {
    }

    public AssignmentCourse(Assignment assignment, Course course) {
        this.assignment = assignment;
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.assignment);
        hash = 17 * hash + Objects.hashCode(this.course);

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
        final AssignmentCourse other = (AssignmentCourse) obj;
        
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "submitted= " + submitted ;
    }

    
}
