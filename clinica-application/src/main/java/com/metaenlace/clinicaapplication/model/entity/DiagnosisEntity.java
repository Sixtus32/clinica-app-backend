package com.metaenlace.clinicaapplication.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIAGNOSIS_DB")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiagnosisEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "SPECIALIST_ASSESSMENT")
	private String specialistAssessment;
	
	@Column(name = "DISEASE")
	private String disease;
	
	@OneToOne
    @JoinColumn(name = "APPOINTMENT_ID")
    private AppointmentEntity appointment;
}
