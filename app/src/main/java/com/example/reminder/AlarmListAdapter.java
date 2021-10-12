package com.example.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<AlarmReminderModel> {
    private Context context;
    int mresource;
    private TextView titleView,dateView,timeView;

    public AlarmListAdapter(Context context, int resource, List<AlarmReminderModel> alarmList) {
        super(context,resource,alarmList);
        this.context=context;
        mresource=resource;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        int id=getItem(position).getId();
        String title=getItem(position).getTitle();
        String date=getItem(position).getDate();
        String time=getItem(position).getTime();
        AlarmReminderModel alarmModel=new AlarmReminderModel(id,title,date,time);
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(mresource,parent,false);
        titleView=(TextView) convertView.findViewById(R.id.text_Title);
        dateView=(TextView) convertView.findViewById(R.id.text_Date);
        timeView=(TextView) convertView.findViewById(R.id.text_Time);
        titleView.setText(title);
        dateView.setText(date);
        timeView.setText(time);
        return convertView;
    }

}
