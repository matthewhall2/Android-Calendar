package hall.androidcalendar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class createAccountActivity extends AppCompatActivity {



    private EditText username;
    private EditText password;
    private EditText confirmPassword;

    private TextView usernameMessage;
    private TextView passwordMessage;
    private TextView confirmPasswordMessage;
    private TextView passwordErrorMessage;

    private Button signUp;

  //  private UserManager userManager = MainActivity.userManager;
    private ArrayList<String> usernames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");

        try {
            usernames = new ArrayList<>();
            List<ParseObject> users =query.find();
            for(ParseObject u: users){
                usernames.add(u.get("username").toString());
            }
        }catch(ParseException p){
            Log.d("users", "could not find");
        }


        username = findViewById(R.id.edCreateUserName);
        password = findViewById(R.id.edCreatedPassword);
        confirmPassword = findViewById(R.id.edConfirmPassword);

        usernameMessage = findViewById(R.id.tvCreateUserNameMessage);
        passwordMessage = findViewById(R.id.tvCreatePasswordMessage);
        confirmPasswordMessage = findViewById(R.id.tvConfirmPasswordMessage);
        passwordErrorMessage = findViewById(R.id.tvPasswordErrorMessage);

        TextWatcher usernameWtacher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = username.getText().toString();
                if (usernameVaild()){

                    if (!usernameAvailable()){
                        usernameMessage.setText(R.string.username_not_available);
                    }else{
                        usernameMessage.setText(R.string.username_available);
                    }
                }else if(!usernameVaild()){
                    usernameMessage.setText(R.string.create_username_error_message);
                }
            }
        };
        username.addTextChangedListener(usernameWtacher);

        TextWatcher passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordInvalid()){
                    passwordErrorMessage.setText(R.string.password_not_correct_format);
                }else{
                    passwordErrorMessage.setText(R.string.password_correct_format);
                }
            }
        };
        password.addTextChangedListener(passwordWatcher);

        TextWatcher passwordMatchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = password.getText().toString();
                String comfirm = confirmPassword.getText().toString();
                if(passwordsMatch()){
                    confirmPasswordMessage.setText(R.string.passwords_match);
                }
                else{
                    confirmPasswordMessage.setText(R.string.passwords_dont_match);
                }
            }
        };
        confirmPassword.addTextChangedListener(passwordMatchWatcher);
    }

    private boolean passwordsMatch(){
        String pass = password.getText().toString();
        String confirm = confirmPassword.getText().toString();
        return pass.equals(confirm);
    }

    private boolean usernameVaild(){
        String name = username.getText().toString();
        return (name.matches("^[A-Za-z0-9]{5,}$"));
    }

    private boolean usernameAvailable(){
        String name = username.getText().toString();
        return !usernames.contains(name);
    }

    private boolean passwordInvalid(){
        String pass = password.getText().toString();
        return pass.matches("^([^,]{0,7}|[^,]{20,}|[^0-9]*|[^a-z]*|[^A-Z]*|[^!@#$%^&*]*)$");
    }

    public void createAccount(View view) {

        if (usernameVaild() && usernameAvailable() && !passwordInvalid() && passwordsMatch()) {
//            userManager.createAccount(username.getText().toString(), "", password.getText().toString());

            final ParseUser user = new ParseUser();
            // Set the user's username and password, which can be obtained by a forms
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            UserManager u = new UserManager(this);
            boolean done = u.createAccount(username.getText().toString(), password.getText().toString());
            if (done) {
                finish();
            }
        }
    }


}
