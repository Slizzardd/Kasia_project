package ua.com.alevel.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ua.com.alevel.config.security.jwt.JwtConfigurer;
import ua.com.alevel.config.security.jwt.JwtTokenProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private static final String USER_ENDPOINT = "/api/v1/user/**";
    private static final String TEACHER_REGISTRATION_ENDPOINT = "/api/v1/teacher/registrationTeacher";
    private static final String TEACHER_ENDPOINT = "/api/v1/teacher/**";
    private static final String COURSES_FOR_NOT_AUTH_ENDPOINT = "/api/v1/course/findAll";
    private static final String COURSES_FOR_TEACHER = "/api/v1/course/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                    .exceptionHandling()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(LOGIN_ENDPOINT).permitAll()
                    .antMatchers(COURSES_FOR_NOT_AUTH_ENDPOINT).permitAll()
                    .antMatchers(ADMIN_ENDPOINT).hasRole("developers:write")
                    .antMatchers(USER_ENDPOINT).hasRole("developers:read")
                    .antMatchers(TEACHER_REGISTRATION_ENDPOINT).hasRole("developers:read")
                    .antMatchers(TEACHER_ENDPOINT).hasRole("developers:educate")
                    .antMatchers(COURSES_FOR_TEACHER).hasRole("developers:educate")
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .httpBasic();;

    }
}
