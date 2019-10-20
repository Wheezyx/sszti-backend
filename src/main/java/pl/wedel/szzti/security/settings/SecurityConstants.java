package pl.wedel.szzti.security.settings;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//TODO Export those variables into application properties and .env file
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {

  public static final String JWT_SECRET =
      "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String TOKEN_ISSUER = "szzti";
}
