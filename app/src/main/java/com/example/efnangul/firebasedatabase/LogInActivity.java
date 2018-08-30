package com.example.efnangul.firebasedatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "LogInActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String LogInEmail, LogInPassword;
    EditText et_LogInEmail, et_LogInPassword;
    TextView text_toRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        et_LogInEmail = (EditText) findViewById(R.id.et_LogInEmail);
        et_LogInPassword = (EditText) findViewById(R.id.et_LogInPassword);
        text_toRegister = (TextView) findViewById(R.id.text_toRegister);

        Button btn_logIn = (Button) findViewById(R.id.btn_logIn);
        Button btnNewUser = (Button) findViewById(R.id.btn_newUser);

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInEmail = et_LogInEmail.getText().toString();
                LogInPassword = et_LogInPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(LogInEmail, LogInPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                try {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "LogIn Succesfully", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Log.e(TAG, "You have an error!"); //Log.e error msg
                                }
                            }
                        });
//                Intent intentPersonProfile = new Intent(LogInActivity.this, PersonProfile.class);
//                startActivity(intentPersonProfile);
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Go Add user page");
                Intent intentNewUser = new Intent(LogInActivity.this, AddNewUserActivity.class);
                startActivity(intentNewUser);
            }
        });
    }
}


