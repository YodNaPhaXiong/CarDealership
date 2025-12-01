package dealership.repository;

import dealership.entities.dealer.Dealer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {
	
	List<Dealer> findByNameContainingAndLocation(String name, String location);

    List<Dealer> findByNameContaining(String name);

    List<Dealer> findByLocation(String location);
}
