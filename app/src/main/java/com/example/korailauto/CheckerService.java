package com.example.korailauto;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckerService extends Service {
    Context con;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Log.d("[LOG::Service]", "Service Created");
        con = this;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("[LOG::Service]", "Service Starts");
        serviceWork(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("[LOG::Service]", "Service Destroyed");

    }

    private void serviceWork(final Intent intent){
        final String trainNum = intent.getStringExtra("tNum");
        final String date = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");
        final String from = intent.getStringExtra("from");
        final String to = intent.getStringExtra("to");
        new Thread(){
            @Override
            public void run(){
                try {
                    while(!chkTrainReservable(trainNum, date, time, from, to))
                        sleep(1000);

                    Log.d("[LOG::Service]", "Now Reservable");
                    Notificator notificator = new Notificator(con);
                    notificator.showNotification("Train Available", "Train num " + trainNum + " is now available!!", "");
                    stopService(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private boolean chkTrainReservable(String trainNum, String date, String time, String from, String to){
        TrainListRequest tr = new TrainListRequest();
        Elements elTrains = tr.sendRequest(date, time, from, to).getReqResult().select("tr");
        for(Element el : elTrains){
            if(el.select("td").eq(1).text().equals(trainNum))
                return el.select("td").toString().split("alt=\"")[1].split("\"")[0].equals("예약하기");
        }
        return false;
    }
}
