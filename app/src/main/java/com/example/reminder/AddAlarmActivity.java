package com.example.reminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class AddAlarmActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button addAlarm,select_audio_alarm;
    EditText title,description;
    TextView select_date,select_time;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);
        String currentDateString= DateFormat.getDateInstance().format(c.getTime());
        select_date=(TextView)findViewById(R.id.select_date);
        select_date.setText(currentDateString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addalarm_screen);
        addAlarm=findViewById(R.id.addReminder_btn);
        title=findViewById(R.id.text_title);
        description=findViewById(R.id.text_description);
        select_date=findViewById(R.id.select_date);
        select_time=findViewById(R.id.select_time);
        select_audio_alarm=findViewById(R.id.select_audio_alarm);




        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmReminderModel alarmModel;
                try {
                    alarmModel=new AlarmReminderModel(1,title.getText().toString(),description.getText().toString(),"none","Alarm",3,select_date.getText().toString(),select_time.getText().toString());
                }catch (Exception e){
                    Toast.makeText(AddAlarmActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                    alarmModel=new AlarmReminderModel(-1,"none","error","none","none",0,"none","none");
                }
               DatabaseHelper databaseHelper=new DatabaseHelper(AddAlarmActivity.this);
               boolean success= databaseHelper.addOne(alarmModel);
               Toast.makeText(AddAlarmActivity.this,"Your alarm has been setup" , Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(AddAlarmActivity.this,ViewAlarmActivity.class);
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

                TimePickerDialog timePickerDialog=new TimePickerDialog(AddAlarmActivity.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

//        select_audio_alarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(AddAlarmActivity.this,FilePickerActivity.class);
//                intent.putExtra(FilePickerActivity.CONFIGS,new Configurations.Builder().setCheckPermission(true).setShowAudios(true).setShowImages(false).setShowVideos(false).setMaxSelection(1).setSkipZeroSizeFiles(true).build());
//                startActivityForResult(intent,103);
//            }
//        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data !=null ){
            ArrayList<MediaFile> mediaFiles=data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            String path=mediaFiles.get(0).getPath();
            if(requestCode == 103){
                filePathToToast("Audio Path :" + path);
                return;
            }
    }

}

    private void filePathToToast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

}