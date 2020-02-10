package my.mins.aus.common;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WelcomeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("index page test")
    @Order(1)
    @WithAnonymousUser
    void test1() throws Exception {
        mockMvc.perform(get("/")/*.with(anonymous())*/)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("user page test with User(minssogi)")
    @Order(2)
    @WithMockUser(username = "minssogi", roles = "USER")
    void test2() throws Exception {
        mockMvc.perform(get("/user")/*.with(user("minssogi").roles("USER"))*/)
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("admin page test with Admin(admin)")
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void test3() throws Exception {
        mockMvc.perform(get("/admin")/*.with(user("admin").roles("ADMIN"))*/)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("admin page test with User(minssogi)")
    @Order(4)
    void test4() throws Exception {
        mockMvc.perform(get("/admin").with(user("minssogi").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("폼 로그인 테스트")
    @Order(5)
    void test5() throws Exception {
        mockMvc.perform(formLogin().user("minssogi").password("123"))
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("폼 로그인 테스트 - 틀린 비밀번호 사용")
    @Order(6)
    void test6() throws Exception {
        mockMvc.perform(formLogin().user("minssogi").password("123555555"))
                .andExpect(unauthenticated());
    }
}
