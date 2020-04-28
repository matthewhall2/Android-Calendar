package hall.androidcalendar.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import hall.androidcalendar.R;

public class SelectAlert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_alert);

    }

    public void setAlert(View view){
        AlertRepSelectDialog alertRepSelectDialog = new AlertRepSelectDialog();
        alertRepSelectDialog.show(getSupportFragmentManager(), "alerts");
    }
}
