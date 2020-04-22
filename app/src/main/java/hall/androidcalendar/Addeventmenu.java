package hall.androidcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Addeventmenu extends AppCompatActivity {
    private EditText event_name;
    private TextView start;
    private TextView end;
    private TextView startTime;
    private TextView endTime;
    public static Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
       // event_name = findViewById(R.id.tv_event_title);
       // start = findViewById(R.id.tv_start);
      //  startTime = findViewById(R.id.tv_start_date);
//        end = findViewById(R.id.tv_end);
//        endTime = findViewById(R.id.tv_end_date);




    }





}
