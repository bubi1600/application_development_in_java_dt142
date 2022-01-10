package com.example.staffapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleGetSpecific extends AppCompatActivity {

    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_get_specific);
        api = MainActivity.api;
        final String[] employeeSwapping = {"", ""};

        LinearLayout linearLayoutEmployees = (LinearLayout) findViewById(R.id.LinearLayoutEmployees);
        LinearLayout linearLayoutSchedule = (LinearLayout) findViewById(R.id.LinearLayoutSchedule);



        for(TableEmployee employee : api.PARSEDApi().getEmployeeList())
        {
            String employeeName = employee.fName + " " + employee.lName;
            TextView textViewEmployeeName = new TextView(linearLayoutEmployees.getContext());
            textViewEmployeeName.setText(employee.fName + " " + employee.lName);
            textViewEmployeeName.setId(Integer.parseInt(employee.employeeId));
            textViewEmployeeName.setTextSize(19);
            textViewEmployeeName.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayoutEmployees.addView(textViewEmployeeName);

            TextView finalTextViewEmployeeName = textViewEmployeeName;
            textViewEmployeeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //employeeSwapping[0] = Integer.toString(finalTextViewEmployeeName.getId());
                    //employeeSwapping[1] = finalTextViewEmployeeName.getText().toString();

                    linearLayoutSchedule.removeAllViews();
                    String employeeId = Integer.toString(finalTextViewEmployeeName.getId());
                    String employeeName = finalTextViewEmployeeName.getText().toString();
                    TextView textViewemployeeSchedule = new TextView(linearLayoutSchedule.getContext());
                    textViewemployeeSchedule.setText("");
                    for(TableTimeTable timeTable : api.PARSEDApi().getTimeTableList())
                    {
                        if(employeeId.equals(timeTable.employeeId))
                        {
                            textViewemployeeSchedule.append("Date: " + timeTable.date + " Time: " + timeTable.working + "\n\n");
                        }
                    }
                    if(textViewemployeeSchedule.getText().equals(""))
                        textViewemployeeSchedule.setText("No shifts were found");
                    textViewemployeeSchedule.setTextSize(19);
                    textViewemployeeSchedule.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayoutSchedule.addView(textViewemployeeSchedule);
                }});
        }
    }
}
