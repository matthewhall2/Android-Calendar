package hall.androidcalendar;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Event implements Comparable<Event>{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;
    private ArrayList<String> linkedSeriesNames = new ArrayList<>();
    private ArrayList<String> durationSeriesNames = new ArrayList<>();
    private ArrayList<String> series;
    private ArrayList<Memo> memos = new ArrayList<>();

    public Event(LocalDateTime start, LocalDateTime end, String name, ArrayList<String> tags,
                 ArrayList<Alert> alerts, ArrayList<String> series) {
        this.startTime = start;
        this.endTime = end;
        this.name = name;
        this.tags = tags;
        this.alerts = alerts;
        this.series = new ArrayList<>();
        this.durationSeriesNames = new ArrayList<>();
        this.series.addAll(series);
        this.durationSeriesNames.addAll(series);
    }

    public Event(){
        LocalDateTime[] dates = Calendar.getCurrentDate();
        this.startTime = dates[0];
        this.endTime = dates[1];
    }

    public Event(String name){
        this.name = name;
    }

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return  this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return new ArrayList<>(this.tags);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public ArrayList<Alert> getAlerts() {
        return new ArrayList<>(this.alerts);
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
        // point alert to this event
    }

    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    public ArrayList<String> getSeries() {
        return new ArrayList<>(this.series);
    }

    public ArrayList<String> getLinkedSeriesNames(){
        return this.linkedSeriesNames;
    }

    public ArrayList<String> getDurationSeriesNames(){
        return durationSeriesNames;
    }

    public void addSeries(String series) {
        this.linkedSeriesNames.add(series);
    }

    public void removeSeries(String name) {
        this.series.remove(name);
    }

    public ArrayList<Memo> getMemos() {
        return this.memos;
    }

    public void addMemo(Memo newMemo) {
        this.memos.add(newMemo);
    }
    
    public void removeMemo(Memo newMemo) {
        this.memos.remove(newMemo);
    }


    @Override
    public int compareTo(Event e) {
        int equalStart = this.startTime.compareTo(e.startTime);
        int equalEnd = this.endTime.compareTo(e.endTime);
        if (equalStart != 0)
            return  equalStart;
        else if (equalEnd != 0)
            return equalEnd;
        else
            return this.name.compareTo(e.name);
    }

    /**
     *
     * @return a string representing the event in file
     */
    public ArrayList<String> eventFileFormatter(){
        ArrayList<String> event = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        event.add(this.name);
        event.add(this.startTime.toString());
        event.add(this.endTime.toString());

        s.append("[");
        for(Alert alert: this.alerts){
            s.append(alert.alertFileFormatter()).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("]");
        event.add(s.toString());
        s = new StringBuilder();
        s.append("[");
        for(String tag:this.tags){
            s.append(tag).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("]");
        event.add(s.toString());
        s = new StringBuilder();
        s.append("[");
        for(Memo memo:this.memos){
            s.append(memo.getText()).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("]");
        event.add(s.toString());
        s = new StringBuilder();
        s.append("[[");
        for(String serie: this.durationSeriesNames){
            s.append(serie).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("],[");
        for(String serie: this.linkedSeriesNames){
            s.append(serie).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("]]");
        event.add(s.toString());
        return event;
    }
}
