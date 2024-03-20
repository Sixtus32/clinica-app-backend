package com.metaenlace.clinicaapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaenlace.clinicaapplication.model.dtos.PatientDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.PatientRequestDTO;
import com.metaenlace.clinicaapplication.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    // Listar pacientes
    @Operation(summary = "List all patients")
    @ApiResponse(responseCode = "200", description = "Retrieves a list of all patients",
        content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientDTO.class)))
        })
    @GetMapping("patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    
    // Crear paciente
    @Operation(summary = "Register a Patient")
    @ApiResponse(responseCode = "201", description = "Register a new Patient",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))
        })
    @PostMapping("patient")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }
    
    // Obtener paciente por id
    @Operation(summary = "Get a patient by ID")
    @ApiResponse(responseCode = "200", description = "Retrieves a patient by ID",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))
        })
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @GetMapping("patient/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Actualizar paciente
    @Operation(summary = "Update a patient by ID")
    @ApiResponse(responseCode = "200", description = "Patient updated successfully",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))
        })
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @PutMapping("patient/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        // Verificar si el paciente existe
        PatientDTO existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Actualizar los datos del paciente
        PatientDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
    
    // Eliminar paciente
    @Operation(summary = "Delete a patient by ID")
    @ApiResponse(responseCode = "204", description = "Patient deleted successfully")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @DeleteMapping("patient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        // Verificar si el paciente existe
        PatientDTO existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Eliminar el paciente
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

