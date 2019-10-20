package pl.wedel.szzti.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.wedel.szzti.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaUserDetailsServiceTest {

  @Mock
  private UserRepository userRepository;

  private JpaUserDetailsService userDetailsService;

  @Before
  public void setUp() throws Exception {
    userDetailsService = new JpaUserDetailsService(userRepository);
  }

  @Test(expected = UsernameNotFoundException.class)
  public void shouldThrowExceptionWhenUsernameNotFound() {
    //given
    when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());
    String username = "testname";

    //when
    userDetailsService.loadUserByUsername(username);
  }
}
