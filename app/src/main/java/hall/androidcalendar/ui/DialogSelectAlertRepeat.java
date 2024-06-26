package hall.androidcalendar.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatSpinner;

import hall.androidcalendar.R;

public class DialogSelectAlertRepeat extends AppCompatDialogFragment {
    private AppCompatSpinner spinner;
    private EditText selectNum;
    private AlertDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AlertDialogListener)getTargetFragment();
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement sendAlertInfo");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_rep_alert, null);
        spinner = view.findViewById(R.id.alert_rep_spinner);
        selectNum = view.findViewById(R.id.alert_rep_num);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.alert_rep_menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Select alert")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int freq = Integer.parseInt(selectNum.getText().toString());
                        String type = spinner.getSelectedItem().toString();

                    }
                });
        return builder.create();

    }

    public interface AlertDialogListener{
        void sendAlertInfo(int freq, String type);
    }
}
