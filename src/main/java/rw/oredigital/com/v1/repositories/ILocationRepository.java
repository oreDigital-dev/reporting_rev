package rw.oredigital.com.v1.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.oredigital.com.v1.models.location.Location;
import rw.oredigital.com.v1.models.location.LocationLevel;
import java.util.List;
import java.util.Optional;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByCode(String code);

    Page<Location> findAll(Pageable pageable);
    boolean existsByCode(String code);
    List<Location> findAllByParent(Location location);
    Page<Location> findAllByParent(Location location, Pageable pageable);
    List<Location> findAllByLevel(LocationLevel locationLevel);
    Page<Location> findAllByLevel(LocationLevel locationLevel, Pageable pageable);
    List<Location> findAllByLevelAndParent(LocationLevel locationLevel, Location location);

//    public Location findByCode(String code);

}
