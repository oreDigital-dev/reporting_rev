package rw.oredigital.com.v1.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.oredigital.com.v1.enums.EVisibility;
import rw.oredigital.com.v1.models.location.DataLocationType;

import java.util.List;
import java.util.Optional;

public interface IDataLocationTypeRepository extends JpaRepository<DataLocationType,Integer> {
    Optional<DataLocationType> findByCode(String code);
    Page<DataLocationType> findAll(Pageable pageable);
    List<DataLocationType> findAllByOrderNumber(String orderNumber);
    Page<DataLocationType> findAllByOrderNumber(String orderNumber,Pageable pageable);
    boolean existsByCodeAndVisibility(String code, EVisibility visibility);
    boolean existsByIdAndVisibility(int id, EVisibility visibility);

}