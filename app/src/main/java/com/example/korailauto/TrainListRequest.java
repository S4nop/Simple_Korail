package com.example.korailauto;

import android.text.TextUtils;

import org.jsoup.select.Elements;

import java.util.Map;

public class TrainListRequest extends Request{
    Elements parseResult;
    public TrainListRequest sendRequest(String dateInfo, String time, String from, String to, Map<String, String> cookies){
        String[] parsed = parseDate(dateInfo);
        makeRequestData(parsed, time, from, to);

        if(request("http://www.letskorail.com/ebizprd/EbizPrdTicketPr21111_i1.do", "https://info.korail.com"
        , "https://letskorail.com/ebizprd/prdMain.do", cookies) != null) {
            parseResult = reqResult.select("#tableResult");
            return this;
        }
        else{
            return null;
        }
    }

    public Elements getParseResult() {
        return parseResult;
    }

    private void makeRequestData(String[] parsed, String time, String from, String to){
        requestData.put("txtGoStartCode", "");
        requestData.put("txtGoEndCode", "0003");
        requestData.put("radJobId", "1");
        requestData.put("selGoTrain", "05");
        requestData.put("txtSeatAttCd_4", "015");
        requestData.put("txtSeatAttCd_3", "000");
        requestData.put("txtSeatAttCd_2", "000");
        requestData.put("txtPsgFlg_2", "0");
        requestData.put("txtPsgFlg_3", "0");
        requestData.put("txtPsgFlg_4", "0");
        requestData.put("txtPsgFlg_5", "0");
        requestData.put("chkCpn", "N");
        requestData.put("selGoSeat1", "015");
        requestData.put("selGoSeat2", "");
        requestData.put("txtPsgCnt1", "1");
        requestData.put("txtPsgCnt2", "0");
        requestData.put("txtGoPage", "1");
        requestData.put("txtGoAbrdDt", parsed[4]);
        requestData.put("checkStnNm", "Y");
        requestData.put("txtMenuId", "11");
        requestData.put("SeandYo", "N");
        requestData.put("txtGoStart", from);
        requestData.put("txtGoEnd", to);
        requestData.put("start", parsed[5]);
        requestData.put("selGoHour", time);
        requestData.put("txtGoHour", time + "0000");
        requestData.put("selGoYear", parsed[0]);
        requestData.put("selGoMonth", parsed[1]);
        requestData.put("selGoDay", parsed[2]);
        requestData.put("selGoYoil", parsed[3]);
        requestData.put("txtPsgFlg_1", "1");
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
