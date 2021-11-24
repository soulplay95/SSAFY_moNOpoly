package com.monopoly.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monopoly.backend.dto.request.PenaltyLogSaveReq;
import com.monopoly.backend.dto.response.BaseResponseBody;
import com.monopoly.backend.dto.response.PenaltyLogRes;
import com.monopoly.backend.service.PenaltyLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "패널티 로그 API", tags = {"PenaltyLog"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/penaltylog")
public class PenaltyLogController {

    private final PenaltyLogService penaltyLogService;

    @ApiOperation(value = "[등록]", notes = "유저 ID, 좌석 ID를 입력받아 패널티 로그를 등록한다.\n\n사석화 1시간 감지 시 자동으로 패널티 로그에 올라간다.")
    @PostMapping
    public ResponseEntity<PenaltyLogRes> savePenaltyLog(
            @ApiParam(value = "유저 ID, 좌석 ID", required = true)
            @RequestBody PenaltyLogSaveReq penaltyLog) {

        return ResponseEntity.status(200).body(new PenaltyLogRes(penaltyLogService.save(penaltyLog)));
    }

    @ApiOperation(value = "[조회] 리스트 By 유저 ID", notes = "유저 ID로 패널티 로그 리스트를 조회한다.\n\n마이페이지에서 패널티 목록 조회 + 패널티 횟수 조회에 활용한다.")
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PenaltyLogRes>> getListUserId(
            @ApiParam(value = "유저 ID", required = true)
            @PathVariable Long userId) {

        return ResponseEntity.status(200).body(penaltyLogService.findAllByUserId(userId));
    }

    @ApiOperation(value = "[조회] 리스트 By 좌석 ID", notes = "좌석 ID로 패널티 로그 리스트를 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<List<PenaltyLogRes>> getListSeatId(
            @ApiParam(value = "좌석 ID", required = true)
            @RequestParam(value = "seatId") Long seatId) {

        return ResponseEntity.status(200).body(penaltyLogService.findAllBySeatId(seatId));
    }

//    @ApiOperation(value = "[카카오톡 메세지 보내기] by access_token", notes = "사용자의 access_token을 입력받아 메세지를 보낸다.")
//    @PostMapping("/message")
//    public ResponseEntity<? extends BaseResponseBody> savePenaltyLog(
//            @ApiParam(value = "사용자 access_token", required = true)
//            @RequestParam(value = "accessToken") String accessToken) {
//
//        String requestUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
//
//        try {
//            URL url = new URL(requestUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
////            conn.setDoInput(true);
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            Map<String, Object> templateObject = new HashMap<>();
//            Map<String, Object> data = new HashMap<>();
//            Map<String, Object> data2 = new HashMap<>();
//            data.put("object_type", "text");
//            data.put("text", "사석화 메시지 test");
//            data2.put("web_url", "www.naver.com");
//            data.put("link", data2);
//            templateObject.put("template_object", data);
//
//            try {
//                String json = new ObjectMapper().writeValueAsString(templateObject);
//                System.out.println(json);
//                bw.write(json);
//                bw.flush();
//                bw.close();
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode =" + responseCode);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            String line = "";
//            String result = "";
//
//            while((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            System.out.println("response body ="+result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "OK"));
//    }

}
