package com.metaenlace.clinicaapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metaenlace.clinicaapplication.model.entity.PatientEntity;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, Long>{}
