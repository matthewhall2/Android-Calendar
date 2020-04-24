package hall.androidcalendar.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import hall.androidcalendar.R;

public class DateSelectRDialog extends AppCompatDialogFragment {

    private CalendarView calendarView;
    private DateDialogListener lsitener;
    private LocalDate date;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_select_repeat_until, null);

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
        calendarView = view.findViewById(R.id.cal_view_event_r);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0).toLocalDate();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            lsitener = (DateDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement dateDialog");
        }

    }

    public interface DateDialogListener{
        void sendDate(LocalDate date);
    }
}
