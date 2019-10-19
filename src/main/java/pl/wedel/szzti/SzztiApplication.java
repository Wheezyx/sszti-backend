package pl.wedel.szzti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.wedel.szzti.domain.User;
import pl.wedel.szzti.repository.UserRepository;

@SpringBootApplication
public class SzztiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SzztiApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    return args -> userRepository
        .save(new User("test", passwordEncoder.encode("test"), true, true, true, true, null));
  }
}


