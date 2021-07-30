package com.jojoldu.book.springboot.web;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//1. 다른 실행자 실행, 스프링부트, junit 연결
@RunWith(SpringRunner.class)
//2. 여러 스프링 테스트 어노테이션중 web에 집중할 수 있는 어노테이션
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //3. 빈주입
    @Autowired
    //4. 웹 api테스트 사용
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        //5. http get 요청
        mvc.perform(get("/hello"))
                //6. 결과검증 200인지 ok인지
                .andExpect(status().isOk())
                //7 리턴 hello 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;


        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }


}
//    @Test
//    public void returnHelloDto() throws Exception {
//        String name = "test";
//        int amount = 100;
//
//        mvc.perform(get("/hello/dto")
//                .param("name", name)
//                .param("amount", String.valueOf(amount)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name)))
//                .andExpect(jsonPath("$.amount", is(amount)));
//    }
//}
