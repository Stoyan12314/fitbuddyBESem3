package org.example.persistence;

import org.example.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPARequestRepository  extends JpaRepository<RequestEntity, Long> {
    List<RequestEntity> findRequestEntitiesByUserEntity_Id(Long id);
}
