package com.monopoly.backend.domain;

import com.monopoly.backend.domain.entity.User;
import com.monopoly.backend.domain.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:applicationTest.properties")
@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        userRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        userRepository.save(User.builder()
                .id(1L)
                .email("test@naver.com")
                .gender("male")
                .nickname("test")
                .phone("010-1234-1234")
                .type(0)
                .build());

//        // when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle(), is("테스트 게시글"));
//        assertThat(posts.getContent(), is("테스트 본문"));
    }

}
