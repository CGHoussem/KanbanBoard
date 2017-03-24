package com.hrcompany.kanbanboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Houssem on 23/03/2017.
 */

public class MyAdapter extends ArrayAdapter<Event> {


    public MyAdapter(Context context, Event[] events) {
        super(context, R.layout.etiquette_list, events);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.etiquette_list, parent, false);

        Event event = getItem(position);

        TextView titleTextView = (TextView) theView.findViewById(R.id.sticky_note_TextView);
        titleTextView.setText(event.getTitle());

        ImageView etiquetteImageView = (ImageView) theView.findViewById(R.id.sticky_note_ImageView);
        etiquetteImageView.setImageResource(R.drawable.sticky_note_yellow);

        return theView;
    }
}
