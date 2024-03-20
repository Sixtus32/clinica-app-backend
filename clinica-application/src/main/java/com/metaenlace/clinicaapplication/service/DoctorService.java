package com.metaenlace.clinicaapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaenlace.clinicaapplication.mapper.ClinicaMapper;
import com.metaenlace.clinicaapplication.model.dtos.DoctorDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.DoctorRequestDTO;
import com.metaenlace.clinicaapplication.model.entity.DoctorEntity;
import com.metaenlace.clinicaapplication.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private ClinicaMapper clinicaMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorDTO> getAllDoctors() {
        List<DoctorEntity> doctorEntities = (List<DoctorEntity>) doctorRepository.findAll();
        return clinicaMapper.doctorEntitiesToDoctorDTOs(doctorEntities);
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName(doctorRequestDTO.getName());
        doctorEntity.setSurname(doctorRequestDTO.getSurname());
        doctorEntity.setUsername(doctorRequestDTO.getUsername());
        doctorEntity.setPasscode(doctorRequestDTO.getPasscode());
        doctorEntity.setCollegiateNum(doctorRequestDTO.getCollegiateNum());
        doctorEntity = doctorRepository.save(doctorEntity);
        return clinicaMapper.doctorEntityToDoctorDTO(doctorEntity);
    }

    @Transactional(readOnly = true)
    public DoctorDTO getDoctorById(Long id) {
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findById(id);
        return doctorEntityOptional.map(clinicaMapper::doctorEntityToDoctorDTO).orElse(null);
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO) {
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findById(id);
        if (doctorEntityOptional.isPresent()) {
            DoctorEntity doctorEntity = doctorEntityOptional.get();
            doctorEntity.setName(doctorRequestDTO.getName());
            doctorEntity.setSurname(doctorRequestDTO.getSurname());
            doctorEntity.setUsername(doctorRequestDTO.getUsername());
            doctorEntity.setPasscode(doctorRequestDTO.getPasscode());
            doctorEntity.setCollegiateNum(doctorRequestDTO.getCollegiateNum());
            doctorEntity = doctorRepository.save(doctorEntity);
            return clinicaMapper.doctorEntityToDoctorDTO(doctorEntity);
        }
        return null;
    }

    @Transactional
    public void deleteDoctor(Long id) {
        this.doctorRepository.deleteById(id);
    }
}
