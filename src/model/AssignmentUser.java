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

    public AssignmentUser() {
    }

    public AssignmentUser(Assignment assignment, User user) {
        this.assignment = assignment;
        this.user = user;
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
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.assignment);
        hash = 79 * hash + Objects.hashCode(this.user);
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
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssignmentUser{" + "assignment=" + assignment + ", user=" + user + '}';
    }
    
    
    
}
