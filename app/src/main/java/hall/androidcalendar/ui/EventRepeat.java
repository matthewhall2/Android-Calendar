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

public class EventRepeat extends AppCompatActivity implements DateSelectRDialog.DateDialogListener {
    RelativeLayout rl;
    TextView tv;
    EditText ed;
    RelativeLayout.LayoutParams par;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_repeat);
        rl = findViewById(R.id.rl_duration_selector);

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
                openDialog();
            }
        });
    }

    public void openDialog(){
        DateSelectRDialog dateSelectRDialog = new DateSelectRDialog();
        dateSelectRDialog.show(getSupportFragmentManager(), "date");
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
                        return true;

                    case R.id.dur_num:
                        rl.removeViewAt(1);                        rl.addView(ed, 1, par);
                        ed.setText("1");
                        return true;

                }
                return false;
            }
        });
    }


    @Override
    public void sendDate(LocalDate date) {
        tv.setText(date.toString());
    }
}
