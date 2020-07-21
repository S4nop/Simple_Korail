package com.example.korailauto;


import java.util.Map;

public class ReserveRequest extends Request{
    public ReserveRequest(String[] parsed, String hour, String yoil){
        makeRequestData(parsed, hour, yoil);
    }

    //TODO : check reservation result using return value
    public boolean reserve(Map<String, String> cookie){
       return request("http://www.letskorail.com/ebizprd/EbizPrdTicketPr12111_i1.do", "http://www.letskorail.com",
               "http://www.letskorail.com/ebizprd/EbizPrdTicketPr21111_i1.do", cookie) != null;
    }

    private void makeRequestData(String[] parsed, String hour, String yoil){
        requestData.put("selGoTrain", "05");
        requestData.put("txtPsgFlg_1", "1");
        requestData.put("txtPsgFlg_2", "0");
        requestData.put("txtPsgFlg_3", "0");
        requestData.put("txtPsgFlg_4", "0");
        requestData.put("txtPsgFlg_5", "0");
        requestData.put("txtSeatAttCd_3", "000");
        requestData.put("txtSeatAttCd_2", "000");
        requestData.put("txtSeatAttCd_4", "015");
        requestData.put("selGoTrainRa", "05");
        requestData.put("radJobId", "1");
        requestData.put("txtGoStart", parsed[19]);
        requestData.put("txtGoEnd", parsed[21]);
        requestData.put("selGoYear", parsed[27].substring(0, 4)); //2020
        requestData.put("selGoMonth", parsed[27].substring(4, 6)); //07
        requestData.put("selGoDay", parsed[27].substring(6, 8));
        requestData.put("selGoHour", hour);
        requestData.put("txtGoYoil", yoil);
        requestData.put("selGoSeat1", "015");
        requestData.put("selGoSeat2", "015");
        requestData.put("txtPsgCnt1", "0");
        requestData.put("txtPsgCnt2", "0");
        requestData.put("txtGoPage", "1");
        requestData.put("txtGoAbrdDt", parsed[27]); //20200724
        requestData.put("chkInitFlg", "Y");
        requestData.put("txtMenuId", "11");
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
        requestData.put("txtPsgTpCd2", "3"); //1?
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
        requestData.put("txtDptStnConsOrdr1", parsed[44]);//여기인
        requestData.put("txtArvStnConsOrdr1", parsed[45]);
        requestData.put("txtDptStnRunOrdr1", parsed[46]);
        requestData.put("txtArvStnRunOrdr1", parsed[47]);
        requestData.put("txtPsrmClCd1", "1");
        requestData.put("txtJrnySqno1", "001");
        requestData.put("txtJrnyTpCd1", "11");
        requestData.put("txtDptDt1", parsed[27]);
        requestData.put("txtDptRsStnCd1", parsed[18]); //0003?
        requestData.put("txtDptRsStnCdNm1", parsed[19]);
        requestData.put("txtDptTm1", parsed[29]); //출발시간이네
        requestData.put("txtArvRsStnCd1", parsed[20]); //0104?
        requestData.put("txtArvRsStnCdNm1", parsed[21]);
        requestData.put("txtArvTm1", parsed[31]); //도착시간
        requestData.put("txtTrnNo1", parsed[22]); //01033
        requestData.put("txtRunDt1", parsed[27]);
        requestData.put("txtTrnClsfCd1", parsed[24]); //02
        requestData.put("txtTrnGpCd1", parsed[25]); //102
        requestData.put("txtChgFlg1", "N");
    }
}
