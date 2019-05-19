/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Makis
 */
public class Assignment {

    private int idassignment;
    private String title;
    private Timestamp submission_date_time;

    public Assignment(String title) {
        this.title = title;
    }

    public Assignment(int idassignment, String title) {
        this.idassignment = idassignment;
        this.title = title;
    }

    public Assignment() {
    }

    public Assignment(int idassignment, String title, Timestamp submission_date_time) {
        this.idassignment = idassignment;
        this.title = title;
        this.submission_date_time = submission_date_time;
    }

    public int getIdassignment() {
        return idassignment;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getSubmission_date_time() {
        return submission_date_time;
    }

    public void setIdassignment(int idassignment) {
        this.idassignment = idassignment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubmission_date_time(Timestamp submission_date_time) {
        this.submission_date_time = submission_date_time;
    }

    @Override
    public String toString() {
        return "Assignment{" + "idassignment=" + idassignment + ", title=" + title + ", submission_date_time=" + submission_date_time + '}';
    }

    public Assignment(String title, Timestamp submission_date_time) {
        this.title = title;
        this.submission_date_time = submission_date_time;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idassignment;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.submission_date_time);
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
        final Assignment other = (Assignment) obj;
        if (this.idassignment != other.idassignment) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.submission_date_time, other.submission_date_time)) {
            return false;
        }
        return true;
    }

}
