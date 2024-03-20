package com.metaenlace.clinicaapplication.model.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "APPOINTMENT_DB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DATATIME")
	private LocalDate dateTime;
	
	@Column(name = "APPOINTMENT_REASON")
	private String appointmentReason;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	@JoinColumn(name = "DIAGNOSIS_ID", nullable = true)
	private DiagnosisEntity diagnosis;
}
