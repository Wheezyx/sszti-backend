package pl.wedel.szzti.web;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.mapper.ItemCsvMapper;
import pl.wedel.szzti.mapper.ItemMapper;
import pl.wedel.szzti.service.ItemService;
import pl.wedel.szzti.validation.ItemDtoValidator;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

  private final ItemService itemService;

  private final ItemDtoValidator itemDtoValidator;

  private final ItemMapper itemMapper;

  private final ItemCsvMapper itemCsvMapper;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> search(@RequestParam Map<String, Object> params, Pageable pageable) {
    ItemSearchParameters itemSearchParameters = new ItemSearchParameters(params);
    return itemService.search(itemSearchParameters, pageable).map(itemMapper::toDto);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ItemDto getItem(@PathVariable("id") UUID itemId) {
    Item item = itemService.findById(itemId);
    return itemMapper.toDto(item);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ItemDto saveItem(@RequestBody ItemDto itemDto) {
    itemDtoValidator.validateItem(itemDto);
    Item item = itemMapper.fromDto(itemDto);
    return itemMapper.toDto(itemService.save(item));
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ItemDto updateItem(@PathVariable("id") UUID itemId, @RequestBody ItemDto itemDto) {
    itemDtoValidator.validateItem(itemId, itemDto);
    Item item = itemMapper.fromDto(itemDto);
    return itemMapper.toDto(itemService.update(item));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeItem(@PathVariable("id") UUID itemId) {
    itemService.deleteById(itemId);
  }

}
