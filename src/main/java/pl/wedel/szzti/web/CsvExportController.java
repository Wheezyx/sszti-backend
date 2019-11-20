package pl.wedel.szzti.web;

import static com.opencsv.ICSVWriter.DEFAULT_SEPARATOR;
import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wedel.szzti.dto.ErrorMessage;
import pl.wedel.szzti.dto.ItemCsvDto;
import pl.wedel.szzti.exception.ValidationException;
import pl.wedel.szzti.mapper.CustomOpenCsvMappingStrategy;
import pl.wedel.szzti.mapper.ItemCsvMapper;
import pl.wedel.szzti.service.ItemService;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class CsvExportController {

  private final ItemService itemService;

  private final ItemCsvMapper itemCsvMapper;

  @PostMapping(value = "/items/export")
  public void exportItems(@RequestParam String format, @RequestBody ArrayList<UUID> itemIds,
      HttpServletResponse response)

      throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    if (!format.equalsIgnoreCase("csv")) {
      throw new ValidationException(new ErrorMessage("Not supported format."));
    }
    String filename = "items.csv";
    response.setContentType("text/csv");
    response.setCharacterEncoding("utf-8");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + filename + "\"");

    List<ItemCsvDto> itemsDto = itemService.findAllByIds(itemIds).stream()
        .map(itemCsvMapper::toDto)
        .collect(Collectors.toList());

    CustomOpenCsvMappingStrategy<ItemCsvDto> mappingStrategy = new CustomOpenCsvMappingStrategy<>();
    mappingStrategy.setType(ItemCsvDto.class);
    StatefulBeanToCsv<ItemCsvDto> writer = new StatefulBeanToCsvBuilder<ItemCsvDto>(
        response.getWriter())
        .withMappingStrategy(mappingStrategy)
        .withQuotechar(NO_QUOTE_CHARACTER)
        .withSeparator(DEFAULT_SEPARATOR)
        .withOrderedResults(true)
        .build();

    writer.write(itemsDto);
  }

}
