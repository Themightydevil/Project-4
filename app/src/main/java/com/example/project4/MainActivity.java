package com.example.project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Created 4 TextViews with onClickListeners to redirect to their specific activity.

        TextView edm = findViewById(R.id.edm);
        TextView electronic = findViewById(R.id.electronic);
        TextView trap = findViewById(R.id.trap);
        TextView dubstep = findViewById(R.id.dubstep);

        edm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Edm.class);
                startActivity(intent);
            }
        });

        electronic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Electronic.class);
                startActivity(intent);
            }
        });
        trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Trap.class);
                startActivity(intent);
            }
        });
        dubstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dubstep.class);
                startActivity(intent);
            }
        });
    }
    }