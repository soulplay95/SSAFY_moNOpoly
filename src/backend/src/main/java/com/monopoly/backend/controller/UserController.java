package com.monopoly.backend.controller;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.monopoly.backend.dto.request.UserSaveReq;
import com.monopoly.backend.dto.response.UserRes;
import com.monopoly.backend.exception.ErrorCode;
import com.monopoly.backend.exception.NoUserIdException;
import com.monopoly.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "유저 API", tags = {"User"})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "<strong>카카오 API 사용자정보</strong>를 받아 유저 테이블을 조회하고 없으면 회원가입 시킨다.")
    @PostMapping("/login")
    public ResponseEntity<UserRes> login(
            @ApiParam(value = "카카오 로그인 API 사용자 정보 JSON", required = true)
            @RequestBody Map<String, Object> userInfo) {

        // 사용자 정보 Json을 파싱
        JsonParser parser = new JsonParser();
        JsonElement properties =  parser.parse(userInfo.get("properties").toString());
        JsonElement kakaoAccount =  parser.parse(userInfo.get("kakao_account").toString());

        Long id = Long.parseLong(userInfo.get("id").toString());
        String accessToken = userInfo.get("access_token").toString();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
//        String phone = kakaoAccount.getAsJsonObject().get("phone_number").getAsString();
        String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();
        Integer type = 0;

        log.debug(accessToken);

        // 회원가입될 유저 entity 빌드
        UserSaveReq user = UserSaveReq.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .phone("")
                .gender(gender)
                .type(type)
                .accessToken(accessToken)
                .build();

        // 회원 테이블에 존재하지 않으면 회원가입 시킨다.
        if (userService.findById(id) == null) {
            userService.save(user);
        }

        // access_token이 바뀌었으면 갱신시켜준다.
        if (accessToken.equals(userService.getAccessToken(id))) {
        } else {
            userService.updateAccessToken(user.toEntity());
        }

        return ResponseEntity.status(200).body(new UserRes(user.toEntity()));
    }

    @ApiOperation(value = "[조회] By ID", notes = "유저 ID로 조회한다.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserRes> findById(
            @ApiParam(value = "유저 ID", required = true)
            @PathVariable Long userId) {

        UserRes res = userService.findById(userId);
        if (res == null) {
            throw new NoUserIdException(ErrorCode.USER_NO_ID);
        }

        return ResponseEntity.status(200).body(res);
    }

//    로그인
//    @ApiOperation(value = "로그인", notes = "로그인 시 유저 테이블을 조회하여 데이터가 존재하면 회원번호(ID)를 반환한다.\n데이터가 없으면 회원가입 처리 후 ID 반환")
//    @GetMapping(value="/login")
//    public ResponseEntity<User> login(@ApiParam(value = "Authorization code(인가 코드)", required = true) @RequestParam("code") String code, HttpSession session) {
//        // 1. 받은 인가 코드로 토큰 정보(access_token, refresh_token)를 받아온다.
//        Map<String, Object> tokens = kakaoApi.getTokens(code);
//
//        // 2. access_token으로 유저 정보를 받아온다.
//        Map<String, Object> userInfo = kakaoApi.getUserInfo(tokens.get("access_token").toString());
//
//        Long id = Long.parseLong(userInfo.get("id").toString());
//
//        // 회원가입될 유저 객체 생성
//        User new_user = new User();
//        new_user.setId(id);
//        new_user.setNickname(String.valueOf(userInfo.get("nickname")));
//        new_user.setEmail(String.valueOf(userInfo.get("email")));
//        new_user.setPhone(String.valueOf(userInfo.get("phone")));
//        new_user.setGender(String.valueOf(userInfo.get("gender")));
//        new_user.setAccess_token(tokens.get("access_token").toString());
//        new_user.setRefresh_token(tokens.get("refresh_token").toString());
//
//        if (userInfo.get("id") != null) {
//            session.setAttribute("userId", userInfo.get("id"));
//            session.setAttribute("access_token", new_user.getAccess_token());
//        }
//
//        if (userService.findUser(id) != null) {
//            userService.join(new_user);
//        }
//
//        return new ResponseEntity<User>(new_user, HttpStatus.OK);
//    }

//    로그아웃
//    @ApiOperation(value = "로그아웃", notes = "세션의 accessToken을 날려버린다.")
//    @GetMapping(value="/logout")
//    public ResponseEntity<String> logout(HttpSession session) {
//        kakaoApi.kakaoLogout((String)session.getAttribute("access_token"));
//        session.removeAttribute("access_Token");
//        session.removeAttribute("userId");
//
//        return new ResponseEntity<String>(HttpStatus.OK);
//    }

}
