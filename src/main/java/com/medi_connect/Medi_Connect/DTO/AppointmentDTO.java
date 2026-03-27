package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.VisitType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentDTO {
    private Long patientId;
    private VisitType visitType;
    private Long doctorId;
    private String source;

}
