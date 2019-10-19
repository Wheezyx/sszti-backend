package pl.wedel.szzti.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "place_item")
@Getter
@Setter
@IdClass(ItemPlaceId.class)
public class PlaceItem {

  @Id
  @ManyToOne
  @JoinColumn(name = "item_id", referencedColumnName = "id")
  private Item item;

  @Id
  @ManyToOne
  @JoinColumn(name = "place_id", referencedColumnName = "id")
  private Place place;


  private Integer quantity;

}
