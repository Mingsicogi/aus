package my.mins.aus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/account/add");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // "/" uri는 인증, 인가 없이 접근가능.
        http.authorizeRequests()
                .mvcMatchers("/").permitAll();

        // "/admin" uri는 인증 후 ADMIN 권한을 가지고 있을때 접근 가능
        http.authorizeRequests()
                .mvcMatchers("/admin").hasRole("ADMIN");

        // 나머지 URI는 인증 후 접근가능
        http.authorizeRequests()
                .anyRequest().authenticated();

        http.formLogin(); // form login on
    }

    /**
     * spring 5 이상부터는 기본 패스워드 인코딩 방식을 bcrypt로 하고 encoder를 제공하는 방식도 변경함.(다양한 알고리즘 제공을 위해)
     * - PasswordEncoder를 Bean으로 등록하면 인증 방식에 Spring이 사용함.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
