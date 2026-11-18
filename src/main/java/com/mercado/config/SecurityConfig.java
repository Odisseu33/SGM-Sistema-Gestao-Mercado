package com.mercado.config;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  http.authorizeHttpRequests(a->a.requestMatchers("/login","/css/**","/js/**","/webjars/**","/h2-console/**").permitAll().anyRequest().hasRole("ADMIN"))
   .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/",true).failureUrl("/login?erro").permitAll())
   .logout(l->l.logoutSuccessUrl("/login?saiu").permitAll())
   .headers(h->h.frameOptions(f->f.sameOrigin()));
  http.csrf(c->c.ignoringRequestMatchers("/h2-console/**"));
  return http.build();
 }
}
