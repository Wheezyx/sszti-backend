package pl.wedel.szzti.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
public final class RentalSearchParameters {

  private static final String ITEM_ID = "itemId";
  private static final String PLACE_ID = "placeId";
  private static final String RENTER_ID = "renterId";

  private static final List<String> ALL_PARAMETERS = Collections
      .unmodifiableList(Arrays.asList(ITEM_ID, PLACE_ID, RENTER_ID));

  private HashMap<String, Object> queryParams;

  public RentalSearchParameters(Map<String, Object> queryMap) {
    queryMap.remove("sort");
    queryMap.remove("page");
    queryMap.remove("size");
    queryParams = new HashMap<>(queryMap);
    validate();
  }

  public boolean containsKey(String key) {
    return queryParams.containsKey(key);
  }

  public UUID getItemId() {
    if (!queryParams.containsKey(ITEM_ID)) {
      return null;
    }
    return UUID.fromString((String) queryParams.get(ITEM_ID));
  }

  public UUID getPlaceId() {
    if (!queryParams.containsKey(PLACE_ID)) {
      return null;
    }
    return UUID.fromString((String) queryParams.get(PLACE_ID));
  }

  public UUID getRenterId() {
    if (!queryParams.containsKey(RENTER_ID)) {
      return null;
    }
    return UUID.fromString((String) queryParams.get(RENTER_ID));
  }

  private void validate() {
    if (!ALL_PARAMETERS.containsAll(queryParams.keySet())) {
      throw new ValidationException(
          new ErrorMessage("Invalid query parameters. Available are: " + ALL_PARAMETERS.toString()));
    }
    queryParams.entrySet().stream()
        .filter(entry -> entry.getKey().contains("Id"))
        .map(Entry::getValue)
        .forEach(this::checkUUID);
  }

  private void checkUUID(Object o) {
    Pattern pattern = Pattern
        .compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/");
    if (pattern.matcher((CharSequence) o).matches()) {
      throw new ValidationException(new ErrorMessage("Invalid UUID format"));
    }
  }

}
