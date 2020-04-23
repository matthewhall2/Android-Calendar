package hall.androidcalendar.ui.events;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hall.androidcalendar.Event;
import hall.androidcalendar.R;
import hall.androidcalendar.ui.CalendarActivity;

public class EventActivity extends AppCompatActivity {
    private EditText event_name;
    private TextView start;
    private TextView end;
    private TextView startTime;
    private TextView endTime;
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
    }





}
