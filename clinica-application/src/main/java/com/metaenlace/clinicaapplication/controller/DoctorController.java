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

import com.metaenlace.clinicaapplication.model.dtos.DoctorDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.DoctorRequestDTO;
import com.metaenlace.clinicaapplication.service.DoctorService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Listar médicos
    @Operation(summary = "List all doctors")
    @ApiResponse(responseCode = "200", description = "Retrieves a list of all doctors",
            content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DoctorDTO.class)))
            })
    @GetMapping("doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Crear médico
    @Operation(summary = "Register a Doctor")
    @ApiResponse(responseCode = "201", description = "Register a new Doctor",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorDTO.class))
            })
    @PostMapping("doctor")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorRequestDTO);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    // Obtener médico por ID
    @Operation(summary = "Get a doctor by ID")
    @ApiResponse(responseCode = "200", description = "Retrieves a doctor by ID",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Doctor not found")
    @GetMapping("doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@NotNull @Valid @PathVariable Long id) {
        DoctorDTO doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar médico
    @Operation(summary = "Update a doctor by ID")
    @ApiResponse(responseCode = "200", description = "Doctor updated successfully",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Doctor not found")
    @PutMapping("doctor/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDTO doctorRequestDTO) {
        // Verificar si el médico existe
        DoctorDTO existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar los datos del médico
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorRequestDTO);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    // Eliminar médico
    @Operation(summary = "Delete a doctor by ID")
    @ApiResponse(responseCode = "204", description = "Doctor deleted successfully")
    @ApiResponse(responseCode = "404", description = "Doctor not found")
    @DeleteMapping("doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        // Verificar si el médico existe
        DoctorDTO existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Eliminar el médico
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
