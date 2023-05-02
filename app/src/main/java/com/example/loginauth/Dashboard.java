package com.example.loginauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class Dashboard extends AppCompatActivity {

    Button btn;
    TextView tv;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        auth = FirebaseAuth.getInstance();
        btn = (Button) findViewById(R.id.outBtn);
        tv = (TextView) findViewById(R.id.textView);

        user = auth.getCurrentUser();
        if(user != null ){
            tv.setText("Hello "+user.getEmail());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                userSignout();
            }

            private void userSignout() {
                Intent intent = new Intent(Dashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}