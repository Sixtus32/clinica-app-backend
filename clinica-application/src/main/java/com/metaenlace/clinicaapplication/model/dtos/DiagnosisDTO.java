package com.metaenlace.clinicaapplication.model.dtos;

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
public class DiagnosisDTO {
	private Long id;
	private String specialistAssessment;
	private String disease;
}
