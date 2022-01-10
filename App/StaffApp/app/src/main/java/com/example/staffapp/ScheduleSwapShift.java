package com.example.staffapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleSwapShift extends AppCompatActivity {
    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_swap_shift);
        api = MainActivity.api;
        Integer timeTableId = getIntent().getIntExtra("timeTableId", 0);
        LinearLayout linearLayoutEmployeeNames = (LinearLayout) findViewById(R.id.linearLayoutEmployeeNames);
        LinearLayout linearLayoutSwapWithEmployee = (LinearLayout) findViewById(R.id.linearLayoutSwapEmployeeName);
        Button buttonSwapShift = (Button) findViewById(R.id.buttonSwapShift);
        TextView textViewSwapWithEmployee = new TextView(linearLayoutEmployeeNames.getContext());

        String swapWithEmployeeName = "";
        String swapWithEmployeeId = "";
        String swapWithEmployeeWorking = "";
        String swapWithEmployeeDate = "";
        final String[] employeeSwapping = {"", ""};

        for(TableTimeTable timeTable : api.PARSEDApi().getTimeTableList())
        {
            for(TableEmployee employee : api.PARSEDApi().getEmployeeList())
            {
                if(timeTable.employeeId.equals(employee.employeeId) && timeTable.timeTableId.equals(Integer.toString(timeTableId)))
                {
                    swapWithEmployeeName = employee.fName + " " + employee.lName;
                    swapWithEmployeeId = employee.employeeId;
                    swapWithEmployeeWorking = timeTable.working;
                    swapWithEmployeeDate = timeTable.date;
                    break;
                }
            }
        }

        textViewSwapWithEmployee.setText("Swap with " + swapWithEmployeeName + "\nWhat is your name?");
        textViewSwapWithEmployee.setGravity(Gravity.CENTER_HORIZONTAL);
        textViewSwapWithEmployee.setTextSize(19);
        linearLayoutSwapWithEmployee.addView(textViewSwapWithEmployee);

        for(TableEmployee employee : api.PARSEDApi().getEmployeeList())
        {
            String employeeName = employee.fName + " " + employee.lName;
            if(!employeeName.equals(swapWithEmployeeName))
            {
                TextView textViewEmployeeName = new TextView(linearLayoutEmployeeNames.getContext());
                textViewEmployeeName.setText(employee.fName + " " + employee.lName);
                textViewEmployeeName.setId(Integer.parseInt(employee.employeeId));
                textViewEmployeeName.setTextSize(19);
                textViewEmployeeName.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayoutEmployeeNames.addView(textViewEmployeeName);

                TextView finalTextViewEmployeeName = textViewEmployeeName;
                textViewEmployeeName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        employeeSwapping[0] = Integer.toString(finalTextViewEmployeeName.getId());
                        employeeSwapping[1] = finalTextViewEmployeeName.getText().toString();
                    }});
            }
        }

        String finalSwapWithEmployeeName = swapWithEmployeeName;
        String finalSwapWithEmployeeId = swapWithEmployeeId;
        String finalSwapWithEmployeeWorking = swapWithEmployeeWorking;
        String finalSwapWithEmployeeDate = swapWithEmployeeDate;
        buttonSwapShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!employeeSwapping[0].equals("") &&
                        !employeeSwapping[1].equals("") &&
                        !finalSwapWithEmployeeName.equals("") &&
                        !finalSwapWithEmployeeId.equals(""))
                {
                    Integer employeeId = Integer.parseInt(employeeSwapping[0]);
                    api.POSTApi().modifyTimeTablePostColumn(timeTableId, employeeId, finalSwapWithEmployeeWorking, finalSwapWithEmployeeDate);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    openScheduleActivity();
                }
                else
                    System.out.println("All not set");
            }});
    }

    private void openScheduleActivity()
    {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
}
