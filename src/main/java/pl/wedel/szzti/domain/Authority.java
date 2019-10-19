package pl.wedel.szzti.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority extends BaseEntity implements GrantedAuthority {

  private String authority;
}
