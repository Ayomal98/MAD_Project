package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startBtn=findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(SplashActivity.this);
                List<AlarmReminderModel> alarmList=databaseHelper.getEveryAlarms();
                ArrayAdapter alarmArrayAdapter=new ArrayAdapter<AlarmReminderModel>(SplashActivity.this, android.R.layout.simple_list_item_1,alarmList);

                Intent intent=new Intent(SplashActivity.this, ViewAlarmActivity.class);
                startActivity(intent);
            }
        });
    }
}