package com.example.gabri.afinal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Event extends AppCompatActivity {

    Button btn_date, btn_time;
    int year_x,month_x,day_x,hour_x,minute_x;
    Calendar mCurrentDate, mCurrentTime;
    String format, FinalTime, FinalDate = "";
    Spinner spinner;
    EditText description;

    Control control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        control = new Control(this);

        //Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adp;
        adp = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        //Current Time
        mCurrentTime = Calendar.getInstance();
        hour_x = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute_x = mCurrentTime.get(Calendar.MINUTE);

        description = (EditText) findViewById(R.id.editText6);

        //Open dialog
        btn_date = findViewById(R.id.button9);
        btn_time = findViewById(R.id.button10);

        mCurrentDate = Calendar.getInstance();

        //Current Date
        day_x = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month_x = mCurrentDate.get(Calendar.MONTH);
        year_x = mCurrentDate.get(Calendar.YEAR);
        month_x = month_x + 1;

        selectedTimeFormat(hour_x);

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Event.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay);
                        Toast.makeText(Event.this, hourOfDay +":"+minute + " "+ format , Toast.LENGTH_SHORT).show();
                        FinalTime = hourOfDay + ":" + minute +" "+ format;

                    }
                },hour_x,minute_x,true);
                timePickerDialog.show();
            }
        });



    }

    public void selectedTimeFormat(int hour_x){
        if(hour_x == 0){
            hour_x += 12;
            format = "AM";
        }else if(hour_x == 12){
            format = "PM";
        }else if(hour_x > 12){
            hour_x -= 12;
            format = "PM";
        }else{
            format = "AM";
        }
    } //check if is AM or PM

    public void showDialogDate(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(Event.this, dayOfMonth +"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
                FinalDate = dayOfMonth +"/"+month+"/"+year;
            }
        }, year_x,month_x,day_x);
        datePickerDialog.show();
    } //Open dialogDate

    public void inscribe(View view) {
            if(FinalDate.equals("") || FinalDate.equals("") || description.getText().toString().equals("")){
                Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
            }else{
                long x = control.insertEvent(spinner.getSelectedItem().toString(), FinalDate, FinalTime, description.getText().toString());
                Toast.makeText(this, String.valueOf(x), Toast.LENGTH_SHORT).show();
            }
    }
      //Add new event
    public void openListEvent(View view){
        Intent ListEvent = new Intent(this, Search.class);
        startActivity(ListEvent);
    } //open intent list
}
