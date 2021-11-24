//package com.monopoly.backend;
//
//import com.monopoly.backend.domain.entity.Notice;
//import com.monopoly.backend.domain.repository.NoticeRepository;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class NoticeRepositoryTest {
//
//    @Autowired
//    NoticeRepository noticeRepository;
//
////    @After
////    public void cleanup() {
////        noticesRepository.deleteAll();
////    }
//
//    @Test
//    public void 공지사항_게시글저장_불러오기() {
//        //given
//        String title = "테스트 공지사항 게시글";
//        String content = "테스트 본문";
////        Integer user_id = 45;
//
//        noticeRepository.save(Notice.builder()
////                .user_id(user_id)
//                .title(title)
//                .content(content)
//                .author("zerone0512@gmail.com")
//                .is_head(true)
//                .hit_cnt(0L)
//                .build());
//
//        //when
//        List<Notice> noticeList = noticeRepository.findAll();
//
//        //then
//        Notice notice = noticeList.get(0);
//        assertThat(notice.getTitle()).isEqualTo(title);
//        assertThat(notice.getContent()).isEqualTo(content);
//    }
//    //
//    @Test
//    public void BaseTimeEntity_등록() {
//        //given
//        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
//        noticeRepository.save(Notice.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .is_head(true)
//                .user_id(045L)
//                .hit_cnt(2L)
//                .build());
//        //when
//        List<Notice> noticeList = noticeRepository.findAll();
//
//        //then
//        Notice notice = noticeList.get(0);
//
//        System.out.println(">>>>>>>>> createDate=" + notice.getCreatedDate() + ", modifiedDate=" + notice.getModifiedDate());
//
//        assertThat(notice.getCreatedDate()).isAfter(now);
//        assertThat(notice.getModifiedDate()).isAfter(now);
//    }
//}
