package com.example.chamal.erpapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    private static TextView tvStart, tvEnd;
    Spinner spinner;
    EditText etUser,etRemarks;
    Button saveButton;
    private static int flag = 0;

    private static String startTime,endTime, reason, user, remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStart = (TextView) findViewById(R.id.textViewStart);
        tvStart.setText("Please set start time");
        tvEnd = (TextView) findViewById(R.id.textViewEnd);
        tvEnd.setText("Please set end time");

        etUser = (EditText)findViewById(R.id.etUse);
        etRemarks = (EditText)findViewById(R.id.etRem);
        saveButton = (Button)findViewById(R.id.bSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = etUser.getText().toString();
                remarks = etRemarks.getText().toString();
            }
        });

        manageSpinner();

    }

    private void manageSpinner() {
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reason = String.valueOf(spinner.getSelectedItem());
                Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showTimePickerDialog(View v) {
        switch (v.getId()) {
            case R.id.buttonStart:
                flag = 1;
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                break;
            case R.id.buttonEnd:
                flag = 2;
                DialogFragment newFragment2 = new TimePickerFragment();
                newFragment2.show(getFragmentManager(), "timePicker2");
                break;
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(flag==1) {
                tvStart.setText("" + hourOfDay + ":" + minute + "h");
                startTime = (hourOfDay+":"+minute);
                Toast.makeText(getActivity(), ""+startTime,Toast.LENGTH_SHORT).show();
            }else{
                tvEnd.setText("" + hourOfDay + ":" + minute + "h");
                endTime = (hourOfDay+":"+minute);
                Toast.makeText(getActivity(), ""+endTime,Toast.LENGTH_SHORT).show();
            }

        }


    }
}













