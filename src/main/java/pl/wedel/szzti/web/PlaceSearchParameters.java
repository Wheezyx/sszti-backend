package pl.wedel.szzti.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.utils.UUIDHelper;

public class PlaceSearchParameters {

  private static final String NAME = "name";

  private static final List<String> ALL_PARAMETERS = Collections
      .unmodifiableList(Collections.singletonList(NAME));

  private HashMap<String, Object> queryParams;

  public PlaceSearchParameters(Map<String, Object> queryMap) {
    queryMap.remove("sort");
    queryMap.remove("page");
    queryMap.remove("size");
    queryParams = new HashMap<>(queryMap);
    validate();
  }

  public boolean containsKey(String key) {
    return queryParams.containsKey(key);
  }

  public String getPlaceName() {
    if (!queryParams.containsKey(NAME)) {
      return null;
    }
    return (String) queryParams.get(NAME);
  }

  private void validate() {
    if (!ALL_PARAMETERS.containsAll(queryParams.keySet())) {
      throw new ValidationException(
          new ErrorMessage("Invalid query parameters. Available are: " +
              ALL_PARAMETERS.toString()));
    }
    queryParams.entrySet().stream()
        .filter(entry -> entry.getKey().contains("Id"))
        .map(Entry::getValue)
        .forEach(UUIDHelper::validateUuidFormat);
  }
}