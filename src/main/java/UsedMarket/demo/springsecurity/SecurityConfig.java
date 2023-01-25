package UsedMarket.demo.springsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final LoginSecurityService loginSecurityService;
    private final LoginOauth2Service loginOauth2Service;

    @Override               //HttpSecurity를 통해 HTTP 요청에 대한 보안을 설정할 수 있습니다.
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                //        모든 작업 끝나면 경로마다 권한 부여해야 함
                .authorizeRequests()
                .antMatchers("/init/login", "/member/new").permitAll()  //로그인없이 접근가능
                .anyRequest().authenticated()
                .and()
                .formLogin()//'formLogin()'에서 폼방식 로그인을 사용할 것임을 알림
                .loginPage("/")//커스텀 페이지로 로그인 페이지를 변경합니다.
                .usernameParameter("userEmail")
                .passwordParameter("passWord")
                .loginProcessingUrl("/member/loginPro")//form 태그의 Action URL.
                .defaultSuccessUrl("/home")//로그인인증 성공후 갈 페이지
                .failureForwardUrl("/loginFail")
                .permitAll()
                .and()
                .logout().logoutUrl("/member/logout").logoutSuccessUrl("/")
//                .invalidateHttpSession(true);   //세션 초기화

                .and()//oauth
                .oauth2Login()
                .loginPage("/")
                .defaultSuccessUrl("/oauth-home")
                .failureUrl("/loginFail")
                .userInfoEndpoint()
                .userService(loginOauth2Service);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {   //화면 깨지지않게 하기
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/files/**");

    }

    @Bean
    public BCryptPasswordEncoder getpasswordEncoder() { return new BCryptPasswordEncoder(); }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginSecurityService).passwordEncoder(getpasswordEncoder());
    }

}
