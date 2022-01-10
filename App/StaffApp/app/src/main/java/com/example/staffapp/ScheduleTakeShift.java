package com.example.staffapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleTakeShift extends AppCompatActivity {
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_take_shift);
        api = MainActivity.api;
        final String[] employeeSwapping = {"", ""};

        LinearLayout linearLayoutTakeShiftDate = (LinearLayout) findViewById(R.id.linearLayoutTakeShiftDate);
        LinearLayout linearLayoutEmployeeNamesTake = (LinearLayout) findViewById(R.id.linearLayoutEmployeeNamesTake);
        Button buttonTakeShift = (Button) findViewById(R.id.buttonTakeShift);

        String time = getIntent().getStringExtra("time");
        String date = getIntent().getStringExtra("date");

        TextView TakeShiftDate = new TextView(linearLayoutTakeShiftDate.getContext());
        TakeShiftDate.setTextSize(19);
        TakeShiftDate.setText("Take the following shift. Date: " + date + " Time: " + time + "\nWhat is your name?");
        TakeShiftDate.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayoutTakeShiftDate.addView(TakeShiftDate);

        for(TableEmployee employee : api.PARSEDApi().getEmployeeList())
        {
            String employeeName = employee.fName + " " + employee.lName;
            TextView textViewEmployeeName = new TextView(linearLayoutEmployeeNamesTake.getContext());
            textViewEmployeeName.setText(employee.fName + " " + employee.lName);
            textViewEmployeeName.setId(Integer.parseInt(employee.employeeId));
            textViewEmployeeName.setTextSize(19);
            textViewEmployeeName.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayoutEmployeeNamesTake.addView(textViewEmployeeName);

            TextView finalTextViewEmployeeName = textViewEmployeeName;
            textViewEmployeeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    employeeSwapping[0] = Integer.toString(finalTextViewEmployeeName.getId());
                    employeeSwapping[1] = finalTextViewEmployeeName.getText().toString();
                }});
        }

        buttonTakeShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer employeeId = Integer.parseInt(employeeSwapping[0]);
                String employeeName = employeeSwapping[1];
                if(!employeeId.equals("") && !employeeName.equals("") && !time.equals("") && !date.equals(""))
                {
                    api.POSTApi().createTimeTablePost(0, employeeId, time, date);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    openScheduleActivity();
                }
            }});
    }
    private void openScheduleActivity()
    {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
}
