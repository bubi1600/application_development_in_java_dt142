package com.example.staffapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Lägg till knapp för varje tableRow0Date, denna används så att employees kan ta passet*/

public class Schedule extends AppCompatActivity {
    private List<Day> days;
    private Api api;
    private TableLayout tableLayout;
    private Button btnSpecificSchedule;
    private final int GET_NUMBER_OF_DAYS = 28;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        api = MainActivity.api;
        tableLayout  = (TableLayout) findViewById(R.id.tableLayout);
        btnSpecificSchedule = (Button) findViewById(R.id.buttonSpecificSchedule);
        processSchedule();

        TableRow tableRow0Date = new TableRow(tableLayout.getContext());
        TableRow tableRow1DayShiftBtn = new TableRow(tableLayout.getContext());
        TableRow tableRow2DayShiftStaff = new TableRow(tableLayout.getContext());
        TableRow tableRow3NightShiftBtn = new TableRow(tableLayout.getContext());
        TableRow tableRow4NightShiftStaff = new TableRow(tableLayout.getContext());

        for(int i=0; i<days.size(); i++)
        {
            if(i%7==0) //En större rad per vecka
            {
                tableRow0Date = new TableRow(tableLayout.getContext());
                tableRow1DayShiftBtn = new TableRow(tableLayout.getContext());
                tableRow2DayShiftStaff = new TableRow(tableLayout.getContext());
                tableRow3NightShiftBtn = new TableRow(tableLayout.getContext());
                tableRow4NightShiftStaff = new TableRow(tableLayout.getContext());
            }

            //Get iteration date
            TextView textViewDayAndDate = new TextView(tableLayout.getContext());
            String date = days.get(i).yearDate + "-" + days.get(i).monthDate + "-" + days.get(i).dayDate;
            String day = days.get(i).day;
            textViewDayAndDate.setText(day + " - " + date);
            textViewDayAndDate.setTextSize(17);

            //Set day shift button
            Button btnDayShift = new Button(tableLayout.getContext());
            String dayShiftTime = "Time: 10-14";
            btnDayShift.setText(dayShiftTime);

            btnDayShift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String time = "10-14";
                    openScheduleTakeShiftActivity(time, date);
                }});

            //Set night shift button
            Button btnNightShift = new Button(tableLayout.getContext());
            String nightShiftTime = "Time: 16-23";
            btnNightShift.setText(nightShiftTime);

            btnNightShift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String time = "16-23";
                    openScheduleTakeShiftActivity(time, date);
                }});

            //Check if we have daytime employees working the current day, if so add new row with employee name and add unique listener for each workers
            TextView textViewDayShift = null;
            if(days.get(i).staffDayShift.size()>0)
            {
                TableLayout tableDayShiftStaff = new TableLayout(tableLayout.getContext());
                for(Employee employee : days.get(i).staffDayShift)
                {
                    TableRow row = new TableRow(tableLayout.getContext());
                    textViewDayShift = new TextView(tableLayout.getContext());
                    textViewDayShift.setId(Integer.parseInt(employee.timeTableId)); //Vi sätter timeTableId från databasen som id, så vi kan lättare hitta denna i databasen och ändra
                    textViewDayShift.setText(employee.employeeName);
                    row.addView(textViewDayShift);
                    tableDayShiftStaff.addView(row);

                    //Add clickListener, so employees can change shifts
                    TextView finalTextViewDayShift = textViewDayShift;
                    textViewDayShift.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //System.out.println(finalTextViewDayShift.getId());
                            int timeTableId = finalTextViewDayShift.getId();
                            openScheduleSwapShiftActivity(timeTableId);
                        }});
                }
                tableRow2DayShiftStaff.addView(tableDayShiftStaff);
            }
            else
            {
                textViewDayShift = new TextView(tableLayout.getContext());
                tableRow2DayShiftStaff.addView(textViewDayShift);
            }

            //Check if we have nighttime employees working the current day, if so add new row with employee name and add unique listener for each workers
            TextView textViewNightShift = null;
            if(days.get(i).staffNightShift.size()>0)
            {
                TableLayout tableNightShiftStaff = new TableLayout(tableLayout.getContext());
                for(Employee employee : days.get(i).staffNightShift)
                {
                    TableRow row = new TableRow(tableLayout.getContext());
                    textViewNightShift = new TextView(tableLayout.getContext());
                    textViewNightShift.setId(Integer.parseInt(employee.timeTableId)); //Vi sätter timeTableId från databasen som id, så vi kan lättare hitta denna i databasen och ändra
                    textViewNightShift.setText(employee.employeeName);
                    row.addView(textViewNightShift);
                    tableNightShiftStaff.addView(row);
                    TextView finalTextViewNightShift = textViewNightShift;

                    //Add clickListener, so employees can change shifts
                    textViewNightShift.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //System.out.println(finalTextViewNightShift.getId());
                            int timeTableId = finalTextViewNightShift.getId();
                            openScheduleSwapShiftActivity(timeTableId);
                        }});
                }
                tableRow4NightShiftStaff.addView(tableNightShiftStaff);
            }else
            {
                textViewNightShift = new TextView(tableLayout.getContext());
                tableRow4NightShiftStaff.addView(textViewNightShift);
            }

            tableRow0Date.addView(textViewDayAndDate);
            tableRow0Date.setId(i);
            tableRow1DayShiftBtn.addView(btnDayShift);
            tableRow3NightShiftBtn.addView(btnNightShift);

            if(i%7==0) //En större rad per vecka
            {
                tableLayout.addView(tableRow0Date, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.addView(tableRow1DayShiftBtn, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.addView(tableRow2DayShiftStaff, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.addView(tableRow3NightShiftBtn, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.addView(tableRow4NightShiftStaff, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)); }
        }

        btnSpecificSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScheduleGetSpecificActivity();
            }});
    }

    void processSchedule()
    {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatDay = new SimpleDateFormat("E"); //t.ex. Tisdag
        SimpleDateFormat formatDayDate = new SimpleDateFormat("dd");
        SimpleDateFormat formatMonthDate = new SimpleDateFormat("MM");
        SimpleDateFormat formatYearDate = new SimpleDateFormat("yyyy");

        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        days = new ArrayList<Day>();
        for (int i = 0; i < GET_NUMBER_OF_DAYS; i++) //Vi hämtar 28 dagar, om vi vill ha 1 vecka bara skriver vi 7 istället för 28 i GET_NUMBER_OF_DAYS
        {
            String day = formatDay.format(now.getTime());
            String dayDate = formatDayDate.format(now.getTime());
            String monthDate = formatMonthDate.format(now.getTime());
            String yearDate = formatYearDate.format(now.getTime());
            days.add(new Day(day, dayDate, monthDate, yearDate)); //Skapa ny Day objekt för varje dag vi vill läsa och ha info på
            for(TableTimeTable timetable : api.PARSEDApi().getTimeTableList())
            {
                String timeTableDate = timetable.date.replace("-","");
                if(timeTableDate.equals(yearDate+monthDate+dayDate))
                {
                    if(timetable.working.equals("10-14"))
                    {
                        days.get(i).staffDayShift.add(new Employee(timetable.employeeId, timetable.timeTableId));
                    }
                    if(timetable.working.equals("16-23"))
                    {
                        days.get(i).staffNightShift.add(new Employee(timetable.employeeId, timetable.timeTableId));
                    }
                }
            }
            now.add(Calendar.DAY_OF_MONTH, 1); //Iterera med ett steg, alltså iterera till nästa dag.
        }

        //Hämta personuppgifter för de personer som jobbar och lagra det, detta hämtas genom att jämföra employeeId som redan hämtades ovan
        for(TableEmployee employeeTable : api.PARSEDApi().getEmployeeList())
        {
            for(Day day : days)
            {
                for(Employee employee : day.staffDayShift)
                {
                    if(employee.employeeId.equals(employeeTable.employeeId))
                        employee.employeeName = employeeTable.fName + " " + employeeTable.lName;
                }
                for(Employee employee : day.staffNightShift)
                {
                    if(employee.employeeId.equals(employeeTable.employeeId))
                        employee.employeeName = employeeTable.fName + " " + employeeTable.lName;
                }
            }
        }
    }

    private void openScheduleSwapShiftActivity(int timeTableId)
    {
        Intent intent = new Intent(this, ScheduleSwapShift.class);
        intent.putExtra("timeTableId", timeTableId);
        startActivity(intent);
    }

    private void openScheduleGetSpecificActivity()
    {
        Intent intent = new Intent(this, ScheduleGetSpecific.class);
        startActivity(intent);
    }

    private void openScheduleTakeShiftActivity(String time, String date)
    {
        Intent intent = new Intent(this, ScheduleTakeShift.class);
        intent.putExtra("time", time);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}

class Day
{
    public String day;
    public String dayDate;
    public String monthDate;
    public String yearDate;
    public List<Employee> staffDayShift;
    public List<Employee> staffNightShift;

    Day(String day, String dayDate, String monthDate, String yearDate)
    {
        this.day = day;
        this.dayDate = dayDate;
        this.monthDate = monthDate;
        this.yearDate = yearDate;
        staffDayShift = new ArrayList<Employee>();
        staffNightShift = new ArrayList<Employee>();
    }
}

class Employee
{
    public String employeeId;
    public String employeeName;
    public String timeTableId;
    Employee(String employeeId, String timeTableId)
    {
        this.employeeId = employeeId;
        this.timeTableId = timeTableId;
    }
}