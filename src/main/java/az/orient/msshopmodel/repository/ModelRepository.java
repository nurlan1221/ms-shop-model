package az.orient.msshopmodel.repository;

import az.orient.msshopmodel.dto.RespModel;
import az.orient.msshopmodel.entity.ModelEntity;
import az.orient.msshopmodel.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    List<ModelEntity> findAllByStatus(Status status);

    Optional<ModelEntity> findByIdAndStatus(Long id, Status status);
    List<ModelEntity> findByBrandId(Long brandId);


}
