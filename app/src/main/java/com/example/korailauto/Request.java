package com.example.korailauto;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {
    protected Document reqResult;
    protected Map<String, String> requestData = new HashMap<>();

    protected Document request(String conURL, String OriginURL, String Referer, Map<String, String> cookie){
        Document resp = null;
        try{
            resp = Jsoup.connect(conURL)
                    .timeout(3000)
                    .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Whale/2.7.98.22 Safari/537.36")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Host", "www.letskorail.com")
                    .header("Origin", OriginURL)
                    .header("Referer", Referer)
                    .cookies(cookie)
                    .data(requestData)
                    .post();
            reqResult = resp;
            return reqResult;
        }catch (IOException e){
            e.printStackTrace();
            Log.d("[Log]", "Failed to reserve Train");
            return reqResult = null;
        }
    }

    protected Document getReqResult(){
        return reqResult;
    }
}
