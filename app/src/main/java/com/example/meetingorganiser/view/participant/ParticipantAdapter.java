package com.example.meetingorganiser.view.participant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meetingorganiser.R;
import com.example.meetingorganiser.data.entities.Participant;

import java.util.List;

public class ParticipantAdapter extends ArrayAdapter<Participant> {

    Context context;
    int resource;
    List<Participant> participants;

    public ParticipantAdapter(@NonNull Context context, int resource, @NonNull List<Participant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.participants = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =  convertView != null ? convertView : LayoutInflater.from(context).inflate(resource, parent, false);
        Participant participant = getItem(position);

        TextView name = (TextView) view.findViewById(R.id.item_name);
        TextView phone = (TextView) view.findViewById(R.id.item_phone);

        name.setText(participant.firstName + " " + participant.lastName);
        phone.setText(participant.phoneNumber);

        return view;
    }
}