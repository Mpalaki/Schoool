/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Makis
 */
public class Schedule {
    private int idcourse;
    private Date working_Day;
    

    public Schedule() {
    }

    public Schedule(int idcourse, Date working_Day) {
        this.idcourse = idcourse;
        this.working_Day = working_Day;
    }

    public int getIdcourse() {
        return idcourse;
    }

    public Date getWorking_Day() {
        return working_Day;
    }

    public void setIdcourse(int idcourse) {
        this.idcourse = idcourse;
    }

    public void setWorking_Day(Date working_Day) {
        this.working_Day = working_Day;
    }

    @Override
    public String toString() {
        return "Schedule{" + "idcourse=" + idcourse + ", working_Day=" + working_Day + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idcourse;
        hash = 47 * hash + Objects.hashCode(this.working_Day);
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
        final Schedule other = (Schedule) obj;
        if (this.idcourse != other.idcourse) {
            return false;
        }
        if (!Objects.equals(this.working_Day, other.working_Day)) {
            return false;
        }
        return true;
    }

   
    
}
