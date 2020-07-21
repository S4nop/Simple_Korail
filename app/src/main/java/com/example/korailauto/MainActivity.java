package com.example.korailauto;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TableLayout tbl;
    EditText txtDate, txtFrom, txtTo;
    Button btnSearch;
    View.OnClickListener selDate, search;
    Spinner spnTime;
    Context con;
    DatePickerDialog dialog;
    DatePickerDialog.OnDateSetListener dListener;
    Map<String, String> cookies;
    Train trains[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this;
        cookies = (HashMap<String, String>)getIntent().getSerializableExtra("hashMap");
        initListener();
        initView();
        makeComboBox();

        txtDate.setOnClickListener(selDate);
        btnSearch.setOnClickListener(search);
    }

    public Map<String, String> getCookies(){
        return cookies;
    }
    private void removeTable(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tbl.removeAllViews();
            }
        });
    }

    private void makeTable(Train trains[]){
        String date = txtDate.getText().toString();
        String time = spnTime.getSelectedItem().toString();
        for(Train train : trains) {
            train.prepare(con, date, time, cookies);
            addRow(train);
            Log.d("[Log]", train.getStartInfo());
        }
    }

    private void initView(){
        tbl = findViewById(R.id.tblTrains);
        txtDate = findViewById(R.id.txtDate);
        spnTime = findViewById(R.id.spnTime);
        btnSearch = findViewById(R.id.btnSearch);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
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
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy.mm.dd-EE", Locale.KOREA);
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
        search = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(){
                    public void run(){
                        TrainMaker tr = new TrainMaker();
                        trains = tr.makeTrainList(txtDate.getText().toString(), spnTime.getSelectedItem().toString(),
                                txtFrom.getText().toString(), txtTo.getText().toString(), cookies).getTrainList();
                        removeTable();
                        makeTable(trains);
                    }
                }.start();

            }
        };
    }
    private void makeComboBox(){
        ArrayAdapter aad = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_dropdown_item);
        spnTime.setAdapter(aad);
    }

    private void addRow(final Train train){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tbl.addView(train.makeRow());
            }
        });
    }

}
