package com.metaenlace.clinicaapplication.model.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDTO {
    
    @Schema(description = "Patient's name", example = "John")
    private String name;
    
    @Schema(description = "Patient's surname", example = "Doe")
    private String surname;
    
    @Schema(description = "Patient's username", example = "johndoe")
    private String username;
    
    @Schema(description = "Patient's passcode", example = "password123")
    private String passcode;
    
    @Schema(description = "Patient's social security number", example = "1234567890")
    private String NSS;
    
    @Schema(description = "Patient's card number", example = "A12345679")
    private String cardNum;
    
    @Schema(description = "Patient's phone number", example = "+1234567890")
    private String phone;
    
    @Schema(description = "Patient's home address", example = "123 Main Street, City, Country")
    private String address;
    
}
