package com.example.efnangul.firebasedatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddNewUserActivity extends AppCompatActivity {
    private final static String TAG = "AddNewUserActivity";
    String newUserEmail, newUserPassword;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText et_newUserEmail, et_newUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        mAuth = FirebaseAuth.getInstance();

        et_newUserEmail = (EditText) findViewById(R.id.et_newUserEmail);
        et_newUserPassword = (EditText) findViewById(R.id.et_newUserPassword);

        Button btn_createNewUser = (Button) findViewById(R.id.btn_createNewUser);
        btn_createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUserEmail = et_newUserEmail.getText().toString();
                newUserPassword = et_newUserPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(newUserEmail, newUserPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i(TAG, "Task success");
                                    Intent intentPersonProfile = new Intent(AddNewUserActivity.this, PersonProfile.class);
                                    startActivity(intentPersonProfile);
                                } else {
                                    Log.e(TAG, "You have an error");
                                    Toast.makeText(getApplicationContext(), "New User Created Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }
}