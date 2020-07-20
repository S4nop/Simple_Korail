package com.example.korailauto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class Train implements ITrain{
    private String type, fee;
    private String startInf;
    private String endInf;
    private String trainNum;

    private String date, time, from, to;
    private boolean isReservable;
    private TextView tv[];
    private Button reservBtn;
    private TableRow thisRow;
    private Context supCon;

    public Train(String type, String fee, String si, String ei, String num, boolean rable){
        this.type = type;
        this.fee = fee;
        startInf = si;
        endInf = ei;
        trainNum = num;
        isReservable = rable;
    }


    @Override
    public boolean reserve() {
        Log.d("Train", ""+isReservable);
        Intent intent = new Intent(supCon, CheckerService.class);
        intent.putExtra("tNum", trainNum);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("from", from);
        intent.putExtra("to", to);
        supCon.startService(intent);
        return false;
    }

    @Override
    public void makeTrainData(String d) {

    }

    @Override
    public int getTrainType() {
        return 0;
    }

    @Override
    public String getStartInfo() {
        return startInf;
    }

    @Override
    public String getEndInfo() {
        return endInf;
    }

    @Override
    public TableRow makeRow(){
        thisRow = new TableRow(supCon);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        thisRow.setLayoutParams(lp);
        for(int i = 0; i < 4; i++)
            thisRow.addView(tv[i]);
        thisRow.addView(reservBtn);
        return thisRow;
    }

    @Override
    public String getTrainNum(){
        return trainNum;
    }

    @Override
    public boolean chkReservable(){
        return isReservable;
    }

    public void prepare(Context con, String date, String time, String from, String to){
        supCon = con;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        makeBtn();
        makeTv();
    }
    private void makeBtn(){
        reservBtn = new Button(supCon);
        reservBtn.setText("예약하기");
        reservBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reserve();
            }
        });
    }

    private void makeTv(){
        tv = new TextView[5];
        for(int i = 0; i < 4; i++){
            tv[i] = new TextView(supCon);
            tv[i].setGravity(Gravity.CENTER);
        }
        tv[0].setText(type);
        tv[1].setText(startInf);
        tv[2].setText(endInf);
        tv[3].setText(fee);
    }
}
