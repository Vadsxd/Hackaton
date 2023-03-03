package ru.nic.hackaton.responses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {
    @NotNull
    private long azimuth;

    @NotNull
    private long elevation;

    @NotNull
    private long speed;
}
