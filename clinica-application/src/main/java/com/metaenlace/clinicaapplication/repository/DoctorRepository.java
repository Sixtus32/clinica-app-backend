package com.metaenlace.clinicaapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metaenlace.clinicaapplication.model.entity.DoctorEntity;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {}
