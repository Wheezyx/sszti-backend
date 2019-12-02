package pl.wedel.szzti.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wedel.szzti.service.ReportService;

@Controller
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @PostMapping(value = "/items/{id}/damage-report")
  public void exportItemDamageReport(@PathVariable("id") UUID itemId, HttpServletResponse response)
      throws IOException, JRException {

    setResponseHeaders(response, "filename=\"protokol-uszkodzenia-sprzetu.pdf\"");

    JasperPrint jasperPrint = reportService.generateItemDamageReport(itemId);
    setJasperReportToResponse(response, jasperPrint);
  }

  @PostMapping(value = "/renters/{id}/rentals-report")
  public void exportRenterRentalReport(@PathVariable("id") UUID renterId,
      HttpServletResponse response)
      throws IOException, JRException {

    setResponseHeaders(response, "filename=\"formularz-przekazania-sprzetu.pdf\"");

    JasperPrint jasperPrint = reportService.generateRenterRentalReport(renterId);
    setJasperReportToResponse(response, jasperPrint);
  }

  private void setJasperReportToResponse(HttpServletResponse response, JasperPrint jasperPrint)
      throws IOException, JRException {
    OutputStream out = response.getOutputStream();
    JRPdfExporter exporter = new JRPdfExporter();
    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
    configuration.setMetadataAuthor("SZZTI");
    exporter.setConfiguration(configuration);
    exporter.exportReport();
  }

  private void setResponseHeaders(HttpServletResponse response, String s) {
    response.setContentType("application/x-download");
    response.setHeader("Content-Disposition", String.format("attachment; "
        + s));
  }


}
