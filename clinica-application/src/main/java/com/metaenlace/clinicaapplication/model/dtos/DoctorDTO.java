package com.metaenlace.clinicaapplication.model.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"id"})
public class DoctorDTO extends UserDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String collegiateNum;
	private List<PatientDTO> patients;
	private List<AppointmentDTO> appointments;
	public DoctorDTO(Long id, String name, String surname, String username, String passcode, String collegiateNum,
			List<PatientDTO> patients, List<AppointmentDTO> appointments) {
		super(id, name, surname, username, passcode);
		this.collegiateNum = collegiateNum;
		this.patients = patients;
		this.appointments = appointments;
	}
}
