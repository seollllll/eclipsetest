package com.spring.boardweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.boardweb.oauth.Oauth2UserService;

@Configuration // 시큐리티의 필터체인을 구현하기 위해 선언
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private Oauth2UserService oauth2UserService;
	
	
	// 비밀번호 암호화 인코더 추가
	//Oauth2UserService 가 passwordEncoder를 바로 호출하여 사용하기 때문에 
	//static 으로 선언해서 변경
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain fileterChain(HttpSecurity http) throws Exception {
		// authozieHttpRequests로 요청에 대한 권한을 설정
		http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/user/**").permitAll()
				.antMatchers("/board/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/upload/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.anyRequest().authenticated(); // 인증된 사용자만 할 수 있도록

		// 로그인.로그아웃 처리
		http.formLogin().loginPage("/user/login")
				// 시큐리티에서는 id를 username으로, pw는 password로 사용
				.usernameParameter("userId") // 우리가 쓰는 아이디를 파라미터값으로 줘서 username이 userId라고 알려줌
				.passwordParameter("userPw")
				// 다음요청이 들어오면 시큐리티가 낚아채서 로그인처리함
				.loginProcessingUrl("/user/loginProc")
				// 로그인 성공 시 이동할 페이지 지정
				.defaultSuccessUrl("/")
				
				//OAuth 기반 로그인 설정
				.and()
				.oauth2Login()
				.loginPage("/user.login")
				//토큰 발행 후 처리
				//토큰이 발행되면 사용자 정보를 받아서 사용할 수 있는데 사용자 정보를 웹사이트에 맞도록 수정하는 작업 필요
				.userInfoEndpoint()
				//사용자 정보를 웹사이트에 맞도록 수정해주는 service 클래스 등록
				.userService(oauth2UserService);
		http.logout()
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/user/login");

		// 크로스 도메인 공격 방지
		http.csrf().disable();

		return http.build();
	}
}
