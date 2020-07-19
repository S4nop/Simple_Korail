package com.example.korailauto;

import android.util.Log;

import org.jsoup.select.Elements;

public class TrainMaker {
    Train trains[];

    public TrainMaker makeTrainList(String dateInfo, String time, String from, String to){
        TrainListRequest trq = new TrainListRequest();
        Elements elTrains = trq.sendRequest(dateInfo, time, from, to).getReqResult().select("tr");
        int tSize = elTrains.size();
        trains = new Train[tSize - 1];
        for(int i = 0; i < tSize - 1; i++){
            String param[] = parseTrainInfo(elTrains.eq(i + 1));
            trains[i] = new Train(param[0], param[1], param[2], param[3], param[5], param[4].equals("예약하기"));
        }
        return this;
    }

    public Train[] getTrainList(){
        return trains;
    }

    private String[] parseTrainInfo(Elements el){
        String rslt[] = new String[6];
        Elements tds = el.select("td");
        rslt[0] = tds.eq(0).text();
        rslt[1] = tds.eq(8).text();
        rslt[2] = tds.eq(2).text();
        rslt[3] = tds.eq(3).text();
        rslt[4] = tds.eq(5).toString().split("alt=\"")[1].split("\"")[0];
        rslt[5] = tds.eq(1).text();
        return rslt;
    }
}
