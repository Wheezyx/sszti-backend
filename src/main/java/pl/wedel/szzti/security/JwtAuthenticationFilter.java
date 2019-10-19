package pl.wedel.szzti.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.wedel.szzti.domain.User;
import pl.wedel.szzti.dto.Credentials;
import pl.wedel.szzti.security.settings.SecurityConstants;

@AllArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final ObjectMapper objectMapper;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    log.debug("Trying to authenticate user");
    try {
      Credentials credentials = objectMapper.readValue(request.getInputStream(), Credentials.class);
      return authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
              credentials.getPassword()));

    } catch (IOException e) {
      log.error(e.getMessage());
      return authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(null, null));
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();

    Set<String> roles = user.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet());
    log.debug("Generating token.");
    String token = Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .setIssuer(SecurityConstants.TOKEN_ISSUER)
        .setSubject(user.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + 8640000))
        .claim("roles", roles)
        .compact();

    //TODO Replace to put the token in body, not a header.
    response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
  }
}

