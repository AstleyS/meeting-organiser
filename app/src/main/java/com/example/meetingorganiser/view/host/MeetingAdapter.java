package com.example.meetingorganiser.view.host;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Meeting;

import java.util.List;

public class MeetingAdapter extends ArrayAdapter<Meeting> {

    Context context;
    int resource;
    List<Meeting> meetings;

    public MeetingAdapter(@NonNull Context context, int resource, @NonNull List<Meeting> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.meetings = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =  convertView != null ? convertView : LayoutInflater.from(context).inflate(resource, parent, false);
        Meeting meeting = getItem(position);

        TextView title = (TextView) view.findViewById(R.id.item_title);
        TextView description = (TextView) view.findViewById(R.id.item_description);

        title.setText(meeting.title);
        description.setText(meeting.description);

        return view;
    }
}