package com.mercado.config;
import com.mercado.model.Usuario; import com.mercado.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner init(UsuarioRepository repo,PasswordEncoder encoder,@Value("${app.admin.username}")String username,@Value("${app.admin.password}")String password){
  return args->{if(repo.findByUsername(username).isEmpty()){Usuario u=new Usuario();u.setNome("Administrador");u.setUsername(username);u.setPassword(encoder.encode(password));repo.save(u);}};
 }
}
