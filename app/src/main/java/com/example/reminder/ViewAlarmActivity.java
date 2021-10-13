package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ViewAlarmActivity extends AppCompatActivity {

    private Button addNew; //the addnew for alarm button
    private ImageButton viewAlarms; //view alarm navigation
    private ImageButton viewReminders;//view reminders navigation
    private ListView alarmListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alarm);

        addNew=findViewById(R.id.addNewBtn);
        viewAlarms=findViewById(R.id.view_alarm_btn);
        viewReminders=findViewById(R.id.view_reminders_btn);
        alarmListView=findViewById(R.id.listView_alarm);

        DatabaseHelper databaseHelper=new DatabaseHelper(ViewAlarmActivity.this);
        List<AlarmReminderModel> alarmList=databaseHelper.getEveryAlarms();
        AlarmListAdapter adapter=new AlarmListAdapter(this,R.layout.adapter_view_layout,alarmList);
        alarmListView.setAdapter(adapter);

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

        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                AlarmReminderModel alarmModel= (AlarmReminderModel) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(alarmModel);
                DatabaseHelper databaseHelper=new DatabaseHelper(ViewAlarmActivity.this);
                List<AlarmReminderModel> alarmList=databaseHelper.getEveryAlarms();
                AlarmListAdapter adapter=new AlarmListAdapter(ViewAlarmActivity.this,R.layout.adapter_view_layout,alarmList);
                alarmListView.setAdapter(adapter);
                Toast.makeText(ViewAlarmActivity.this,alarmModel.getTitle().toString() + "has been deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }
}