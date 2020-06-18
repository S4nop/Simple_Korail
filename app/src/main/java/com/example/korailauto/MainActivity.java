package com.example.korailauto;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TableLayout tbl;
    EditText txtDate;
    View.OnClickListener selDate;
    Spinner spnTime;
    DatePickerDialog dialog;
    DatePickerDialog.OnDateSetListener dListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbl = findViewById(R.id.tblTrains);
        Train train = new Train(this);
        addRow(train);
        initListener();
        initView();
        makeComboBox();

        txtDate.setOnClickListener(selDate);
    }

    private void initView(){
        txtDate = findViewById(R.id.txtDate);
        spnTime = findViewById(R.id.spnTime);
        Calendar c = Calendar.getInstance();
        dialog = new DatePickerDialog(this, dListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    private void initListener(){
        dListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Date date;
                try {
                    date = new SimpleDateFormat("yyyy-mm-dd").parse(Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(dayOfMonth) + " 00:00:00");
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy.m.d-EE", Locale.KOREA);
                    txtDate.setText(sf.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        selDate = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        };

    }
    private void makeComboBox(){
        ArrayAdapter aad = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_dropdown_item);
        spnTime.setAdapter(aad);
    }

    private void addRow(Train train){
        train.test();
        tbl.addView(train.makeRow());
    }

}
