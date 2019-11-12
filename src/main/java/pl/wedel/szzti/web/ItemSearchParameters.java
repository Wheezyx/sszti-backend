package pl.wedel.szzti.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;

public class ItemSearchParameters {

  private static final String CODE = "code";

  private static final List<String> ALL_PARAMETERS = Collections
      .unmodifiableList(Arrays.asList(CODE));

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
        .forEach(this::checkUuid);
  }

  private void checkUuid(Object o) {
    Pattern pattern = Pattern
        .compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/");
    if (pattern.matcher((CharSequence) o).matches()) {
      throw new ValidationException(new ErrorMessage("Invalid UUID format"));
    }
  }
}
