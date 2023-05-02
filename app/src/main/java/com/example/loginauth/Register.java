package com.example.loginauth;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText userReg, passReg;
    Button regBtn;
    FirebaseAuth mAuth;
    ProgressBar pg;

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pg = (ProgressBar) findViewById(R.id.regProgress);
        mAuth = FirebaseAuth.getInstance();
        userReg = (EditText) findViewById(R.id.userReg);
        passReg = (EditText) findViewById(R.id.passReg);
        regBtn = (Button) findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setVisibility(View.VISIBLE);
                String email, pass;
                email = String.valueOf(userReg.getText());
                pass = String.valueOf(passReg.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    pg.setVisibility(View.GONE);

                                    Toast.makeText(Register.this, "Account created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    pg.setVisibility(View.GONE);
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


            }
        });

    }
}