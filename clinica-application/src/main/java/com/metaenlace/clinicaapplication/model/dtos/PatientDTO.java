package com.metaenlace.clinicaapplication.model.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PatientDTO extends UserDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(
			description = "Patient social security number",
			example = "1234567890")
	private String NSS;
	
	@Schema(
			description = "Patient card number",
			example = "A12345679")
	private String cardNum;
	
	@Schema(
			description = "Patient phone number",
			example = "+1234567890")
	private String phone;
	
	@Schema(
			description = "Patient home address",
			example = "123 Main Street, City, Country")
	private String address;
	
	
	@Schema(
		    description = "List of doctors associated with the patient",
		    implementation = DoctorDTO.class,
		    nullable = true
	)
	private List<DoctorDTO> doctors;
	
	@Schema(
		    description = "List of appointments made by the patient",
		    implementation = AppointmentDTO.class,
		    nullable = true
	)
	private List<AppointmentDTO> appointments;
	
	public PatientDTO(Long id, String name, String surname, String username, String passcode, String nSS, String cardNum,
			String phone, String address, List<DoctorDTO> doctors, List<AppointmentDTO> appointments) {
		super(id, name, surname, username, passcode);
		NSS = nSS;
		this.cardNum = cardNum;
		this.phone = phone;
		this.address = address;
		this.doctors = doctors;
		this.appointments = appointments;
	}
}
