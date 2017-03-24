package com.hrcompany.kanbanboard;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.ViewFlipper;

import java.io.File;
import java.sql.Date;

public class MainBoard extends FragmentActivity {

    private ListView listViewToDo;
    private TabHost tabHost;

    private Event[] events;
    private SQLiteDatabase eventsDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);

        createDatabase();

        listViewToDo = (ListView) findViewById(R.id.listViewToDo);


        if (Configuration.ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
            tabHost = (TabHost) findViewById(R.id.tab_host);
            tabHost.setup();

            // tab 1
            TabHost.TabSpec spec = tabHost.newTabSpec("TO DO");
            spec.setContent(R.id.tab1);
            spec.setIndicator("TO DO");
            tabHost.addTab(spec);

            // tab 2
            spec = tabHost.newTabSpec("DOING");
            spec.setContent(R.id.tab2);
            spec.setIndicator("DOING");
            tabHost.addTab(spec);

            // tab 3
            spec = tabHost.newTabSpec("DONE");
            spec.setContent(R.id.tab3);
            spec.setIndicator("DONE");
            tabHost.addTab(spec);
        }
        events = getEvents(1);

        ListAdapter theAdapter = new MyAdapter(this, events);
        listViewToDo.setAdapter(theAdapter);
    }

    public void createDatabase(){
        try {
            eventsDB = this.openOrCreateDatabase("MyEvents", MODE_PRIVATE, null);

            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY, title VARCHAR, description TEXT, eventDate DATE, state INTEGER DEFAULT 1);");

            File database = getApplicationContext().getDatabasePath("MyEvents.db");
        }
        catch (Exception e){
            Log.e("DATABASE ERROR", "Error Creating Database");
        }
    }

    public void addEvent(Event event){
        eventsDB.execSQL("INSERT INTO events (title, description, eventDate) VALUES ('"+event.getTitle()+"', '"+event.getDescription()+"', '"+event.getEventDate()+"')");
    }

    public Event[] getEvents(int state){
        int longeur = 0;
        Cursor cursor = eventsDB.rawQuery("SELECT * FROM events WHERE state="+state, null);
        Event[] events = new Event[cursor.getCount()];

        int idColumn = cursor.getColumnIndex("id");
        int titleColumn = cursor.getColumnIndex("title");
        int descriptionColumn = cursor.getColumnIndex("description");
        int dateColumn = cursor.getColumnIndex("eventDate");

        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            do {

                String id = cursor.getString(idColumn);
                String title = cursor.getString(titleColumn);
                String description = cursor.getString(descriptionColumn);
                String date = cursor.getString(dateColumn);

                events[longeur++] = new Event(title, description, Date.valueOf(date), state);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return events;
    }

    public void addToDoEvent(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);

        startActivity(intent);
    }

}
