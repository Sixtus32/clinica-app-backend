package com.metaenlace.clinicaapplication.model.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"id"})
public class AppointmentDTO {
	private Long id;
	private LocalDate dateTime;
	private String appointmentReason;
	private PatientDTO patient;
	private DoctorDTO doctor;
	private DiagnosisDTO diagnosis;
}
