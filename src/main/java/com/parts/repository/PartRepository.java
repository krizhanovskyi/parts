package com.parts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parts.model.Part;
import org.springframework.data.jpa.repository.Query;

public interface PartRepository extends JpaRepository<Part, Integer> {

    Page<Part> findAllByOrderByNameAsc(Pageable pageable);

    @Query("SELECT p FROM Part p WHERE p.name LIKE %?1%")
    Page<Part> findAllLikeName(String name, Pageable pageable);

    @Query("SELECT p FROM Part p WHERE necessary = 1 order by name")
    Page<Part> findAllNecessary(Pageable pageable);

    @Query("SELECT p FROM Part p WHERE necessary = 0 order by name")
    Page<Part> findAllOptional(Pageable pageable);

    @Query("SELECT MIN(p.quantity) FROM Part p WHERE necessary = 1")
    Integer findCanCollectQuantity();
}
