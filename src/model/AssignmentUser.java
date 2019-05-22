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
public class AssignmentUser {
    private Assignment assignment;
    private User user;
    private int mark;
    private boolean submitted;

    public AssignmentUser() {
    }

    public AssignmentUser(Assignment assignment, User user) {
        this.assignment = assignment;
        this.user = user;
    }

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

    public Assignment getAssignment() {
        return assignment;
    }

    public User getUser() {
        return user;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return " submitted=" + submitted ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.assignment);
        hash = 31 * hash + Objects.hashCode(this.user);
        hash = 31 * hash + this.mark;
        hash = 31 * hash + (this.submitted ? 1 : 0);
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
        final AssignmentUser other = (AssignmentUser) obj;
        if (this.mark != other.mark) {
            return false;
        }
        if (this.submitted != other.submitted) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

   
    
}
