package me.caneva20.Core.CNVScheduler;

import me.caneva20.Core.Util.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CNVTime {
    private int hour;
    private int minute;

    private boolean executed;

    public CNVTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public CNVTime(String time) {
        CNVTime cnvTime = Util.convertToCNVTime(time);

        this.hour = cnvTime != null ? cnvTime.getHour() : 0;
        this.minute = cnvTime != null ? cnvTime.getMinute() : 0;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void execute () {
        executed = true;
    }

    public void reset () {
        executed = false;
    }

    public boolean canExecute (int tolerance) {
        if (executed) {
            return false;
        }

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        int executeTime = this.hour * 60 + this.minute;
        int now = hour * 60 + min;

        return now >= executeTime - tolerance && now <= executeTime + tolerance;
    }
}















