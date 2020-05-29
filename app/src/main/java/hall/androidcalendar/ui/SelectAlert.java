package hall.androidcalendar.ui;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import hall.androidcalendar.R;

public class SelectAlert extends AppCompatActivity {
    RadioGroup rg_ind;
    RadioGroup rg_rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_alert);
        this.rg_ind = findViewById(R.id.rg_ind);
        this.rg_rep = findViewById(R.id.rg_rep);
        this.rg_ind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                finish();
            }
        });
        this.rg_rep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAlert();
                finish();
            }
        });
    }

    public void setAlert(){
        DialogSelectAlertRepeat dialogSelectAlertRepeat = new DialogSelectAlertRepeat();
        dialogSelectAlertRepeat.show(getSupportFragmentManager(), "alerts");
    }
}
