package pl.wedel.szzti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.wedel.szzti.domain.GenericName;
import pl.wedel.szzti.domain.InsideType;
import pl.wedel.szzti.domain.Item;
import pl.wedel.szzti.domain.ItemType;
import pl.wedel.szzti.domain.User;
import pl.wedel.szzti.repository.GenericNameRepository;
import pl.wedel.szzti.repository.ItemRepository;
import pl.wedel.szzti.repository.UserRepository;

@SpringBootApplication
public class SzztiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SzztiApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(UserRepository userRepository,
      PasswordEncoder passwordEncoder, ItemRepository itemRepository,
      GenericNameRepository genericNameRepository) {
    return args -> {
      userRepository
          .save(new User("test", passwordEncoder.encode("test"), true, true, true, true, null));
      Item item = new Item();
      GenericName genericName = new GenericName("TEST");
      genericNameRepository.save(genericName);
      item.setInventoryCode("55012312");
      item.setInsideType(InsideType.NN);
      item.setGenericName(genericName);
      item.setItemType(ItemType.EQUIPMENT);
      itemRepository.save(item);
    };
  }
}


