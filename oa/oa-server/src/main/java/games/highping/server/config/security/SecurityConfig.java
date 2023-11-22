package games.highping.server.config.security;

import games.highping.server.pojo.Admin;
import games.highping.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 关闭csrf防护
            .sessionManagement()// 基于token，所以不需要session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 禁用session
            .and()
            .authorizeRequests().anyRequest().authenticated() // 所有请求都需要认证
            //.authorizeRequests().antMatchers("/admin/info").permitAll() // 放行登录和注册接口
            .and()
            .headers().cacheControl(); // 禁用缓存
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)
            .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //"/admin/info",
                "/noob/**",
                "/websocket/**",
                "/doc.html",
                "/**.html",
                "/login",
                "/login/**",
                "/register/**",
                "/logout/**",
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
                "favicon.ico",
                "/doc.html",                    // 放行 swagger 资源
                "/webjars/**",                  // 放行 swagger 资源
                "/swagger-resources/**",        // 放行 swagger 资源
                "/v2/api-docs/**",              // 放行 swagger 资源
                "/captcha",                     // 验证码接口
                "/ws/**"                        // 放行 websocket 资源
        );
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (null != admin) {
                return admin;
            }
            return null;
        };
    }

    //TODO update
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter() {
        return new JwtAuthencationTokenFilter();
    }

}
