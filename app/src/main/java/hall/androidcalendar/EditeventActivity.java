package hall.androidcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class EditeventActivity extends AppCompatActivity implements View.OnClickListener{
    private  EditText event_name;
    public static Event e;
    private TextView event_message;
    private Button back_b;
    private Button event_b;
    private Button tag_b;
    private Button alert_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);
        event_name = findViewById(R.id.editeventname);
        event_message = findViewById(R.id.event_message);
        back_b = findViewById(R.id.button);
        event_b = findViewById(R.id.button2);
        tag_b = findViewById(R.id.button3);
        alert_b = findViewById(R.id.button4);
    }


    public boolean matched_name(){
        String name = event_name.getText().toString();
        boolean flag = false;
        for (Event e : CalendarActivity.currentCalendar.getEvents()){
            if (e.getName().equals(name)) {
                this.e = e;
                flag = true;
                break;
            }
        }
        if (flag == false) {
            this.e = null;
        }
        return (this.e.equals(null));
    }

    public static Event get_e(){
        return e;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                Intent ev = new Intent(this, modifyeventActivity.class);
                startActivity(ev);
            case R.id.button3:
                Intent ta = new Intent(this, modifytag.class);
                startActivity(ta);
            case R.id.button4:
                Intent al = new Intent(this, modifyalert.class);
            case R.id.button:
                break;
        }

    }
}
