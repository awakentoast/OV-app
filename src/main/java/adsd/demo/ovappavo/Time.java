package adsd.demo.ovappavo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    
    private int hour;
    private int minute;
    private int second;
    
    public Time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String[] times = dtf.format(now).split(":");
        hour = Integer.parseInt(times[0]);
        minute = Integer.parseInt(times[1]);
        second = Integer.parseInt(times[2]);
    }
    
    public String getCurrentTime() {
        String tempHour = hour > 9 ? Integer.toString(hour) : "0" + hour;
        String tempMinute = minute > 9 ? Integer.toString(minute) : "0" + minute;
        String tempSecond = second > 9 ? Integer.toString(second) : "0" + second;
        
        return tempHour + ":" + tempMinute + ":" + tempSecond;
    }
    
    public int getHour() {
        return hour;
    }
    
    public int getMinute() {
        return minute;
    }
    
    public void oneSecondPassed() {
        second++;
        if (second == 60) {
            minute++;
            second = 0;
            if (minute == 60) {
                hour++;
                minute = 0;
                if (hour == 24) {
                    hour = 0;
                    System.out.println("next day");
                }
            }
        }
    }
}
