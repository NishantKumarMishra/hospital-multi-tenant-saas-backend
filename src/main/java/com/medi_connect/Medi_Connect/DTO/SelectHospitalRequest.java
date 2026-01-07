package com.medi_connect.Medi_Connect.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectHospitalRequest {

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;
}
