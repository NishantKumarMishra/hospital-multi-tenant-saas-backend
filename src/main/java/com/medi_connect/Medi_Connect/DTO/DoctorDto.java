package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Doctor_Status;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    private String phone;
    private String password;
    private String specialization;
    private Integer experience;
    private Doctor_Status status;


    public Doctor_Status getStatus() {
        return status;
    }

    public void setStatus(Doctor_Status status) {
        this.status = status;
    }

    private String licenseNumber;
}
