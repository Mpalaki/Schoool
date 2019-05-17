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
public class AssignmentCourse {

    private Assignment assignment;
    private Course course;

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
    public String toString() {
        return "AssignmentCourse{" + "assignment=" + assignment + ", course=" + course + '}';
    }

}
