package com.example.korailauto;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class Train implements ITrain{
    private String type, fee;
    private String[] timeInf = new String[2];
    private String[] fromTo = new String[2];
    private TextView tv[];
    private Button reservBtn;
    private TableRow thisRow;
    private Context supCon;

    public Train(Context sup){
        supCon = sup;
    }

    public void test(){
        type = "무궁화";
        fee = "2700";
        timeInf[0] = "17:00";
        timeInf[1] = "18:00";
        fromTo[0] = "서울";
        fromTo[1] = "수원";
        makeBtn();
        makeTv();
    }

    @Override
    public boolean reserve() {
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
    public String[] getTimeInfo() {
        return new String[0];
    }

    @Override
    public String[] getFromTo() {
        return new String[0];
    }

    @Override
    public TableRow makeRow(){
        thisRow = new TableRow(supCon);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        thisRow.setLayoutParams(lp);
        for(int i = 0; i < 5; i++)
            thisRow.addView(tv[i]);
        thisRow.addView(reservBtn);
        return thisRow;
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
        for(int i = 0; i < 5; i++){
            tv[i] = new TextView(supCon);
            tv[i].setGravity(Gravity.CENTER);
        }
        tv[0].setText(type);
        tv[1].setText(timeInf[0] + " ~ " + timeInf[1]);
        tv[2].setText(fromTo[0]);
        tv[3].setText(fromTo[1]);
        tv[4].setText(fee);
    }
}
