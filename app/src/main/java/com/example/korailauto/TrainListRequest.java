package com.example.korailauto;

import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrainListRequest {
    Elements reqResult;

    public TrainListRequest sendRequest(String dateInfo, String time, String from, String to){
        String[] parsed = parseDate(dateInfo);
        Map<String, String> loginData = new HashMap<>();
        loginData.put("txtGoStartCode", "");
        loginData.put("txtGoEndCode", "0003");
        loginData.put("radJobId", "1");
        loginData.put("selGoTrain", "05");
        loginData.put("txtSeatAttCd_4", "015");
        loginData.put("txtSeatAttCd_3", "000");
        loginData.put("txtSeatAttCd_2", "000");
        loginData.put("txtPsgFlg_2", "0");
        loginData.put("txtPsgFlg_3", "0");
        loginData.put("txtPsgFlg_4", "0");
        loginData.put("txtPsgFlg_5", "0");
        loginData.put("chkCpn", "N");
        loginData.put("selGoSeat1", "015");
        loginData.put("selGoSeat2", "");
        loginData.put("txtPsgCnt1", "1");
        loginData.put("txtPsgCnt2", "0");
        loginData.put("txtGoPage", "1");
        loginData.put("txtGoAbrdDt", parsed[4]);
        loginData.put("checkStnNm", "Y");
        loginData.put("txtMenuId", "11");
        loginData.put("SeandYo", "N");
        loginData.put("txtGoStart", from);
        loginData.put("txtGoEnd", to);
        loginData.put("start", parsed[5]);
        loginData.put("selGoHour", time);
        loginData.put("txtGoHour", time + "0000");
        loginData.put("selGoYear", parsed[0]);
        loginData.put("selGoMonth", parsed[1]);
        loginData.put("selGoDay", parsed[2]);
        loginData.put("selGoYoil", parsed[3]);
        loginData.put("txtPsgFlg_1", "1");

        Document loginResp = null;
        try {
            loginResp = Jsoup.connect("http://www.letskorail.com/ebizprd/EbizPrdTicketPr21111_i1.do")
                    .timeout(3000)
                    .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Whale/2.7.98.22 Safari/537.36")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Host", "www.letskorail.com")
                    .header("Origin", "https://info.korail.com")
                    .header("Referer", "https://letskorail.com/ebizprd/prdMain.do")
                    .data(loginData)
                    .post();
            reqResult = loginResp.select("#tableResult");
            return this;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("[Log]","Failed to get train list");
            return null;
        }
    }

    public Elements getReqResult() {
        return reqResult;
    }

    private String[] parseDate(String date){
        String[] rslt = new String[6];
        rslt[3] = date.split("-")[1];
        String[] tmp = date.split("-")[0].split("\\.");
        rslt[0] = tmp[0];
        rslt[1] = tmp[1];
        rslt[2] = tmp[2];
        rslt[4] = TextUtils.join("", tmp);
        tmp[1] = Integer.toString(Integer.parseInt(tmp[1]));
        tmp[2] = Integer.toString(Integer.parseInt(tmp[2]));
        rslt[5] = TextUtils.join(".", tmp);

        return rslt;
    }
}
