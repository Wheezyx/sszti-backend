package pl.wedel.szzti.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
public final class RentalSearchParameters {

  private static final String ID = "id";
  private static final String ITEM_ID = "itemId";
  private static final String PLACE_ID = "placeId";
  private static final String RENTER_ID = "renterId";

  private static final List<String> ALL_PARAMETERS = Collections
      .unmodifiableList(Arrays.asList(ID, ITEM_ID, PLACE_ID, RENTER_ID));

  private MultiValueMap<String, Object> queryParams;

  public RentalSearchParameters(MultiValueMap<String, Object> queryMap) {
    queryParams = new LinkedMultiValueMap<>(queryMap);
    //TODO ADD VALIDATE METHOD
  }

  public boolean containsKey(String key) {
    return queryParams.containsKey(key);
  }

  public String getId() {
    return (String) queryParams.getFirst(ID);
  }
}
