package pl.wedel.szzti.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "items")
@NoArgsConstructor
public class Item extends BaseEntity {

  //TODO Possible export to another table
  private String placeOfPosting;

  @Column(nullable = false, columnDefinition = "text")
  @Enumerated(value = EnumType.STRING)
  private InsideType insideType;

  private boolean equipment;

  private String inventoryCode;

  @Column(nullable = false, columnDefinition = "text")
  @Enumerated(value = EnumType.STRING)
  private ItemType itemType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "generic_name_id")
  private GenericName genericName;

  private String fullItemName;

  @OneToMany(mappedBy = "item",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Set<Rental> itemRentals;

  private String description;

  private LocalDate dateOfDelivery;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  @Getter
  @Setter
  private Item parent;
}
