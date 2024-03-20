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

import com.metaenlace.clinicaapplication.model.dtos.AppointmentDTO;
import com.metaenlace.clinicaapplication.model.dtos.request.AppointmentRequestDTO;
import com.metaenlace.clinicaapplication.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Listar citas
    @Operation(summary = "List all appointments")
    @ApiResponse(responseCode = "200", description = "Retrieves a list of all appointments",
            content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentDTO.class)))
            })
    @GetMapping("appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Crear cita
    @Operation(summary = "Create an appointment")
    @ApiResponse(responseCode = "201", description = "Create a new appointment",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))
            })
    @PostMapping("appointment")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentDTO createdAppointment = appointmentService.createAppointment(appointmentRequestDTO);
        if (createdAppointment != null) {
            return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener cita por ID
    @Operation(summary = "Get an appointment by ID")
    @ApiResponse(responseCode = "200", description = "Retrieves an appointment by ID",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Appointment not found")
    @GetMapping("appointment/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@NotNull @Valid @PathVariable Long id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar cita
    @Operation(summary = "Update an appointment by ID")
    @ApiResponse(responseCode = "200", description = "Appointment updated successfully",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))
            })
    @ApiResponse(responseCode = "404", description = "Appointment not found")
    @PutMapping("appointment/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        // Verificar si la cita existe
        AppointmentDTO existingAppointment = appointmentService.getAppointmentById(id);
        if (existingAppointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar los datos de la cita
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentRequestDTO);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar cita
    @Operation(summary = "Delete an appointment by ID")
    @ApiResponse(responseCode = "204", description = "Appointment deleted successfully")
    @ApiResponse(responseCode = "404", description = "Appointment not found")
    @DeleteMapping("appointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        // Verificar si la cita existe
        AppointmentDTO existingAppointment = appointmentService.getAppointmentById(id);
        if (existingAppointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Eliminar la cita
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

