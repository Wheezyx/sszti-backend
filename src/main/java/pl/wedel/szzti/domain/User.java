package pl.wedel.szzti.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

  private String username;
  private String password;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Authority> authorities;

}
