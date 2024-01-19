package rw.oredigital.com.v1.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.oredigital.com.v1.enums.EVisibility;
import rw.oredigital.com.v1.models.location.DataLocation;
import rw.oredigital.com.v1.models.location.DataLocationType;
import rw.oredigital.com.v1.models.location.Location;

import java.util.List;
import java.util.Optional;

public interface IDataLocationRepository extends JpaRepository<DataLocation, Integer> {
    Optional<DataLocation> findByCode(String code);
    Page<DataLocation> findAll(Pageable pageable);
    List<DataLocation> findAllByLocation(Location location);
    Page<DataLocation>findAllByLocation(Location location,Pageable pageable);
    List<DataLocation> findAllByType(DataLocationType type);
    Page<DataLocation> findAllByType(DataLocationType dataLocationType,Pageable pageable);
    List<DataLocation> findAllByManagedBy(DataLocation dataLocation);
    Page<DataLocation> findAllByManagedBy(DataLocation dataLocation,Pageable pageable);
    List<DataLocation> findAllByNeedsReview(boolean needsReview);
    Page<DataLocation> findAllByNeedsReview(boolean needsReview,Pageable pageable);
    Optional<DataLocation> findByIdAndVisibility(int id, EVisibility visibility);
    Optional<DataLocation> findByCodeAndVisibility(String code, EVisibility visibility);

    boolean existsByCodeAndVisibility(String code, EVisibility visibility);
    boolean existsByIdAndVisibility(int id, EVisibility visibility);

    long countAllByVisibility(EVisibility visibility);

}