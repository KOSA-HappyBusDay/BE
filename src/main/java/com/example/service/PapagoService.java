package com.example.service;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class PapagoService {
    private final String clientId = "qCcsm1RiCl8NIxESAVS4"; // Your Client ID
    private final String clientSecret = "DJLFqMDTYU"; // Your Client Secret

    public String translate(String text) {
        String result = "";
        try {
            String encodedText = URLEncoder.encode(text, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            String postParams = "source=en&target=ko&text=" + encodedText;
            con.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(postParams);
            osw.flush();
            osw.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject =  (JSONObject)jsonParser.parse(new InputStreamReader(con.getInputStream()));
                JSONObject message = (JSONObject) jsonObject.get("message");
                JSONObject resultObject = (JSONObject) message.get("result");
                result = resultObject.get("translatedText").toString();
            } else {
                result = "번역에 실패했습니다. HTTP error code: " + responseCode;
            }
        } catch (Exception e) {
            result = "번역에 실패했습니다. Error: " + e.getMessage();
        }
        return result;
    }
}
