package hall.androidcalendar.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import hall.androidcalendar.Alert;
import hall.androidcalendar.Calendar;
import hall.androidcalendar.Event;
import hall.androidcalendar.R;
import hall.androidcalendar.ui.AlertRepSelectDialog;
import hall.androidcalendar.ui.CalendarActivity;
import hall.androidcalendar.ui.DateSelectRDialog;
import hall.androidcalendar.ui.DateTimeSelectDialog;
import hall.androidcalendar.ui.EventRepeat;
import hall.androidcalendar.ui.SelectDate;

public class EventActivity extends AppCompatActivity implements DateTimeSelectDialog.DateDialogListener {
    private EditText event_name;
    private EditText tags;
    private EditText location;

    private TextView startTime;
    private TextView endTime;

    private RecyclerView alerts;
    private RecyclerView invitees;
    private Calendar calendar;
    public  Event event;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        calendar = CalendarActivity.currentCalendar;
        startTime = findViewById(R.id.tv_event_start_info);
        endTime = findViewById(R.id.tv_event_end_info);
        LocalDateTime[] dates  = Calendar.getCurrentDate();
        startTime.setText(this.getDateString(dates[0]));
        endTime.setText(this.getDateString(dates[1]));
        event_name = findViewById(R.id.event_name);
        tags = findViewById(R.id.event_tags);
        alerts = findViewById(R.id.reminders);
        invitees = findViewById(R.id.invitees);
        location = findViewById(R.id.event_location);

        ArrayList<Alert>  alert = new ArrayList<>();
        Alert al = new Alert();
        alert.add(al);
        AlertsAdapter alertsAdapter = new AlertsAdapter(this, alert);
        LinearLayoutManager alertsLayoutManager = new LinearLayoutManager(this);
        alerts.setAdapter(alertsAdapter);
        alerts.setLayoutManager(alertsLayoutManager);

        ArrayList<String> invites = new ArrayList<>();
        InviteeAdapter inviteeAdapter = new InviteeAdapter(this, invites);
        LinearLayoutManager inviteeLayoutManager = new LinearLayoutManager(this);
        invitees.setAdapter(inviteeAdapter);
        invitees.setLayoutManager(inviteeLayoutManager);
        event = new Event();

    }

    public void setStartDate(View view){
       this.isStart = true;
        DateTimeSelectDialog dateSelectRDialog = new DateTimeSelectDialog();
        dateSelectRDialog.show(getSupportFragmentManager(), "date");
    }

    public void setEndDate(View view){
        this.isStart = false;
        DateTimeSelectDialog dateSelectRDialog = new DateTimeSelectDialog();
        dateSelectRDialog.show(getSupportFragmentManager(), "date");

    }

    public void repEvent(View view){
        Intent intent = new Intent(this, EventRepeat.class);
        startActivity(intent);
    }

    private String getDateString(LocalDateTime date){
        return date.getDayOfWeek().name().substring(0, 3) + "., " +
                date.getMonth().name().substring(0, 3) + ". " +  date.getDayOfMonth() +
                ", " + date.getYear() + ", " + date.getHour() + ":" + date.getMinute();
    }


    @Override
    public void sendDate(LocalDateTime date) {
        if(isStart){
            event.setStartTime(date);
            startTime.setText(this.getDateString(date));
        }else{
            event.setEndTime(date);
            endTime.setText(this.getDateString(date));
        }

    }
}
