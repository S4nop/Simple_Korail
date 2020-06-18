package com.example.korailauto;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public Map<String, String> loginKorail(String id, String pw) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("selInputFlg", "2");
        loginData.put("radIngrDvCd", "2");
        loginData.put("ret_url", "/mbs/www/index.jsp");
        loginData.put("hidMemberFlg", "1");
        loginData.put("UserId", id);
        loginData.put("UserPwd", pw);
        loginData.put("txtDv", "2");

        Connection.Response loginResp = null;
        try {
            loginResp = Jsoup.connect("https://www.letskorail.com/korail/com/loginAction.do")
                    .timeout(3000)
                    .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Whale/2.7.98.22 Safari/537.36")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Host", "www.letskorail.com")
                    .header("Origin", "https://info.korail.com")
                    .header("Referer", "https://info.korail.com/mbs/www/jsp/member/login.jsp?id")
                    .data(loginData)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("[Log]","Korail Login failed");
            return null;
        }
        if(loginResp.statusCode() != 403) return loginResp.cookies();

        Log.d("[Log]","Korail Login failed / status code : " + loginResp.statusCode());
        return null;
    }


}
