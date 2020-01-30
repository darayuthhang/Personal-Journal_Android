package com.darayuth.personaljournalapp.model;

public class JournalCalendar {
    private String dayOfmonth;
    private String month;
    private String year;


    public JournalCalendar(String dayOfmonth, String month, String year) {
        this.dayOfmonth = dayOfmonth;
        this.month = month;
        this.year = year;
    }

    public String getDayOfmonth() {
        return dayOfmonth;
    }

    public void setDayOfmonth(String dayOfmonth) {
        this.dayOfmonth = dayOfmonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
