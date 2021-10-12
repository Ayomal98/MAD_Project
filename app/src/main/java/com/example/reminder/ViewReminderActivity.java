package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewReminderActivity extends AppCompatActivity {

    private ImageButton viewAlarmBtn;
    private Button addReminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
        viewAlarmBtn=findViewById(R.id.view_alarm_btn);
        addReminderBtn=findViewById(R.id.addNewBtn);

        viewAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(ViewReminderActivity.this);
                List<AlarmReminderModel> alarmList=databaseHelper.getEveryAlarms();
                Toast.makeText(ViewReminderActivity.this,alarmList.toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ViewReminderActivity.this,ViewAlarmActivity.class);
                startActivity(intent);
            }
        });

        addReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewReminderActivity.this,AddReminderActivity.class);
                startActivity(intent);

            }
        });
    }


}