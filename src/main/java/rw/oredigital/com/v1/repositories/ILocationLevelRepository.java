package rw.oredigital.com.v1.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.oredigital.com.v1.models.location.LocationLevel;

import java.util.Optional;

public interface ILocationLevelRepository extends JpaRepository<LocationLevel,Integer> {

    Optional<LocationLevel> findByCode(String code);
    Page<LocationLevel> findAll(Pageable pageable);

    boolean existsByCode(String code);


}
