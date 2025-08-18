package com.radical.childmonitoring.panic.repository;

import com.radical.childmonitoring.panic.entity.Panic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PanicRepository extends JpaRepository<Panic, Long> {
    Optional<Panic> findByChildIdAndActiveTrue(Long childId); // You can add custom queries if needed later

    List<Panic> findByChildId(Long childId);


}
