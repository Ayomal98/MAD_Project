package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ViewAlarmActivity extends AppCompatActivity {

    private Button addNew; //the addnew for alarm button
    private ImageButton viewAlarms; //view alarm navigation
    private ImageButton viewReminders;//view reminders navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alarm);

        addNew=findViewById(R.id.addNewBtn);
        viewAlarms=findViewById(R.id.view_alarm_btn);
        viewReminders=findViewById(R.id.view_reminders_btn);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewAlarmActivity.this,AddAlarmActivity.class);
                startActivity(intent);
            }
        });


        viewReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewAlarmActivity.this,ViewReminderActivity.class);
                startActivity(intent);
            }
        });

    }
}