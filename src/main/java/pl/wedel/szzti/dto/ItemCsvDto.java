package pl.wedel.szzti.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Getter
@Setter
public class ItemCsvDto {

  @CsvBindByName(column = "id", required = true)
  @CsvBindByPosition(position = 0)
  private UUID id;

  @CsvBindByName(column = "Pełna nazwa produktu", required = true)
  @CsvBindByPosition(position = 1)
  private String fullItemName;

  @CsvBindByName(column = "Kod inwentarzowy", required = true)
  @CsvBindByPosition(position = 2)
  private String inventoryCode;

  @CsvBindByName(column = "Data dostarczenia", required = true)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @CsvBindByPosition(position = 3)
  private LocalDate dateOfDelivery;

  @CsvBindByName(column = "Miejsce zaksięgowania", required = true)
  @CsvBindByPosition(position = 4)
  private String placeOfPosting;

  @CsvBindByName(column = "Nazwa", required = true)
  @CsvBindByPosition(position = 5)
  private String insideType;

  @CsvBindByName(column = "?", required = true)
  @CsvBindByPosition(position = 6)
  private boolean equipment;

  @CsvBindByName(column = "Typ przedmiotu", required = true)
  @CsvBindByPosition(position = 7)
  private String itemType;

  @CsvBindByName(column = "Nazwa generyczna", required = true)
  @CsvBindByPosition(position = 8)
  private String genericName;

  @CsvBindByName(column = "Opis", required = true)
  @CsvBindByPosition(position = 9)
  private String description;

  @CsvBindByName(column = "Nazwa przedmiotu nadrzędnego", required = true)
  @CsvBindByPosition(position = 10)
  private String parentName;

  @CsvBindByName(column = "Nazwa miejsca wypożyczenia", required = true)
  @CsvBindByPosition(position = 11)
  private String placeName;

  @CsvBindByName(column = "Kod osoby wypożyczającej", required = true)
  @CsvBindByPosition(position = 12)
  private String renterCode;
}
