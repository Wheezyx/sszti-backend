package pl.wedel.szzti.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ItemPlaceId implements Serializable {
  static final long serialVersionUID = 42L;

  private UUID item;

  private UUID place;
}
