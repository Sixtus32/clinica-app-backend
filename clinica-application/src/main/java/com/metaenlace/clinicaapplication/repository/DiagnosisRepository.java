package com.metaenlace.clinicaapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metaenlace.clinicaapplication.model.entity.DiagnosisEntity;

@Repository
public interface DiagnosisRepository extends CrudRepository<DiagnosisEntity, Long> {}
