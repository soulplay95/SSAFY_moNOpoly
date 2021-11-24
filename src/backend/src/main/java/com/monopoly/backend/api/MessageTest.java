package com.monopoly.backend.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MessageTest {

    public static void main(String[] args) {
        KakaoMessageAPI api = new KakaoMessageAPI();

        api.sendMessage("81OD78jhWk2fnpFU3nzmJJz-NYwhuHzAChT8MAo9dVoAAAF7U0GXOA", 30, "2021-08-05 14:48:37");
//        se();
    }

    public static void se() {
        try {
            URL url = new URL("https://kapi.kakao.com/v2/api/talk/memo/default/send");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Authorization", "Bearer 5nF9Y4_gloXQlxpgzQMHYYCdaIa_PY5sJ00JZQo9c-sAAAF7Ur5flg");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = "template_object={\n        \"object_type\": \"text\",\n        \"text\": \"텍스트 영역입니다. 최대 200자 표시 가능합니다.\",\n        \"link\": {\n            \"web_url\": \"https://developers.kakao.com\",\n            \"mobile_web_url\": \"https://developers.kakao.com\"\n        },\n        \"button_title\": \"바로 확인\"\n    }";

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
