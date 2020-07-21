package com.example.korailauto;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReserveTrain {
    Elements reqResult;
    Map<String, String> requestData = new HashMap<>();

    public ReserveTrain(){
        makeRequestData();
    }

    public String request(Map<String, String> cookie){
        Document resp = null;
        try{
            resp = Jsoup.connect("http://www.letskorail.com/ebizprd/EbizPrdTicketPr12111_i1.do")
                    .timeout(3000)
                    .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Whale/2.7.98.22 Safari/537.36")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Host", "www.letskorail.com")
                    .header("Origin", "http://www.letskorail.com")
                    .header("Referer", "http://www.letskorail.com/ebizprd/EbizPrdTicketPr21111_i1.do")
                    .cookies(cookie)
                    .data(requestData)
                    .post();
            reqResult = resp.select(".mt40").select("td").eq(2);
            return reqResult.toString();
        }catch (IOException e){
            e.printStackTrace();
            Log.d("[Log]", "Failed to reserve Train");
            return null;
        }
    }

    private void makeRequestData(){
        requestData.put("selGoTrain", "05");
        requestData.put("txtSeatAttCd_4", "015");
        requestData.put("txtSeatAttCd_3", "000");
        requestData.put("txtSeatAttCd_2", "000");
        requestData.put("txtPsgFlg_1", "1");
        requestData.put("txtPsgFlg_2", "0");
        requestData.put("txtPsgFlg_3", "0");
        requestData.put("txtPsgFlg_4", "0");
        requestData.put("txtPsgFlg_5", "0");
        requestData.put("selGoTrainRa", "05");
        requestData.put("radJobId", "1");
        requestData.put("selGoSeat1", "015");
        requestData.put("selGoSeat2", "015");
        requestData.put("txtPsgCnt1", "0");
        requestData.put("txtPsgCnt2", "0");
        requestData.put("txtGoPage", "1");
        requestData.put("txtGoAbrdDt", "20200724");
        requestData.put("checkStnNm", "Y");
        requestData.put("txtMenuId", "11");
        requestData.put("txtGoStart", "서울");
        requestData.put("txtGoEnd", "수원");
        requestData.put("selGoHour", "19");
        requestData.put("selGoYear", "2020");
        requestData.put("selGoMonth", "07");
        requestData.put("selGoDay", "24");
        requestData.put("selGoYoil","금");
        requestData.put("chkInitFlg", "Y");
        requestData.put("ra", "1");
        requestData.put("txtSeatAttCd1", "000");
        requestData.put("txtSeatAttCd2", "000");
        requestData.put("txtSeatAttCd3", "000");
        requestData.put("txtSeatAttCd4", "015");
        requestData.put("txtSeatAttCd5", "000");
        requestData.put("strChkCpn", "N");
        requestData.put("txtTotPsgCnt", "1");
        requestData.put("txtSrcarCnt", "0");
        requestData.put("txtSrcarCnt1", "0");
        requestData.put("hidRsvTpCd", "03");
        requestData.put("txtPsgTpCd1", "1");
        requestData.put("txtPsgTpCd2", "1");
        requestData.put("txtPsgTpCd3", "1");
        requestData.put("txtPsgTpCd5", "1");
        requestData.put("txtPsgTpCd7", "1");
        requestData.put("txtPsgTpCd8", "1");
        requestData.put("txtPsgTpCd9", "1");
        requestData.put("txtDiscKndCd1", "000");
        requestData.put("txtDiscKndCd2", "000");
        requestData.put("txtDiscKndCd3", "111");
        requestData.put("txtDiscKndCd5", "131");
        requestData.put("txtDiscKndCd7", "112");
        requestData.put("txtCompaCnt1", "1");
        requestData.put("txtCompaCnt2", "0");
        requestData.put("txtCompaCnt3", "0");
        requestData.put("txtCompaCnt4", "0");
        requestData.put("txtCompaCnt5", "0");
        requestData.put("txtCompaCnt6", "0");
        requestData.put("txtCompaCnt7", "0");
        requestData.put("txtJobId", "1101");
        requestData.put("txtJrnyCnt", "1");
        requestData.put("txtDptStnConsOrdr1", "000001");
        requestData.put("txtArvStnConsOrdr1", "000009");
        requestData.put("txtDptStnRunOrdr1", "000001");
        requestData.put("txtArvStnRunOrdr1", "000003");
        requestData.put("txtPsrmClCd1", "1");
        requestData.put("txtJrnySqno1", "001");
        requestData.put("txtJrnyTpCd1", "11");
        requestData.put("txtDptDt1", "20200724");
        requestData.put("txtDptRsStnCd1", "0001");
        requestData.put("txtDptRsStnCdNm1", "서울");
        requestData.put("txtDptTm1", "191400");
        requestData.put("txtArvRsStnCd1", "0003");
        requestData.put("txtArvRsStnCdNm1", "수원");
        requestData.put("txtArvTm1", "194300");
        requestData.put("txtTrnNo1", "01033");
        requestData.put("txtRunDt1", "20200724");
        requestData.put("txtTrnClsfCd1", "08");
        requestData.put("txtTrnGpCd1", "101");
        requestData.put("txtChgFlg1", "N");
    }
}
