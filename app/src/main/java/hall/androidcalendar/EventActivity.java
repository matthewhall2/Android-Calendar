package hall.androidcalendar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
       // event_name = findViewById(R.id.tv_event_title);
       // start = findViewById(R.id.tv_start);
      //  startTime = findViewById(R.id.tv_start_date);
//        end = findViewById(R.id.tv_end);
//        endTime = findViewById(R.id.tv_end_date);




    }





}
