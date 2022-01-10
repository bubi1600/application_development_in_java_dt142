package com.example.staffapp.api.database.table.classes;

public class TimeTableClass {
    private Integer timetableid;
    private Integer employeeid;
    private String working;
    private String date;

    public TimeTableClass(Integer timetableid, Integer employeeid, String working, String date) {
        this.timetableid = timetableid;
        this.employeeid = employeeid;
        this.working = working;
        this.date = date;
    }
}
