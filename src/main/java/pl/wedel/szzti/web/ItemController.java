package pl.wedel.szzti.web;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.dto.ItemDto;
import pl.wedel.szzti.mapper.ItemMapper;
import pl.wedel.szzti.service.ItemService;
import pl.wedel.szzti.validation.ItemDtoValidator;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class  ItemController {

  private final ItemService itemService;

  private final ItemDtoValidator itemDtoValidator;

  private final ItemMapper itemMapper;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ItemDto> getItems(Pageable pageable) {
    return itemService.findAll(pageable).map(itemMapper::toDto);
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
}
