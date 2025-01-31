package com.eletronic.eletronic.controller;

import com.eletronic.eletronic.models.spareparts.SparePartsEntity;
import com.eletronic.eletronic.service.SparePartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class SpareParseController {

    @Autowired
    private SparePartService service;

    @GetMapping("/spareparts")
    public ResponseEntity<List<SparePartsEntity>> getAllSpareParts() {
        List<SparePartsEntity> spareParts = this.service.getAllSpareParts();
        return ResponseEntity.ok().body(spareParts);
    }

    @GetMapping("/sparepart/{id}")
    public ResponseEntity<SparePartsEntity> getSparePart(@PathVariable("id") Long id) {
        SparePartsEntity sparePart = this.service.getSparePartFindById(id);

        return ResponseEntity.ok().body(sparePart);
    }

    @PostMapping("/sparepart")
    public ResponseEntity<SparePartsEntity> postSparePart(@RequestBody SparePartsEntity data) {
        SparePartsEntity sparePart = this.service.postSparePart(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(sparePart);
    }

    @PutMapping("/sparepart/{id}")
    public ResponseEntity<SparePartsEntity> putSparePart(@PathVariable("id") Long id, @RequestBody SparePartsEntity data) {
        SparePartsEntity sparePart = this.service.putSparePart(id, data);

        return ResponseEntity.ok().body(sparePart);
    }





}
