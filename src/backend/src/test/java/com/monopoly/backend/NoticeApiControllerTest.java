//package com.monopoly.backend;//package com.moNOpoly.web;
//import com.monopoly.backend.domain.entity.Notice;
//import com.monopoly.backend.domain.repository.NoticeRepository;
//import com.monopoly.backend.dto.notice.NoticeSaveRequestDto;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class NoticeApiControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private NoticeRepository noticeRepository;
//
//    //    @Autowired
////    private WebApplicationContext context;
////
////    private MockMvc mvc;
////
////    @Before
////    public void setup() {
////        mvc = MockMvcBuilders
////                .webAppContextSetup(context)
////                .apply(springSecurity())
////                .build();
////    }
////
//    @After
//    public void tearDown() throws Exception {
//        noticeRepository.deleteAll();
//    }
//    @Test
//    @After
////    @WithMockUser(roles="USER")
//    public void Notice_등록된다() throws Exception {
//
//        //given
//        String title = "title";
//        String content = "content";
//        Boolean is_head = true;
////        Integer user_id = 045;
//        Long hit_cnt = 0L;
//
//        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .author("author")
//                .is_head(true)
//                .hit_cnt(hit_cnt)
////                .user_id(user_id)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/notice";
//
//        //when
////        mvc.perform(post(url)
////                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .content(new ObjectMapper().writeValueAsString(requestDto)))
////                .andExpect(status().isOk());
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Notice> all = noticeRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//
//    }
//}
