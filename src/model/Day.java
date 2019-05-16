/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Makis
 */
public class Day {
    private int iddays;
    private Date working_day;

    public Day() {
    }

    public Day(int iddays, Date working_day) {
        this.iddays = iddays;
        this.working_day = working_day;
    }

    public int getIddays() {
        return iddays;
    }

    public Date getWorking_day() {
        return working_day;
    }

    public void setIddays(int iddays) {
        this.iddays = iddays;
    }

    public void setWorking_day(Date working_day) {
        this.working_day = working_day;
    }

    @Override
    public String toString() {
        return "Day{" + "iddays=" + iddays + ", working_day=" + working_day + '}';
    }

    
    
}
