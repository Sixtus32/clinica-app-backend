package com.metaenlace.clinicaapplication.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisRequestDTO {
	private String specialistAssessment;
	private String disease;
}
