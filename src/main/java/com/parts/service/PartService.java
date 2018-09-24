package com.parts.service;

import com.parts.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartService {
    Part getPartById(Integer id);
    void savePart(Part part);
    void updatePart(Integer id, String name, int quantity, boolean necessary);
    void deletePart(Integer id);
    Page<Part> findAllByOrderByNameAsc(Pageable pageable);
    Integer findCanCollectQuantity();
    Page<Part> findAllNecessary(Pageable pageable);
    Page<Part> findAllOptional(Pageable pageable);
    Page<Part> findAllLikeName(String name, Pageable pageable);
}
