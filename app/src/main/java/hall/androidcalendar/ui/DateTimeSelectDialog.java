package hall.androidcalendar.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hall.androidcalendar.R;

public class DateTimeSelectDialog extends AppCompatDialogFragment implements PopupMenu.OnMenuItemClickListener {
    private CalendarView calendarView;
    private DateTimeSelectDialog.DateDialogListener lsitener;
    private LocalDateTime date;
    private TimePicker timePicker;
    private TextView dur;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_select_date, null);
        date = LocalDateTime.now().withSecond(0).withNano(0);
     //   dur = view.findViewById(R.id.event_rep_duration);
    //    dur.setText("date");
        builder.setView(view)
                .setTitle("Select Date")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        lsitener.sendDate(date);
                    }
                });
        timePicker = view.findViewById(R.id.event_time);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                date = date.withHour(timePicker.getHour());
                date = date.withMinute(timePicker.getMinute());
            }
        });
        calendarView = view.findViewById(R.id.event_date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = date.withYear(year);
                date = date.withMonth(month + 1);
                date = date.withDayOfMonth(dayOfMonth);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        try {
            lsitener = (DateTimeSelectDialog.DateDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement dateDialog");
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public interface DateDialogListener{
        void sendDate(LocalDateTime date);
    }

}
