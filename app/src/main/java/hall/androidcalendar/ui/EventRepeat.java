package hall.androidcalendar.ui;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

import hall.androidcalendar.Calendar;
import hall.androidcalendar.R;

public class EventRepeat extends AppCompatActivity implements DateSelectRDialog.DateDialogListener, EventRepSelectDialog.EventRepListener {
    RelativeLayout rl;
    TextView tv;
    EditText ed;
    RelativeLayout.LayoutParams par;
    TextView durationOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_repeat);
        rl = findViewById(R.id.rl_duration_selector);
        durationOption = findViewById(R.id.tv_duration_option);
        tv = new TextView(this);
        ed = new EditText(this);
        par = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        par.rightMargin = 10;
        rl.addView(tv, 1, par);
        tv.setText(Calendar.getDate()[0].substring(0, 18));
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });
        durationOption.setText("Until date");
    }

    public void openDateDialog(){
        DateSelectRDialog dateSelectRDialog = new DateSelectRDialog();
        dateSelectRDialog.show(getSupportFragmentManager(), "date");
    }

    public void openCustomDialog(View view){
        EventRepSelectDialog eventRepSelectDialog = new EventRepSelectDialog();
        eventRepSelectDialog.show(getSupportFragmentManager(), "event");
    }

        public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_duration, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.dur_until:
                        rl.removeViewAt(1);
                        rl.addView(tv,1,  par);
                        tv.setText(Calendar.getDate()[0].substring(0, 18));
                        durationOption.setText("Until Date");
                        return true;

                    case R.id.dur_num:
                        rl.removeViewAt(1);
                        rl.addView(ed, 1, par);
                        ed.setText("1");
                        durationOption.setText("Number of Times");
                        return true;

                }
                return false;
            }
        });
    }


    @Override
    public void sendDate(LocalDate date) {
        tv.setText(Calendar.getDateString(date));
    }

    @Override
    public void sendFreq(int number, String type) {

    }
}
