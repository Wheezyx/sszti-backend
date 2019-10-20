package pl.wedel.szzti.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.wedel.szzti.domain.UserAuthority;
import pl.wedel.szzti.security.settings.SecurityConstants;

@Slf4j
class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader(SecurityConstants.TOKEN_HEADER);
    if (header == null || header.isEmpty() || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      log.debug("JWT token not found, ignore the header");
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    try {
      byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
      Jws<Claims> parsedToken = Jwts.parser()
          .setSigningKey(signingKey)
          .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

      String username = parsedToken.getBody().getSubject();

      List<String> stringRoles = (List<String>) parsedToken.getBody().get("roles");

      List<GrantedAuthority> roles = stringRoles.stream()
          .map(UserAuthority::new)
          .collect(Collectors.toList());

      if (username.isEmpty() || roles.isEmpty()) {
        return null;
      }

      return new UsernamePasswordAuthenticationToken(username, null, roles);
    } catch (ExpiredJwtException | MalformedJwtException exception) {
      log.debug(exception.getMessage());
      return null;
    }
  }
}