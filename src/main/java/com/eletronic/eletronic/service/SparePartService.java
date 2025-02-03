package com.eletronic.eletronic.service;

import com.eletronic.eletronic.models.spareparts.SparePartsEntity;
import com.eletronic.eletronic.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {

    @Autowired
    private SparePartRepository repository;


    public List<SparePartsEntity> getAllSpareParts() {

        List<SparePartsEntity> spareParts = this.repository.findAll();

        return spareParts;
    }

    public SparePartsEntity getSparePartFindById(Long id) {

        Optional<SparePartsEntity> sparePart = this.repository.findById(id);

        return sparePart.orElseThrow(() -> new RuntimeException("Does not exist order service with id"));
    }

    public SparePartsEntity postSparePart(SparePartsEntity data) {

        this.repository.save(data);

        return data;
    }

    public SparePartsEntity putSparePart(Long id, SparePartsEntity data) {
        SparePartsEntity sparePart = this.getSparePartFindById(id);

        sparePart.setModel(data.getModel());
        sparePart.setOrderServicePart(data.getOrderServicePart());
        sparePart.setName(data.getName());
        sparePart.setStockQuantity(data.getStockQuantity());
        sparePart.setUnitPrice(data.getUnitPrice());

        this.repository.save(sparePart);

        return sparePart;

    }
}
