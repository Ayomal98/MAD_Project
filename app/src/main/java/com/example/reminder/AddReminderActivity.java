package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AddReminderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button addReminderBtn;
    EditText title,description,location;
    TextView select_date,select_time;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        select_date = (TextView) findViewById(R.id.select_date);
        select_date.setText(currentDateString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        addReminderBtn=findViewById(R.id.addReminder_btn);
        title=findViewById(R.id.text_title);
        description=findViewById(R.id.text_description);
        select_date=findViewById(R.id.select_date);
        select_time=findViewById(R.id.select_time);
        location=findViewById(R.id.text_location);


        addReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmReminderModel alarmModel;
                try {
                    alarmModel=new AlarmReminderModel(1,title.getText().toString(),description.getText().toString(),location.getText().toString(),"Reminder",3,select_date.getText().toString(),select_time.getText().toString());
                }catch (Exception e){
                    Toast.makeText(AddReminderActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                    alarmModel=new AlarmReminderModel(-1,"none","error","none","none",0,"none","none");
                }
                DatabaseHelper databaseHelper=new DatabaseHelper(AddReminderActivity.this);
                boolean success= databaseHelper.addOne(alarmModel);
                Toast.makeText(AddReminderActivity.this,"Your Reminder has been setup" , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddReminderActivity.this,ViewReminderActivity.class);
                startActivity(intent);
            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker=new DateFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int hours=calendar.get(Calendar.HOUR_OF_DAY);
                int minutes=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(AddReminderActivity.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursofDay, int curMinutes) {
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hoursofDay);
                        c.set(Calendar.MINUTE,curMinutes);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat fomrat=new SimpleDateFormat("k:mm a");
                        String timeFormet=fomrat.format(c.getTime());
                        select_time.setText(timeFormet);
                    }
                },hours,minutes,false);
                timePickerDialog.show();
            }
        });


    }



}