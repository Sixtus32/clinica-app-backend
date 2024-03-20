package com.metaenlace.clinicaapplication.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

import com.metaenlace.clinicaapplication.model.dtos.DiagnosisDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.DiagnosisRequestDTO;
import com.metaenlace.clinicaapplication.service.DiagnosisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    // Listar diagnósticos
    @Operation(summary = "List all diagnoses")
    @ApiResponse(responseCode = "200", description = "Retrieves a list of all diagnoses",
            content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DiagnosisDTO.class)))
            })
    @GetMapping("diagnoses")
    public ResponseEntity<List<DiagnosisDTO>> getAllDiagnoses() {
        List<DiagnosisDTO> diagnoses = diagnosisService.getAllDiagnoses();
        return new ResponseEntity<>(diagnoses, HttpStatus.OK);
    }

    // Crear diagnóstico
    @Operation(summary = "Create a diagnosis")
    @ApiResponse(responseCode = "201", description = "Create a new diagnosis",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DiagnosisDTO.class))
            })
    @PostMapping("diagnosis")
    public ResponseEntity<DiagnosisDTO> createDiagnosis(@RequestBody DiagnosisRequestDTO diagnosisRequestDTO) {
        DiagnosisDTO createdDiagnosis = diagnosisService.createDiagnosis(diagnosisRequestDTO);
        if (createdDiagnosis != null) {
            return new ResponseEntity<>(createdDiagnosis, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener diagnóstico por ID
    @Operation(summary = "Get a diagnosis by ID")
    @ApiResponse(responseCode = "200", description = "Retrieves a diagnosis by ID",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DiagnosisDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Diagnosis not found")
    @GetMapping("diagnosis/{id}")
    public ResponseEntity<DiagnosisDTO> getDiagnosisById(@NotNull @Valid @PathVariable Long id) {
        DiagnosisDTO diagnosis = diagnosisService.getDiagnosisById(id);
        if (diagnosis != null) {
            return new ResponseEntity<>(diagnosis, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar diagnóstico
    @Operation(summary = "Update a diagnosis by ID")
    @ApiResponse(responseCode = "200", description = "Diagnosis updated successfully",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DiagnosisDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Diagnosis not found")
    @PutMapping("diagnosis/{id}")
    public ResponseEntity<DiagnosisDTO> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisRequestDTO diagnosisRequestDTO) {
        // Verificar si el diagnóstico existe
        DiagnosisDTO existingDiagnosis = diagnosisService.getDiagnosisById(id);
        if (existingDiagnosis == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar los datos del diagnóstico
        DiagnosisDTO updatedDiagnosis = diagnosisService.updateDiagnosis(id, diagnosisRequestDTO);
        if (updatedDiagnosis != null) {
            return new ResponseEntity<>(updatedDiagnosis, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar diagnóstico
    @Operation(summary = "Delete a diagnosis by ID")
    @ApiResponse(responseCode = "204", description = "Diagnosis deleted successfully")
    @ApiResponse(responseCode = "404", description = "Diagnosis not found")
    @DeleteMapping("diagnosis/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable Long id) {
        // Verificar si el diagnóstico existe
        DiagnosisDTO existingDiagnosis = diagnosisService.getDiagnosisById(id);
        if (existingDiagnosis == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Eliminar el diagnóstico
        diagnosisService.deleteDiagnosis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
