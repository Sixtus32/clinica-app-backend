package com.metaenlace.clinicaapplication.model.dtos.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {
	private LocalDate dateTime;
	private String appointmentReason;
	private Long patientID;
	private Long doctorID;
	private Long DiagnosisID;
}
