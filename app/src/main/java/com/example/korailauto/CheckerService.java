package com.example.korailauto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    private boolean chkTrainReservable(String trainNum, String date, String time, String from, String to){
        TrainListRequest tr = new TrainListRequest();
        Elements elTrains = tr.sendRequest(date, time, from, to).getReqResult();
        for(Element el : elTrains){
            if(el.select("td").eq(0).text().equals(trainNum))
                return el.select("td").toString().split("alt=\"")[1].split("\"")[0].equals("예약하기");
        }
        return false;
    }
}
