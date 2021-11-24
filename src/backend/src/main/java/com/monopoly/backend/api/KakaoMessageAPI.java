package com.monopoly.backend.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class KakaoMessageAPI {

    public void sendMessage(String accessToken, int type, String detectTime) {
        String requestUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Authorization", "Bearer " + accessToken);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String message = "";
            if (type == 30) {
                // -30분
                LocalDateTime time = StringToDateTimeAPI.stringToDateTime(detectTime);
                time = time.minusMinutes(30);
                detectTime = DateTimeToStringAPI.dateTimeToString(time);
                message = detectTime + " 이후로 30분간 사석화가 감지되었습니다.";
            } else if (type == 60) {
                message = "1시간동안 사석화가 감지되었습니다. 이 시간부로 자동 퇴실조치되며 패널티가 1회 누적됩니다.";
            }

            String data = "template_object={\n        \"object_type\": \"text\",\n        \"text\": \"" + message + "\",\n        \"link\": {\n            \"web_url\": \"https://i5a209.p.ssafy.io/#/home\",\n            \"mobile_web_url\": \"https://i5a209.p.ssafy.io/#/home\"\n        },\n        \"button_title\": \"확인\"\n    }";

            System.out.println(data);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
