package pl.wedel.szzti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Slf4j
public class DemoDataInitializer implements CommandLineRunner {

  @Value(value = "classpath:demo-data/items.csv")
  private Resource itemsResource;

  @Value(value = "classpath:demo-data/rentals.csv")
  private Resource rentalsResource;

  @Value(value = "classpath:demo-data/places.csv")
  private Resource placesResource;

  @Value(value = "classpath:demo-data/renters.csv")
  private Resource rentersResource;

  @Value(value = "classpath:demo-data/users.csv")
  private Resource usersResource;

  @Value(value = "classpath:demo-data/user_authorities.csv")
  private Resource userAuthoritiesResource;

  @Value(value = "classpath:demo-data/user_authorities_assignment.csv")
  private Resource userAuthoritiesAssignmentResource;

  @Value(value = "classpath:demo-data/hosts.csv")
  private Resource hostsResource;

  @Value(value = "classpath:demo-data/connection_interfaces.csv")
  private Resource connectionInterfacesResource;

  private final JdbcTemplate jdbcTemplate;

  public DemoDataInitializer(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    insertIntoDatabase("items", itemsResource);
    insertIntoDatabase("places", placesResource);
    insertIntoDatabase("renters", rentersResource);
    insertIntoDatabase("rentals", rentalsResource);
    insertIntoDatabase("users", usersResource);
    insertIntoDatabase("authorities", userAuthoritiesResource);
    insertIntoDatabase("users_authorities", userAuthoritiesAssignmentResource);
    insertIntoDatabase("hosts", hostsResource);
    insertIntoDatabase("connection_interfaces", connectionInterfacesResource);
  }

  private void insertIntoDatabase(String column, Resource resource) throws IOException {
    log.info("Inserting data for table: {} from resource: {}", column, resource.getDescription());
    try {
      String headers;
      List<String> data = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(resource.getInputStream()))) {
        String line;
        headers = br.readLine();
        while ((line = br.readLine()) != null) {
          data.add(line);
        }
      }
      String query = String.format("INSERT INTO %s (%s) VALUES %s", column, headers, data.stream()
          .map(line -> "(" + line + ")").collect(Collectors.joining(",")));
      jdbcTemplate.execute(query);
    } catch (RuntimeException e) {
      log.error("Error while inserting data:" + e.getMessage());
    }
  }


}

