package com.metaenlace.clinicaapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaenlace.clinicaapplication.mapper.ClinicaMapper;
import com.metaenlace.clinicaapplication.model.dtos.AppointmentDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.AppointmentRequestDTO;
import com.metaenlace.clinicaapplication.model.entity.AppointmentEntity;
import com.metaenlace.clinicaapplication.model.entity.DiagnosisEntity;
import com.metaenlace.clinicaapplication.model.entity.DoctorEntity;
import com.metaenlace.clinicaapplication.model.entity.PatientEntity;
import com.metaenlace.clinicaapplication.repository.AppointmentRepository;
import com.metaenlace.clinicaapplication.repository.DoctorRepository;
import com.metaenlace.clinicaapplication.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private ClinicaMapper clinicaMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAllAppointments() {
        List<AppointmentEntity> appointmentEntities = (List<AppointmentEntity>) appointmentRepository.findAll();
        return clinicaMapper.appointmentEntitiesToAppointmentDTOs(appointmentEntities);
    }

    @Transactional
    public AppointmentDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDateTime(appointmentRequestDTO.getDateTime());
        appointmentEntity.setAppointmentReason(appointmentRequestDTO.getAppointmentReason());
        
        // Obtener el paciente y el doctor asociados
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(appointmentRequestDTO.getPatientID());
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findById(appointmentRequestDTO.getDoctorID());
        
        if (patientEntityOptional.isPresent() && doctorEntityOptional.isPresent()) {
            appointmentEntity.setPatient(patientEntityOptional.get());
            appointmentEntity.setDoctor(doctorEntityOptional.get());
            
            // Asignar diagnóstico si está presente
            if (appointmentRequestDTO.getDiagnosisID() != null) {
                DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
                diagnosisEntity.setId(appointmentRequestDTO.getDiagnosisID());
                appointmentEntity.setDiagnosis(diagnosisEntity);
            }

            appointmentEntity = appointmentRepository.save(appointmentEntity);
            return clinicaMapper.appointmentEntityToAppointmentDTO(appointmentEntity);
        } else {
            // Si no se encuentran el paciente o el médico, no se puede crear la cita
            return null;
        }
    }

    @Transactional(readOnly = true)
    public AppointmentDTO getAppointmentById(Long id) {
        Optional<AppointmentEntity> appointmentEntityOptional = appointmentRepository.findById(id);
        return appointmentEntityOptional.map(clinicaMapper::appointmentEntityToAppointmentDTO).orElse(null);
    }

    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentRequestDTO appointmentRequestDTO) {
        Optional<AppointmentEntity> appointmentEntityOptional = appointmentRepository.findById(id);
        if (appointmentEntityOptional.isPresent()) {
            AppointmentEntity appointmentEntity = appointmentEntityOptional.get();
            appointmentEntity.setDateTime(appointmentRequestDTO.getDateTime());
            appointmentEntity.setAppointmentReason(appointmentRequestDTO.getAppointmentReason());
            
            // Obtener el paciente y el doctor asociados
            Optional<PatientEntity> patientEntityOptional = patientRepository.findById(appointmentRequestDTO.getPatientID());
            Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findById(appointmentRequestDTO.getDoctorID());
            
            if (patientEntityOptional.isPresent() && doctorEntityOptional.isPresent()) {
                appointmentEntity.setPatient(patientEntityOptional.get());
                appointmentEntity.setDoctor(doctorEntityOptional.get());
                
                // Asignar diagnóstico si está presente
                if (appointmentRequestDTO.getDiagnosisID() != null) {
                    DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
                    diagnosisEntity.setId(appointmentRequestDTO.getDiagnosisID());
                    appointmentEntity.setDiagnosis(diagnosisEntity);
                }

                appointmentEntity = appointmentRepository.save(appointmentEntity);
                return clinicaMapper.appointmentEntityToAppointmentDTO(appointmentEntity);
            } else {
                // Si no se encuentran el paciente o el médico, no se puede actualizar la cita
                return null;
            }
        }
        return null;
    }

    @Transactional
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
