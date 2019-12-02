package pl.wedel.szzti.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.utils.UUIDHelper;

public class ItemSearchParameters {

  private static final String CODE = "code";
  private static final String SKIP_RENTED = "skipRented";
  private static final String SKIP_NOT_RENTED = "skipNotRented";

  private static final List<String> ALL_PARAMETERS = Collections
      .unmodifiableList(Arrays.asList(CODE, SKIP_RENTED, SKIP_NOT_RENTED));

  private HashMap<String, Object> queryParams;

  public ItemSearchParameters(Map<String, Object> queryMap) {
    queryMap.remove("sort");
    queryMap.remove("page");
    queryMap.remove("size");
    queryParams = new HashMap<>(queryMap);
    validate();
  }

  public boolean containsKey(String key) {
    return queryParams.containsKey(key);
  }

  public String getCode() {
    if (!queryParams.containsKey(CODE)) {
      return null;
    }
    return (String) queryParams.get(CODE);
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
