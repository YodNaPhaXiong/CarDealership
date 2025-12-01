package dealership;

import dealership.entities.dealer.Dealer;
import dealership.repository.DealerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DealerRepository dealerRepository) {
        return args -> {
            Dealer dealer1 = new Dealer("dealer001");
            Dealer dealer2 = new Dealer("dealer002");
            Dealer dealer3 = new Dealer("dealer003");


            log.info("Preloading " + dealerRepository.save(dealer1));
            log.info("Preloading " + dealerRepository.save(dealer2));
            log.info("Preloading " + dealerRepository.save(dealer3));

        };

    }
}
