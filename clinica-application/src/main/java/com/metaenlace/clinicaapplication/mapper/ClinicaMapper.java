package com.metaenlace.clinicaapplication.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.metaenlace.clinicaapplication.model.dtos.AppointmentDTO;
import com.metaenlace.clinicaapplication.model.dtos.DiagnosisDTO;
import com.metaenlace.clinicaapplication.model.dtos.DoctorDTO;
import com.metaenlace.clinicaapplication.model.dtos.PatientDTO;
import com.metaenlace.clinicaapplication.model.entity.AppointmentEntity;
import com.metaenlace.clinicaapplication.model.entity.DiagnosisEntity;
import com.metaenlace.clinicaapplication.model.entity.DoctorEntity;
import com.metaenlace.clinicaapplication.model.entity.PatientEntity;

@Mapper(componentModel = "spring")
public interface ClinicaMapper {

    // Mapeo de Paciente
    PatientDTO patientEntityToPatientDTO(PatientEntity patientEntity);
    List<PatientDTO> patientEntitiesToPatientDTOs(List<PatientEntity> patientEntities);
    
    // Mapeo de Médico	Unmapped target properties: "appointments, collegiateNum, patients".
    List<DoctorDTO> doctorEntitiesToDoctorDTOs(List<DoctorEntity> doctorEntities);
    DoctorDTO doctorEntityToDoctorDTO(DoctorEntity doctorEntity);

    // Mapeo de Diagnóstico
    DiagnosisDTO diagnosisEntityToDiagnosisDTO(DiagnosisEntity diagnosisEntity);
    List<DiagnosisDTO> diagnosisEntitiesToDiagnosisDTOs(List<DiagnosisEntity> diagnosisEntities);

    // Mapeo de Cita
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateTime", source = "dateTime")
    @Mapping(target = "appointmentReason", source = "appointmentReason")
    AppointmentDTO appointmentEntityToAppointmentDTO(AppointmentEntity appointmentEntity);

    List<AppointmentDTO> appointmentEntitiesToAppointmentDTOs(List<AppointmentEntity> appointmentEntities);
	
}



