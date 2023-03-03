package ru.nic.hackaton.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateRequest {
    @NotNull
    private long X;

    @NotNull
    private long Y;
}
