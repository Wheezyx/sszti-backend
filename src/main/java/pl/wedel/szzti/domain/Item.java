package pl.wedel.szzti.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  @Enumerated(value = EnumType.STRING)
  private InsideType insideType;

  private boolean wyposazenie; //TODO CHANGE NAME

  private String inventoryCode;

  @Column(nullable = false, columnDefinition = "text")
  @Enumerated(value = EnumType.STRING)
  private ItemType itemType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "generic_name_id")
  private GenericName genericName;

  private String name;

  private Integer stockOnHand;

  @OneToMany(mappedBy = "item")
  private Set<PlaceItem> placeItems;
}
