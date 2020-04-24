package hall.androidcalendar.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hall.androidcalendar.Alert;
import hall.androidcalendar.Event;
import hall.androidcalendar.R;
import hall.androidcalendar.ui.CalendarActivity;
import hall.androidcalendar.ui.SelectDate;

public class EventActivity extends AppCompatActivity {
    private EditText event_name;
    private EditText tags;
    private EditText location;

    private TextView startTime;
    private TextView endTime;

    private RecyclerView alerts;
    private RecyclerView invitees;
    public static Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        startTime = findViewById(R.id.tv_event_start_info);
        endTime = findViewById(R.id.tv_event_end_info);
        String[] dates  = CalendarActivity.currentCalendar.getDate();
        startTime.setText(dates[0]);
        endTime.setText(dates[1]);
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



    }

    public void setDate(View view){
        Intent intent = new Intent(this, SelectDate.class);
        startActivity(intent);
    }





}
