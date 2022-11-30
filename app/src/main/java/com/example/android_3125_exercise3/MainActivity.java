package com.example.android_3125_exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private Switch swtBtn;
    private Button btnAction;
    private TextView titleText;
    private EditText username;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    static Map<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swtBtn = findViewById(R.id.swtBtn);
        btnAction = findViewById(R.id.btn);
        titleText = findViewById(R.id.textView);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);

        setUpSwitchAction();
        setBtnAction();
    }

    private void setUpSwitchAction() {
        swtBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = (isChecked) ? View.VISIBLE : View.GONE;
                String btnTitle = (isChecked) ? "Register" : "Login";
                firstname.setVisibility(visibility);
                lastname.setVisibility(visibility);
                email.setVisibility(visibility);

                btnAction.setText(btnTitle);
            }
        });
    }

    private void setBtnAction() {
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().isEmpty()){
                    showShortToast("User Name is empty");
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    showShortToast("Password is empty");
                    return;
                }

                if(swtBtn.isChecked()){
                    if(firstname.getText().toString().isEmpty()){
                        showShortToast("First name is empty");
                        return;
                    }
                    if(lastname.getText().toString().isEmpty()){
                        showShortToast("Last name is empty");
                        return;
                    }
                    if(email.getText().toString().isEmpty()){
                        showShortToast("Email is empty");
                        return;
                    }
                    register(username.getText().toString(), password.getText().toString());
                }
                else{
                    login(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void register(String username, String password){
        if (users.containsKey(username)) {
            showShortToast("User already exists");
        } else {
            users.put(username, password);
            showShortToast("User Successfully registered.");
        }
    }
    private void login(String username, String password){
        Map<String, String> result = users.entrySet()
                .stream()
                .filter(map -> map.getKey().equals(username) && map.getValue().equals(password))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println("Result: " + result);

        if (result.size() > 0) {
            showShortToast("LOGIN SUCCESSFUL");
        } else {
            showShortToast("Password or user does not match.");
        }
    }


    /*
    *  private Button signUp;
    private EditText regisUsername;
    private EditText regisPassword;
    private EditText firstname;
    private EditText lastname;
    private EditText regisEmail;
    private Map<String, String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regisUsername = findViewById(R.id.regisUsername);
        regisPassword = findViewById(R.id.regisPassword);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        regisEmail = findViewById(R.id.regisEmail);

        signUp = findViewById(R.id.btnSignupAction);
        setSignUpAction();


    }

    public void setSignUpAction() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    showDialogAlert();
                }
            }
        });
    }

    private void showDialogAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Signup Action")
                .setMessage("Are you sure?")

                .setPositiveButton("Yes, I am sure!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setNegativeButton("No!", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean validateFields() {
        if (regisUsername.getText().equals("")) {
            showShortToast("Please input Username");
            return false;
        } else if (regisPassword.getText().equals("")) {
            showShortToast("Please input Password");
            return false;
        } else if (firstname.getText().equals("")) {
            showShortToast("Please input your first name");
            return false;
        } else if (lastname.getText().equals("")) {
            showShortToast("Please input your lastname");
            return false;
        } else if (regisEmail.getText().equals("")) {
            showShortToast("Please input your email");
            return false;
        } else {
            return true;
        }
    }

    private void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    * */
}