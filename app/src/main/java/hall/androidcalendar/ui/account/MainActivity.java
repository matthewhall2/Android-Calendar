package hall.androidcalendar.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseInstallation;

import hall.androidcalendar.R;
import hall.androidcalendar.UserManager;
import hall.androidcalendar.ui.CalendarActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private TextView passwordMessage;

    private Button loginButton;
    private Button signUpButton;



    UserManager userManager;
    private boolean correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        userManager = new UserManager(this);
        username = findViewById(R.id.edUserName);
        password = findViewById(R.id.edPassword);

        passwordMessage = findViewById(R.id.tvpasswordinfo);
        loginButton = findViewById(R.id.btnLogin);
        signUpButton = findViewById(R.id.btnSignUp);

        //   userManager = new UserManager(this);
    }
    String data = "";



    public void login(View view){
        final String currentUserName = username.getText().toString();
        final String currentPassword = password.getText().toString();
        this.correct = false;

        correct = userManager.logIn(currentUserName, currentPassword);
        if(correct){
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        }

    }

    public void createAccount(View view){
        Intent intent = new Intent(this, createAccountActivity.class);
        startActivity(intent);
    }
}

