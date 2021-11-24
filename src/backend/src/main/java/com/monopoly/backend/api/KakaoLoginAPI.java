package com.monopoly.backend.api;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoLoginAPI {

    /**
     * 인가 코드로 토큰정보 받아오기
     * @param code
     * @return Map
     */
    public Map<String, Object> getTokens(String code) {
        // tokens
        Map<String, Object> result = new HashMap<>();
        String access_token = "";
        String refresh_token = "";

        String requestURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=ea244da48a50205359e79f7692d3ac3a");
            sb.append("&redirect_uri=http://localhost/login");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("response code = " + responseCode); // log

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String body = "";
            while((line = br.readLine()) != null) {
                body += line;
            }
            System.out.println("response body = " + body); // log

            JsonObject jsonObjectAlt = JsonParser.parseString(body).getAsJsonObject();

            access_token = jsonObjectAlt.get("access_token").getAsString();
            refresh_token = jsonObjectAlt.get("refresh_token").getAsString();
            result.put("access_token", access_token);
            result.put("refresh_token", refresh_token);

            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 유저 정보 받아오기
     * @param accessToken
     * @return Map
     */
    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String requestUrl = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode =" + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body ="+result);

            JsonParser parser = new JsonParser();
            JsonElement element =  parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String id = element.getAsJsonObject().get("id").getAsString();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();

            userInfo.put("id", id);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("gender", gender);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    /**
     * 로그아웃
     * @param accessToken
     */
    public void kakaoLogout(String accessToken) {
        String requestURL = "http://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while((line = br.readLine()) != null) {
                result+=line;
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}