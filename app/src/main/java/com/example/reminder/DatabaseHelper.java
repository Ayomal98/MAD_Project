package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME="Reminder_App";
    static final int DATABASE_VERSION=1;

    //Alarm_reminder table
    static final String REMINDER_TABLE="ALARM_REMINDER";
    static final String ID_Column="ID";
    static final String Title_Column="Title";
    static final String Description_Column="Description";
    static final String Location_Column="Location";
    static final String Type_Column="Type";
    static final String Date_Column="Date";
    static final String Time_Column="Time";
    static final String No_Snoozes_Column="No_Snoozes";
//    static final String URI_Column="URI";

    //snoozes table
    static final String SNOOZE_TABLE="SNOOZE_NOTIFICATIONS";
    static final String ID_Notifications_Column="ID";
    static final String five_mins_Column="five_miutes";
    static final String fifteen_mins_Column="fifteen_miutes";
    static final String thirty_mins_Column="thirty_miutes";
    static final String one_hour_Column="one_hours";
    static final String two_hour_Column="two_hours";
    static final String one_days_Column="one_days";
    static final String two_days_Column="two_days";
    static final String Reminder_ID_Column="Reminder_ID";



    //create query for alarm reminder table
    static final String CREATE_QUERY_REMINDER="CREATE TABLE " + REMINDER_TABLE +"(" +ID_Column +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+ Title_Column +" varchar(50) NOT NULL, "
         + Description_Column +" text NOT NULL, "+ Location_Column+" varchar(50)  NOT NULL, " + Date_Column +" varchar(50) NOT NULL , "+ Time_Column+" varchar(50) NOT NULL, " + No_Snoozes_Column +" INTEGER NOT NULL, "+
             Type_Column +" varchar(50) NOT NULL) ";

    //create query for notifications table
    static final String CREATE_QUERY_Notifications="CREATE TABLE "+ SNOOZE_TABLE + "(" + ID_Notifications_Column +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+five_mins_Column+" INTEGER NOT NULL, "+thirty_mins_Column+" INTEGER NOT NULL, "+one_hour_Column+" INTEGER NOT NULL,"+two_hour_Column+" INTEGER NOT NULL,"+one_days_Column+" INTEGER NOT NULL, "+two_days_Column+" INTEGER NOT NULL, "+Reminder_ID_Column+" INTEGER NOT NULL )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY_REMINDER);
        db.execSQL(CREATE_QUERY_Notifications);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ALARM_REMINDER" );
    }

    public boolean addOne(AlarmReminderModel alarmReminderModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Title_Column,alarmReminderModel.getTitle());
        cv.put(Description_Column,alarmReminderModel.getDescription());
        cv.put(Location_Column,alarmReminderModel.getLocation());
        cv.put(Type_Column,alarmReminderModel.getType());
        cv.put(No_Snoozes_Column,alarmReminderModel.getNoNotifications());
        cv.put(Date_Column,alarmReminderModel.getDate());
        cv.put(Time_Column,alarmReminderModel.getTime());
        long insert=db.insert(REMINDER_TABLE,null,cv);
        if(insert == -1 ){
            return false;
        }else{
            return true;
        }
    }



    public List<AlarmReminderModel> getEveryAlarms(){
        List<AlarmReminderModel> returnAlarms=new ArrayList<>();
        String type="Alarm";
        String queryAlarm="SELECT * FROM ALARM_REMINDER WHERE Type='Alarm'" ;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryAlarm,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String Title=cursor.getString(1);
                String Date=cursor.getString(4);
                String Time=cursor.getString(5);
                AlarmReminderModel alarmReminderModel=new AlarmReminderModel(id,Title,Date,Time);
                returnAlarms.add(alarmReminderModel);
            }while(cursor.moveToNext());
        }else{
        }
        return returnAlarms;
    }

    public List<AlarmReminderModel> getEveryReminders(){
        List<AlarmReminderModel> returnAlarms=new ArrayList<>();
        String type="Alarm";
        String queryAlarm="SELECT * FROM ALARM_REMINDER WHERE Type='Reminder'" ;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryAlarm,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String Title=cursor.getString(1);
                String Date=cursor.getString(4);
                String Time=cursor.getString(5);
                AlarmReminderModel alarmReminderModel=new AlarmReminderModel(id,Title,Date,Time);
                returnAlarms.add(alarmReminderModel);
            }while(cursor.moveToNext());
        }else{
        }
        return returnAlarms;
    }

    public boolean deleteOne(AlarmReminderModel alarmReminderModel){
        SQLiteDatabase db=this.getWritableDatabase();
        String queryDelete="DELETE FROM ALARM_REMINDER WHERE "+ ID_Column+" = " +alarmReminderModel.getId();
        Cursor cursor = db.rawQuery(queryDelete, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
