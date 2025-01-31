package com.eletronic.eletronic.repository;

import com.eletronic.eletronic.models.spareparts.SparePartsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparePartRepository extends JpaRepository<SparePartsEntity, Long> {

}
