package com.example.samson.testproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private NumberPicker year;
    private NumberPicker month;
    private NumberPicker day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberPicker.OnValueChangeListener changeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                update();
            }
        };

        Calendar c = Calendar.getInstance();

        year = (NumberPicker) findViewById(R.id.numberPicker);
        year.setMaxValue(2200);
        year.setMinValue(1800);
        year.setValue(c.get(Calendar.YEAR));
        year.setOnValueChangedListener(changeListener);
        year.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        month = (NumberPicker) findViewById(R.id.numberPicker2);
        month.setMaxValue(12);
        month.setMinValue(1);
        month.setValue(c.get(Calendar.MONTH) + 1);
        month.setOnValueChangedListener(changeListener);
        month.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        day = (NumberPicker) findViewById(R.id.numberPicker3);
        day.setMaxValue(31);
        day.setMinValue(1);
        day.setValue(c.get(Calendar.DAY_OF_MONTH));
        day.setOnValueChangedListener(changeListener);
        day.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        update();
    }

    public void update()
    {
        Calendar c = Calendar.getInstance();
        validateDay(c, year.getValue(), month.getValue());
        updateText(c);
    }

    public void validateDay(Calendar c, int year, int month)
    {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setMaxValue(daysInMonth);
        c.set(Calendar.DAY_OF_MONTH, day.getValue());
    }

    public void updateText(Calendar c)
    {
        TextView textView = (TextView) findViewById(R.id.textView);

        String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        if (new Random().nextInt(1000) == 1)
        {
            days = new String[]{ "Sunday", "Monday", "Tuesday", "EAR DAY", "Thursday", "Friday", "Saturday" };
        }

        //System.out.println(months[c.get(Calendar.MONTH)] + " " + day.getValue() + ", " + year + " is on a "
         //       + days[c.get(Calendar.DAY_OF_WEEK) - 1]);

        textView.setText(months[c.get(Calendar.MONTH)] + " " + day.getValue() + ", " + year.getValue() +
                " is on a \n" + days[c.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateDialogue(MenuItem item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Future Updates");
        alert.setMessage("- Dates will be savable\n- Dates will push notifications as reminders");

        alert.setCancelable(false);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }
}
