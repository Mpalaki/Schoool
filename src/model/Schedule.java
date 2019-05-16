/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;

/**
 *
 * @author Makis
 */
public class Schedule {
    private Course course;
    private Day working_Day;
    private Time start_time;
    private Time end_time;

    public Schedule() {
    }

    public Schedule(Course course, Day working_Day, Time start_time, Time end_time) {
        this.course = course;
        this.working_Day = working_Day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Course getCourse() {
        return course;
    }

    public Day getWorking_Day() {
        return working_Day;
    }

    public Time getStart_time() {
        return start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setWorking_Day(Day working_Day) {
        this.working_Day = working_Day;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Schedule{" + "course=" + course + ", working_Day=" + working_Day + ", start_time=" + start_time + ", end_time=" + end_time + '}';
    }
    
    
}
