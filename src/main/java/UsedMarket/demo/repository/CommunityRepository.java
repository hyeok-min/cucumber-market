package UsedMarket.demo.repository;

import UsedMarket.demo.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {

}
