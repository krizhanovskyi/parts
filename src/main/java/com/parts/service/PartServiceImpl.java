package com.parts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.parts.model.Part;
import com.parts.repository.PartRepository;

import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    private PartRepository repository;

    @Autowired
    public void setProductRepository(PartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Part> getPartById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void savePart(Part part) {
        repository.save(part);
    }

    @Override
    public void updatePart(Integer id, String name, int quantity, boolean necessary) {
        Optional<Part> updated = repository.findById(id);
        if(updated.isPresent()){
            Part getUpdated = updated.get();
            getUpdated.setName(name);
            getUpdated.setNecessary(necessary);
            getUpdated.setQuantity(quantity);
            repository.save(getUpdated);
        }
    }

    @Override
    public void deletePart(Integer id) {
        repository.deleteById(id);
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
