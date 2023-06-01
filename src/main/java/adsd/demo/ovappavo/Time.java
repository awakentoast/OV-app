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

    public String getCurrentTime(){
        return  hour + ":" + minute + ":" + second;
    }

    public void oneSecondPassed(){
        second++;
        if (second == 60) {
            minute++;
            second = 0;
            if (minute == 60){
                hour++;
                minute = 0;
                if (hour == 24){
                    hour = 0;
                    System.out.println("next day" );
                }
            }
        }
    }

}
