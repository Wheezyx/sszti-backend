package pl.wedel.szzti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
public class ItemDto extends BaseDto {

  private String placeOfPosting;

  private String insideType;

  private boolean equipment;

  private String inventoryCode;

  private String itemType;

  private String genericName;

  private String fullItemName;

  private String description;

  @JsonProperty(access = Access.READ_ONLY)
  private boolean rented;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateOfDelivery;

  @JsonSerialize(as = ItemDto.class)
  @JsonProperty(access = Access.WRITE_ONLY)
  private ItemDto parent;
}
