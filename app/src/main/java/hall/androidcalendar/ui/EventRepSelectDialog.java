package hall.androidcalendar.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatSpinner;

import java.time.LocalDateTime;

import hall.androidcalendar.R;

public class EventRepSelectDialog extends AppCompatDialogFragment {
     EventRepSelectDialog.EventRepListener lsitener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_rep_event, null);
        AppCompatSpinner spinner = view.findViewById(R.id.event_rep_type);
        TextView textview = view.findViewById(R.id.mon);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textview.isSelected()){
                    textview.setSelected(false);
                }else{
                    textview.setSelected(true);
                }
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.event_rep_menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Select Frequency")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        try {
            lsitener = (EventRepSelectDialog.EventRepListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement dateDialog");
        }
    }

    public interface EventRepListener{
        void sendFreq(int number, String type);
    }
}
