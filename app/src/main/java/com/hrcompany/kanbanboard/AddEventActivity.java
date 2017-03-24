package com.hrcompany.kanbanboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private EditText event_title_EditText;
    private EditText event_description_EditText;
    //private static TextView event_date_TextView;

    private SQLiteDatabase eventsDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        event_title_EditText = (EditText) findViewById(R.id.event_title_EditText);
        event_description_EditText = (EditText) findViewById(R.id.event_description_EditText);
        //event_date_TextView = (TextView) findViewById(R.id.event_date_TextView);
        eventsDB = MainBoard.getDatabase();
    }

    /*public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        private static int yearValue;
        private static int monthValue;
        private static int dayValue;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            AddEventActivity.event_date_TextView.setText("Event Date : " + year + "/" + month + "/" + day);

            yearValue = year;
            monthValue = month;
            dayValue = day;
        }

        public static Date getDate(){
            String finalDateTime = "";
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;



            return Date.valueOf(String.valueOf(yearValue)+"-"+String.valueOf(monthValue)+"-"+String.valueOf(dayValue));
        }
    }

    public void selectEventDate(View view) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "eventDatePicker");
    }*/

    public void addEvent(View view) {
        String title = event_title_EditText.getText().toString();
        String description = event_description_EditText.getText().toString();
        //Date eventDate = DatePickerFragment.getDate();

        eventsDB.execSQL("INSERT INTO events (title, description, state)" +
                "VALUES('"+title+"', '"+description+"', 1);");

        Intent intent = new Intent(this, MainBoard.class);
        startActivity(intent);

        finish();
    }

    public void cancelButtonFunction(View view) {
        finish();
    }
}
