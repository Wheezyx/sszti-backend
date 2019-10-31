package pl.wedel.szzti.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import pl.wedel.szzti.exception.ValidationException;

public class RentalSearchParametersTest {

  private Map<String, Object> queryParams;

  @Before
  public void setUp() {
    queryParams = new HashMap<>();
  }

  @Test
  public void shouldGetItemIdFromParameters() {
    UUID id = UUID.randomUUID();
    queryParams.put("itemId", id.toString());

    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertEquals(id, searchParameters.getItemId());
  }

  @Test
  public void shouldGetNullWhenMapHasNoItemIdProperty() {
    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertNull(searchParameters.getItemId());
  }

  @Test
  public void shouldGetPlaceIdFromParameters() {
    UUID id = UUID.randomUUID();
    queryParams.put("placeId", id.toString());

    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertEquals(id, searchParameters.getPlaceId());
  }

  @Test
  public void shouldGetNullWhenMapHasNoPlaceIdProperty() {
    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertNull(searchParameters.getPlaceId());
  }

  @Test
  public void shouldGetRenterIdFromParameters() {
    UUID id = UUID.randomUUID();
    queryParams.put("renterId", id.toString());

    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertEquals(id, searchParameters.getRenterId());
  }

  @Test
  public void shouldGetNullWhenMapHasNoRenterIdProperty() {
    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);

    assertNull(searchParameters.getRenterId());
  }

  @Test(expected = ValidationException.class)
  public void shouldThrowExceptionIfUnkownParameterIsPresent() {
    queryParams.put("test", null);

    RentalSearchParameters searchParameters = new RentalSearchParameters(queryParams);
  }
}
