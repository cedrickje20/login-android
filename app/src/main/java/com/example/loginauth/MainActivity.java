package com.example.loginauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    EditText userLog, passLog;
    Button logBtn;
    FirebaseAuth mAuth;
    ProgressBar pg;

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.signup);
        pg = (ProgressBar) findViewById(R.id.loginProgress);
        mAuth = FirebaseAuth.getInstance();
        userLog = (EditText) findViewById(R.id.userLogin);
        passLog = (EditText) findViewById(R.id.passLogin);
        logBtn = (Button) findViewById(R.id.Loginbtn);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setVisibility(View.VISIBLE);
                String email, pass;
                email = String.valueOf(userLog.getText());
                pass = String.valueOf(passLog.getText());

                if (TextUtils.isEmpty(email)){
                    pg.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    pg.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    pg.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Login Successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    pg.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Login Failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }

}