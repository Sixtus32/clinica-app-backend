package com.metaenlace.clinicaapplication.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDTO {
	private String name;
	private String surname;
	private String username;
	private String passcode;
	private String collegiateNum;
}
