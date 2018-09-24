package com.parts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.parts.model.Part;
import com.parts.repository.PartRepository;

@Service
public class PartServiceImpl implements PartService {

    private PartRepository repository;

    @Autowired
    public void setProductRepository(PartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Part getPartById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void savePart(Part part) {
        repository.save(part);
    }

    @Override
    public void updatePart(Integer id, String name, int quantity, boolean necessary) {
        Part updated = repository.findOne(id);
        updated.setName(name);
        updated.setNecessary(necessary);
        updated.setQuantity(quantity);
        repository.save(updated);
    }

    @Override
    public void deletePart(Integer id) {
        repository.delete(id);
    }

    @Override
    public Page<Part> findAllByOrderByNameAsc(Pageable pageable) {
        return repository.findAllByOrderByNameAsc(pageable);
    }

    @Override
    public Integer findCanCollectQuantity() {
        return repository.findCanCollectQuantity();
    }

    @Override
    public Page<Part> findAllNecessary(Pageable pageable) {
        return repository.findAllNecessary(pageable);
    }

    @Override
    public Page<Part> findAllOptional(Pageable pageable) {
        return repository.findAllOptional(pageable);
    }

    @Override
    public Page<Part> findAllLikeName(String name, Pageable pageable){
        return repository.findAllLikeName(name, pageable);
    }
}
