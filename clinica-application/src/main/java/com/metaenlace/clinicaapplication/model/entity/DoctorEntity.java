package com.metaenlace.clinicaapplication.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "DOCTOR_DB")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorEntity extends UserEntity {
	@Column(name = "COLLEGIATE_NUM")
	private String collegiateNum;
	
	@ManyToMany
	@JoinTable(name = "doctor_pacient",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID"))
	private List<PatientEntity> patients;
	
	@OneToMany(mappedBy = "doctor")
	private List<AppointmentEntity> appointments;
	
	public DoctorEntity(Long id, String name, String surname, String username, String passcode,String collegiateNum, List<PatientEntity> patients, List<AppointmentEntity> appointments) {
		super(id,name,surname,username,passcode);
		this.collegiateNum = collegiateNum;
		this.patients = patients;
		this.appointments = appointments;
	}
	
}
