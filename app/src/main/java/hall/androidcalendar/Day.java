package hall.androidcalendar;


import java.time.LocalDate;
import java.util.ArrayList;

public class Day implements Comparable<Day>{
    private LocalDate date;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;
    private String name;
    private String month;




    public Day(LocalDate date) {
        this.date = date;
        this.name = date.getDayOfWeek().name();
        this.month = Integer.toString(date.getDayOfMonth());
        this.events = new ArrayList<>();
    }

    public LocalDate getDay(){
        return this.date;
    }

    public String getDayName(){
        return this.name;
    }

    public int getWeekNum(){
        return this.date.getDayOfWeek().getValue();
    }

    public String getMonthDayNumber(){
        return this.month;
    }

    public void addEvent(Event event) {
        this.events.add(event);
        //Collections.sort(this.events);
    }
    public void removeEvent(Event event) {
        this.events.remove(event);
    }
    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }
    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    @Override
    public int compareTo(Day other) {
        return this.date.compareTo(other.date);
    }


}
