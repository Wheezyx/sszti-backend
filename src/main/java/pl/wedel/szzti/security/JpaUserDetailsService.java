package pl.wedel.szzti.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.repository.UserRepository;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with username=%s was not found", username)));
  }
}
