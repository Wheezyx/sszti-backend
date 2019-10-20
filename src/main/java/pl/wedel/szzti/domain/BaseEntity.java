package pl.wedel.szzti.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {

  @Id
  @GeneratedValue(
      strategy = GenerationType.AUTO,
      generator = "pg-uuid"
  )
  @GenericGenerator(
      name = "pg-uuid",
      strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
}
