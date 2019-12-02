package pl.wedel.szzti.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.Rental;
import pl.wedel.szzti.domain.Renter;
import pl.wedel.szzti.web.RentalSearchParameters;

@Service
public class ReportService {

  @Value(value = "classpath:reports/damage_protocol.jrxml")
  private Resource damageProtocolReport;

  @Value(value = "classpath:reports/rental-renters-report.jrxml")
  private Resource renterRentalReport;

  private final ItemService itemService;
  private final RentalService rentalService;

  public ReportService(ItemService itemService, RentalService rentalService) {
    this.itemService = itemService;
    this.rentalService = rentalService;
  }

  public JasperPrint generateItemDamageReport(UUID itemId) throws JRException, IOException {
    Item item = itemService.findById(itemId);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("item", item);
    parameters.put("date", LocalDate.now().toString());

    return compileAndFillReport(new JREmptyDataSource(), parameters,
        damageProtocolReport.getInputStream());
  }

  public JasperPrint generateRenterRentalReport(UUID renterId) throws JRException, IOException {
    Map<String, Object> paramsMap = new HashMap<>();
    paramsMap.put("renterId", renterId.toString());
    RentalSearchParameters params = new RentalSearchParameters(paramsMap);

    Page<Rental> rentals = rentalService.search(params, PageRequest.of(0, Integer.MAX_VALUE));
    List<Item> rentedItems = rentals.stream()
        .map(Rental::getItem)
        .collect(Collectors.toList());

    Rental firstRental = rentals.stream().findFirst().orElse(null);
    Renter renter = firstRental != null ? firstRental.getRenter() : null;

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("renter", renter);

    return compileAndFillReport(new JRBeanCollectionDataSource(rentedItems), parameters,
        renterRentalReport.getInputStream());
  }

  private JasperPrint compileAndFillReport(JRRewindableDataSource dataSource,
      Map<String, Object> parameters,
      InputStream inputStream)
      throws JRException {
    //TODO LOAD COMPILED REPORT INSTEAD OF COMPILING IT EVERY TIME WHEN USED.
    JasperReport jasperReport
        = JasperCompileManager.compileReport(inputStream);
    return JasperFillManager
        .fillReport(jasperReport, parameters, dataSource);
  }

}
