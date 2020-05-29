package hall.androidcalendar.ui;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;

import ca.antonious.materialdaypicker.MaterialDayPicker;
import hall.androidcalendar.Calendar;
import hall.androidcalendar.R;

public class EventRepeatFragment extends Fragment implements DialogSelectRepeatEndDate.DateDialogListener,
        DialogSelectEventCustomRepeat.EventRepListener {
    private RelativeLayout rl;
    private TextView tv;
    private EditText ed;
    private RelativeLayout.LayoutParams par;
    private TextView durationOption;
    private LinearLayout linearLayout;
    private RadioButton rb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_event_repeat);
//        rl = findViewById(R.id.rl_duration_selector);
//        durationOption = findViewById(R.id.tv_duration_option);
//        tv = new TextView(this);
//        ed = new EditText(this);
//        ed.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//
//        par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//        par.rightMargin = 10;
//        rl.addView(tv, 1, par);
//        tv.setText(Calendar.getDate()[0].substring(0, 18));
//        tv.setClickable(true);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDateDialog();
//            }
//        });
//        durationOption.setText("Until date");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_event_repeat, container, false);
       linearLayout = view.findViewById(R.id.event_rep_option);
        ed = new EditText(this.getContext());
        ed.setInputType(InputType.TYPE_CLASS_NUMBER);
       tv = new TextView(this.getContext());
       rb = view.findViewById(R.id.event_r_custom);
        durationOption = view.findViewById(R.id.tv_duration_option);
        par = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        par.rightMargin = 10;
        tv.setClickable(true);

       linearLayout.setOnClickListener(new LinearLayout.OnClickListener() {
           @Override
           public void onClick(View v) {
               showPopup(v);
           }
       });
       tv.setOnClickListener(new TextView.OnClickListener() {
           @Override
           public void onClick(View v) {
               openDateDialog();
           }
       });
        rb.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(v);
            }
        });
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        durationOption.setText("Until Date");
        rl = view.findViewById(R.id.rl_duration_selector);
        rl.addView(tv, 1, par);
        tv.setText(Calendar.getDate()[0].substring(0, 18));
    }

    public void openDateDialog(){
        DialogSelectRepeatEndDate dialogSelectRepeatEndDate = new DialogSelectRepeatEndDate();
        dialogSelectRepeatEndDate.setTargetFragment(this, 0);
        dialogSelectRepeatEndDate.show(getActivity().getSupportFragmentManager(), "date");
    }

    private void openCustomDialog(View view){
        DialogSelectEventCustomRepeat dialogSelectEventCustomRepeat = new DialogSelectEventCustomRepeat();
        dialogSelectEventCustomRepeat.setTargetFragment(this, 0);
        dialogSelectEventCustomRepeat.show(this.getActivity().getSupportFragmentManager(), "event");
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this.getContext(), v);
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
