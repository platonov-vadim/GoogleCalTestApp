package com.example.googlecaltestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = df.format(c.getTime());
        final EditText startDateEdit = (EditText) findViewById(R.id.editText2);
        startDateEdit.setText(formattedDate);
        String formattedTime = tf.format(c.getTime());
        final EditText startTimeEdit = (EditText) findViewById(R.id.editText3);
        startTimeEdit.setText(formattedTime);
        Button b = (Button) findViewById(R.id.testButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) findViewById(R.id.editText);
                String eventName = text.getText().toString();
                if(!eventName.trim().isEmpty()) {
                    Calendar cal = Calendar.getInstance();
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    try {
                        intent.putExtra("title", eventName);
                        Date startDate = dtf.parse(startDateEdit.getText().toString() + " " + startTimeEdit.getText().toString());
                        intent.putExtra("beginTime", startDate.getTime());
                        CheckBox chb = (CheckBox) findViewById(R.id.checkBox);
                        intent.putExtra("allDay", chb.isChecked());
                        //intent.putExtra("rrule", "FREQ=YEARLY");
                        EditText endDateEdit = (EditText) findViewById(R.id.editText4);
                        String endDateString = endDateEdit.getText().toString();
                        EditText endTimeEdit = (EditText) findViewById(R.id.editText5);
                        String endTimeString = endTimeEdit.getText().toString();
                        if(!endDateString.trim().isEmpty() && !endTimeString.trim().isEmpty()) {
                            Date endDate = dtf.parse(endDateString + " " + endTimeString);
                            intent.putExtra("endTime", endDate.getTime());
                        }
                        else if(!chb.isChecked())
                            throw new Exception("End Date is not set!");
                        startActivity(intent);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
