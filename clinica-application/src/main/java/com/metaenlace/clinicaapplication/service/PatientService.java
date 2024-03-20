package com.metaenlace.clinicaapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaenlace.clinicaapplication.mapper.ClinicaMapper;
import com.metaenlace.clinicaapplication.model.dtos.PatientDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.PatientRequestDTO;
import com.metaenlace.clinicaapplication.model.entity.PatientEntity;
import com.metaenlace.clinicaapplication.repository.PatientRepository;


@Service
public class PatientService {

    @Autowired
    private ClinicaMapper clinicaMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        List<PatientEntity> patientEntities = (List<PatientEntity>) patientRepository.findAll();
        return clinicaMapper.patientEntitiesToPatientDTOs(patientEntities);
    }

    @Transactional
    public PatientDTO createPatient(PatientRequestDTO patientRequestDTO) {
        PatientEntity patientEntity = new PatientEntity();
        		patientEntity.setName(patientRequestDTO.getName());
        		patientEntity.setSurname(patientRequestDTO.getSurname());
        		patientEntity.setUsername(patientRequestDTO.getUsername());
        		patientEntity.setPasscode(patientRequestDTO.getPasscode());
                patientEntity.setNss(patientRequestDTO.getNSS());
                patientEntity.setCardNum(patientRequestDTO.getCardNum());
                patientEntity.setPhone(patientRequestDTO.getPhone());
                patientEntity.setAddress(patientRequestDTO.getAddress());
        patientEntity = patientRepository.save(patientEntity);
        return clinicaMapper.patientEntityToPatientDTO(patientEntity);
    }

    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long id) {
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(id);
        return patientEntityOptional.map(clinicaMapper::patientEntityToPatientDTO).orElse(null);
    }

    @Transactional
    public PatientDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(id);
        if (patientEntityOptional.isPresent()) {
            PatientEntity patientEntity = patientEntityOptional.get();
            patientEntity.setName(patientRequestDTO.getName());
            patientEntity.setSurname(patientRequestDTO.getSurname());
            patientEntity.setUsername(patientRequestDTO.getUsername());
            patientEntity.setPasscode(patientRequestDTO.getPasscode());
            patientEntity.setNss(patientRequestDTO.getNSS());
            patientEntity.setCardNum(patientRequestDTO.getCardNum());
            patientEntity.setPhone(patientRequestDTO.getPhone());
            patientEntity.setAddress(patientRequestDTO.getAddress());
            patientEntity = patientRepository.save(patientEntity);
            return clinicaMapper.patientEntityToPatientDTO(patientEntity);
        }
        return null;
    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
