package hall.androidcalendar.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import hall.androidcalendar.ui.SelectAlert;
import hall.androidcalendar.ui.SelectDate;

public class EventActivity extends Fragment implements DateTimeSelectDialog.DateDialogListener,
        AlertRepSelectDialog.AlertDialogListener {

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

    private RelativeLayout info_start;
    private RelativeLayout info_end;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        calendar = CalendarActivity.currentCalendar;
//        setContentView(R.layout.activity_event);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_event, container, false);
        this.info_start = view.findViewById(R.id.info_start);
        this.info_end = view.findViewById(R.id.info_end);
        this.info_start.setOnClickListener(new RelativeLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartDate();
            }
        });
        this.info_end.setOnClickListener(new RelativeLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEndDate();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startTime = view.findViewById(R.id.tv_event_start_info);
        endTime = view.findViewById(R.id.tv_event_end_info);
        LocalDateTime[] dates  = Calendar.getCurrentDate();
        startTime.setText(this.getDateString(dates[0]));
        endTime.setText(this.getDateString(dates[1]));
        event_name = view.findViewById(R.id.event_name);
        tags = view.findViewById(R.id.event_tags);
        alerts = view.findViewById(R.id.reminders);
        invitees = view.findViewById(R.id.invitees);
        location = view.findViewById(R.id.event_location);

        ArrayList<Alert>  alert = new ArrayList<>();
        Alert al = new Alert();
        alert.add(al);
        AlertsAdapter alertsAdapter = new AlertsAdapter(this.getActivity(), alert);
        LinearLayoutManager alertsLayoutManager = new LinearLayoutManager(this.getActivity());
        alerts.setAdapter(alertsAdapter);
        alerts.setLayoutManager(alertsLayoutManager);

        ArrayList<String> invites = new ArrayList<>();
        InviteeAdapter inviteeAdapter = new InviteeAdapter(this.getActivity(), invites);
        LinearLayoutManager inviteeLayoutManager = new LinearLayoutManager(this.getActivity());
        invitees.setAdapter(inviteeAdapter);
        invitees.setLayoutManager(inviteeLayoutManager);
        event = new Event();

    }

    public void setStartDate(){
       this.isStart = true;
        DateTimeSelectDialog dateSelectRDialog = new DateTimeSelectDialog();
        dateSelectRDialog.setTargetFragment(this, 0);
        dateSelectRDialog.show(this.getActivity().getSupportFragmentManager(), "date");
    }

    public void setEndDate(){
        this.isStart = false;
        DateTimeSelectDialog dateSelectRDialog = new DateTimeSelectDialog();
        dateSelectRDialog.setTargetFragment(this, 0);
        dateSelectRDialog.show(getActivity().getSupportFragmentManager(), "date");
    }

    public void repEvent(View view){
        Intent intent = new Intent(getActivity(), EventRepeat.class);
        startActivity(intent);
    }

    private String getDateString(LocalDateTime date){
        return date.getDayOfWeek().name().substring(0, 3) + "., " +
                date.getMonth().name().substring(0, 3) + ". " +  date.getDayOfMonth() +
                ", " + date.getYear() + ", " + date.getHour() + ":" + date.getMinute();
    }

    public void selectAlert(View view){
        Intent intent = new Intent(getActivity(), SelectAlert.class);
        startActivity(intent);
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

    @Override
    public void sendAlertInfo(int freq, String type) {

    }
}
