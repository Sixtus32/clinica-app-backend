package com.metaenlace.clinicaapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaenlace.clinicaapplication.mapper.ClinicaMapper;
import com.metaenlace.clinicaapplication.model.dtos.DiagnosisDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.DiagnosisRequestDTO;
import com.metaenlace.clinicaapplication.model.entity.DiagnosisEntity;
import com.metaenlace.clinicaapplication.repository.DiagnosisRepository;

@Service
public class DiagnosisService {

    @Autowired
    private ClinicaMapper clinicaMapper;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Transactional(readOnly = true)
    public List<DiagnosisDTO> getAllDiagnoses() {
        List<DiagnosisEntity> diagnosisEntities = (List<DiagnosisEntity>) diagnosisRepository.findAll();
        return clinicaMapper.diagnosisEntitiesToDiagnosisDTOs(diagnosisEntities);
    }

    @Transactional
    public DiagnosisDTO createDiagnosis(DiagnosisRequestDTO diagnosisRequestDTO) {
        DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
        diagnosisEntity.setSpecialistAssessment(diagnosisRequestDTO.getSpecialistAssessment());
        diagnosisEntity.setDisease(diagnosisRequestDTO.getDisease());

        // Guardar la entidad de diagnóstico y devolver el DTO mapeado
        diagnosisEntity = diagnosisRepository.save(diagnosisEntity);
        return clinicaMapper.diagnosisEntityToDiagnosisDTO(diagnosisEntity);
    }

    @Transactional(readOnly = true)
    public DiagnosisDTO getDiagnosisById(Long id) {
        Optional<DiagnosisEntity> diagnosisEntityOptional = diagnosisRepository.findById(id);
        return diagnosisEntityOptional.map(clinicaMapper::diagnosisEntityToDiagnosisDTO).orElse(null);
    }

    @Transactional
    public DiagnosisDTO updateDiagnosis(Long id, DiagnosisRequestDTO diagnosisRequestDTO) {
        Optional<DiagnosisEntity> diagnosisEntityOptional = diagnosisRepository.findById(id);
        if (diagnosisEntityOptional.isPresent()) {
            DiagnosisEntity diagnosisEntity = diagnosisEntityOptional.get();
            diagnosisEntity.setSpecialistAssessment(diagnosisRequestDTO.getSpecialistAssessment());
            diagnosisEntity.setDisease(diagnosisRequestDTO.getDisease());
            diagnosisEntity = diagnosisRepository.save(diagnosisEntity);
            return clinicaMapper.diagnosisEntityToDiagnosisDTO(diagnosisEntity);
        }
        return null;
    }

    @Transactional
    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }
}
