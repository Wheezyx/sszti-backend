package pl.wedel.szzti.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.utils.UUIDHelper;

public class HostSearchParameters {

  public static final String NAME = "name";
  public static final String PLACE = "place";
  public static final String INVENTORY_CODE = "inventoryCode";
  public static final String IP = "ip";
  public static final String MAC = "mac";
  public static final String PATCH_PANEL = "patchPanel";
  public static final String CONNECTION_NAME = "connectionName";
  public static final String CONNECTION_NUMBER = "connectionNumber";

  private static final List<String> ALL_PARAMETERS = List
      .of(NAME, PLACE, INVENTORY_CODE, IP, MAC, PATCH_PANEL, CONNECTION_NAME, CONNECTION_NUMBER);

  @Getter
  private HashMap<String, Object> queryParams;

  public HostSearchParameters(Map<String, Object> queryMap) {
    queryMap.remove("sort");
    queryMap.remove("page");
    queryMap.remove("size");
    queryParams = new HashMap<>(queryMap);
    validate();
  }

  public boolean containsKey(String key) {
    return queryParams.containsKey(key);
  }

  public String getValue(String key) {
    if (!containsKey(key)) {
      return null;
    }
    return (String) queryParams.get(key);
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
