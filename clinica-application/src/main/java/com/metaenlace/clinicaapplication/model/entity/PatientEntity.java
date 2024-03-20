package com.metaenlace.clinicaapplication.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PATIENT_DB")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class PatientEntity extends UserEntity {
    @Column(name = "NSS")
    private String nss; // Cambiado de NSS a nss
    
    @Column(name = "CARD_NUM")
    private String cardNum; // Cambiado de cardNum a cardNum
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @ManyToMany(mappedBy = "patients")
    private List<DoctorEntity> doctors;
    
    @OneToMany(mappedBy = "patient")
    private List<AppointmentEntity> appointments;

	public PatientEntity(Long id, String name, String surname, String username, String passcode, String nss, String cardNum, String phone, String address, List<DoctorEntity> doctors,
			List<AppointmentEntity> appointments) {
		super(id,name,surname,username,passcode);
		this.nss = nss;
		this.cardNum = cardNum;
		this.phone = phone;
		this.address = address;
		this.doctors = doctors;
		this.appointments = appointments;
	}
    
}
