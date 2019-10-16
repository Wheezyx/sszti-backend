package pl.wedel.szzti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "item")
public class Item extends BaseEntity {

  private String placeOfPosting;

  @Column(nullable = false, columnDefinition = "text")
  @Getter
  @Setter
  @Enumerated(value = EnumType.STRING)
  private InsideType insideType;

  private boolean wyposazenie; //TODO CHANGE NAME

  private String inventoryCode;
}
